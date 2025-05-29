package mx.gob.edomex.microservicios.digital_vault.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter :OncePerRequestFilter() {

    val HEADER: String = "Authorization"
    val PREFIX: String = "Bearer "
    val SECRET: String = "mySecretKey"

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            if (existeJWTToken(request, response)) {
                val claims: Claims = validateToken(request)
                if (claims["authorities"] != null) {
                    setUpSpringAuthentication(claims)
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }
            chain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN)
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
            return
        }
    }

    private fun validateToken(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader(HEADER).replace(PREFIX, "")
        return Jwts.parser().setSigningKey(SECRET.toByteArray()).parseClaimsJws(jwtToken).body
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    private fun setUpSpringAuthentication(claims: Claims) {
        val authorities: List<String>? = claims["authorities"] as List<String>
        val auth = UsernamePasswordAuthenticationToken(claims.subject, null,
                authorities!!.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList()))
        SecurityContextHolder.getContext().authentication = auth
    }

    private fun existeJWTToken(request: HttpServletRequest, res: HttpServletResponse): Boolean {
        val authenticationHeader = request.getHeader(HEADER)
        return if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) false else true
    }
}