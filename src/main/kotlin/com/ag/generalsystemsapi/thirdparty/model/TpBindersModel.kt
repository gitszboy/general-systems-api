package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "gin_binders")
class TpBindersModel (
    @Id
    @Column(name = "bind_code", nullable = false)
    var bindCode: Long,

    @Column(name = "bind_agnt_agent_code", nullable = false)
    var bindAgentCode: Long? = null,

    @Column(name = "bind_agnt_sht_desc", nullable = false)
    var bindAgentShtDesc: String? = null,

    @Column(name = "bind_wef_date", nullable = false)
    var bindWef: Date? = null,

    @Column(name = "bind_wet_date", nullable = false)
    var bindWet: Date? = null,

    @Column(name = "bind_scl_code", nullable = false)
    var bindSubClassCode: Long? = null,

    @Column(name = "bind_remarks", nullable = false)
    var bindRemarks: String? = null,

    @Column(name = "bind_name", nullable = false)
    var bindName: String? = null,

    @Column(name = "bind_sht_desc", nullable = false)
    var bindShtDesc: String? = null,

    @Column(name = "bind_type", nullable = false)
    var bindType: String? = null,

    @Column(name = "bind_default", nullable = false)
    var bindDefault: String? = null,

    @Column(name = "bind_web_default", nullable = false)
    var bindWebDefault: String? = null,
)