package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "system_user_roles")
class UserRolesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usrl_id", nullable = true)
    var userRoleId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usrl_user_id")
    var userRolesUserId: UsersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usrl_rl_id")
    var userRolesRlId: SystemRolesModel? = null,

    @Column(name = "usrl_active", nullable = false)
    var userRoleActive: Boolean,
)