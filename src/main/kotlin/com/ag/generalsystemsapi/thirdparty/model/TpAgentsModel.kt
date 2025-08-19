package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "lms_agencies")
@NamedStoredProcedureQueries(
    NamedStoredProcedureQuery(
        name = "auth_agent",
        procedureName = "TQC_WEB_PKG.agn_authenticate",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_entered_user", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_entered_pwd", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_reset", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_msg", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_return", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_agn_code", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_agn_name", type = String::class)
        )
    )
)
class TpAgentsModel (
    @Id
    @Column(name = "agn_code", nullable = false)
    var agnCode: Long,

    @Column(name = "agn_sht_desc", nullable = true)
    var agenUserName: String?,

    @Column(name = "agn_name", nullable = true)
    var agentName: String?,

    @Column(name = "agn_email_address", nullable = true)
    var agentEmail: String?,

    @Column(name = "agn_physical_address", nullable = true)
    var agentPhysicalAddr: String?,

    @Column(name = "agn_postal_address", nullable = true)
    var agentPostalAddr: String?,

    @Column(name = "agn_tel1", nullable = true)
    var agentTelephone: String?,

    @Column(name = "agn_pin", nullable = true)
    var agentPIN: String?,

    @Column(name = "agn_id_no", nullable = true)
    var agentIDNo: String?,

    @Column(name = "agn_status", nullable = true)
    var agentStatus: String?,

    @Column(name = "agn_nic_number", nullable = true)
    var agentRegNo: String?,

    @Column(name = "agn_act_code", nullable = true)
    var agentAccountType: Long?,

    @Column(name = "agn_brn_code", nullable = true)
    var agentBranchCode: Long? = null,

    )