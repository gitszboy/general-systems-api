package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "binders")
class BindersModel  (
    @Id
    @Column(name = "bind_code", nullable = false)
    var bindCode: Long,

    @Column(name = "bind_agnt_agent_code", nullable = true)
    var bindAgentCode: Long? = null,

    @Column(name = "bind_agnt_sht_desc", nullable = true)
    var bindAgentShtDesc: String? = null,

    @Column(name = "bind_wef_date", nullable = true)
    var bindWef: Date? = null,

    @Column(name = "bind_wet_date", nullable = true)
    var bindWet: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bind_scl_code", nullable = true)
    var bindSubClassCode: SubClassesModel? = null,

    @Column(name = "bind_remarks", nullable = true)
    var bindRemarks: String? = null,

    @Column(name = "bind_name", nullable = true)
    var bindName: String? = null,

    @Column(name = "bind_sht_desc", nullable = true)
    var bindShtDesc: String? = null,

    @Column(name = "bind_type", nullable = true)
    var bindType: String? = null,

    @Column(name = "bind_default", nullable = true)
    var bindDefault: String? = null,

    @Column(name = "bind_web_default", nullable = true)
    var bindWebDefault: String? = null,
)