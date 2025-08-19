package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "tqc_sys_rpt_templates")
class TpSystemReportTemplateModel (
    @Id
    @Column(name = "rpt_tmpl_code", nullable = false)
    var reportTemplCode: Long,

    @Column(name = "rpt_tmpl_rpt_code", nullable = false)
    var reportTemplReportCode: Long,

    @Column(name = "rpt_tmpl_file", nullable = false)
    var reportTemplFile: String,

    @Column(name = "rpt_tmpl_name", nullable = true)
    var reportTemplName: String,

    @Column(name = "rpt_tmpl_style_file", nullable = true)
    var reportTemplStyleFile: String,

    @Column(name = "rpt_tmpl_active", nullable = true)
    var reportTemplActive: String,
)