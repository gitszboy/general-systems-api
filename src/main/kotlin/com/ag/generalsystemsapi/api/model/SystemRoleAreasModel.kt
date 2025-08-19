package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "system_role_areas")
class SystemRoleAreasModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rla_id", nullable = false)
    var roleAreaId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rla_rl_id")
    var roleAreaRole: SystemRolesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rla_sa_id")
    var roleAreaSysArea: SystemAreasModel? = null,

    @Column(name = "rla_name", nullable = false)
    var roleAreaName: String,

    @Column(name = "rla_active", nullable = true)
    var roleAreaActive: Boolean,

    @Column(name = "rla_create", nullable = true)
    var roleAreaCreate: Boolean,

    @Column(name = "rla_update", nullable = true)
    var roleAreaUpdate: Boolean,

    @Column(name = "rla_delete", nullable = true)
    var roleAreaDelete: Boolean,

    @Column(name = "rla_read", nullable = true)
    var roleAreaRead: Boolean,
)