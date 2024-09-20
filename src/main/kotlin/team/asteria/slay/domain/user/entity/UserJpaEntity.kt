package team.asteria.slay.domain.user.entity

import jakarta.persistence.*
import team.asteria.slay.domain.user.entity.enums.Role

@Entity
@Table(name = "tb_user")
data class UserJpaEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long?,

    @Column(name = "name", columnDefinition = "VARCHAR(10)")
    val name: String?,

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(320)")
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: Role,

    @Column(name = "coin", nullable = false)
    val coin: Int
)
