package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "system_roles")
class SystemRolesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rl_id", nullable = false)
    var roleId: Long,

    @Column(name = "rl_name", nullable = false)
    var roleName: String,

    @Column(name = "rl_active", nullable = true)
    var roleActive: Boolean,

    @Column(name = "rl_default", nullable = true)
    var roleDefault: Boolean,
)