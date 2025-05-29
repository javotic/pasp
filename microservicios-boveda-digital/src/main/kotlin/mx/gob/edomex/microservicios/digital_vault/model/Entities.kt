package mx.gob.edomex.microservicios.digital_vault.model

import com.vladmihalcea.hibernate.type.json.JsonStringType
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.json.JSONObject
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

/**
 * JSON Models
 */

data class FileIds (val userId: Int, val fileId: Int)
data class Msg (val status: Boolean, val message: String)
data class Msg1 (val status: Boolean, val message: Object)
data class DecryptFile (val userId: Int?, val fileId: Int?, val username: String?, val fileName: String, val idDocProfesional: Int)
data class User (val username: String, val password: String)
data class UserToken (val userId: Int, val username: String, val token: String)

@Entity
@Table(name = "vault_user")
class VaultUser (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "user_id")
        var userId : Int,
        @Column(unique=true)
        var username: String,
        var pass: String,
        @Column(name = "key")
        var privateKey: ByteArray,
        @Column(name = "type_key")
        var typeKey: String,
        @OneToMany (mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
        var vaultFiles: Set<Vault>? = null

) {
        constructor () : this (0 , "", "", ByteArray(0), "")
}

@Embeddable
class VaultId(
        @Column(name="user_id")
        var userId: Int,
        @Column(name="file_id")
        var fileId: Int
) : Serializable {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as VaultId

                if (userId != other.userId) return false
                if (fileId != other.fileId) return false

                return true
        }

        override fun hashCode(): Int {
                var result = userId
                result = 31 * result + fileId
                return result
        }
}


@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
@Entity
@Table(name = "vault")
class Vault (
        @EmbeddedId
        var vaultId: VaultId?,
        var name: String,
        @Type(type = "jsonb")
        @Column (name = "metada_data",  columnDefinition = "jsonb")
        var metadaData: String?,
        @Column (name = "date_storage")
        var dateStorage: Timestamp?,
        @Column (name = "last_view")
        var lastView: Timestamp?,
        @Column (name = "id_doc_profesional")
        var idDocProfesional: Int,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumns(
                JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
        )
        var user: VaultUser? = null
) {
        constructor() : this (null, "","", null, null,0, null)
}
