package mx.gob.edomex.microservicios.digital_vault.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface VaultUserRepository : JpaRepository<VaultUser, Int> {
    fun findByUsername(username: String): VaultUser
    fun findByUsernameAndPass(username: String, pass: String): VaultUser
}

@Repository
interface VaultRepository : JpaRepository<Vault, VaultId>{

    @Query("SELECT coalesce(max(v.id.fileId), 0) FROM Vault v WHERE v.id.userId = :userId")
    fun getMaxId(@Param("userId") userId: Int): Int
    fun findByVaultIdUserId(userId: Int) : List<Vault>
    fun findByVaultIdUserIdAndVaultIdFileId(userId: Int, fileId: Int) : Vault
    @Query("SELECT * FROM Vault v WHERE v.user_id = ? AND v.id_doc_profesional = ?", nativeQuery = true)
    fun findByVaultIdUserAndIdDoc(@Param("userId") userId: Int, @Param("idDocProfesional") idDocProfesional: Int) : List<Vault>

}
