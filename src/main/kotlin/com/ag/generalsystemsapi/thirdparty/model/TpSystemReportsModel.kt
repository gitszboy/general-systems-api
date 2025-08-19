package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "tqc_system_reports")
class TpSystemReportsModel (
    @Id
    @Column(name = "rpt_code", nullable = false)
    var reportCode: Long,

    @Column(name = "rpt_name", nullable = false)
    var reportName: String,

    @Column(name = "rpt_data_file", nullable = false)
    var reportDataFile: String,

    @Column(name = "rpt_active", nullable = true)
    var reportActive: String,
)