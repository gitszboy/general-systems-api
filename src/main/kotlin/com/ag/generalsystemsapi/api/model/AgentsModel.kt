package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.Date
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "agents")
class AgentsModel (
    @Id
    @Column(name = "agn_code")
    var agentCode: Long? = null,

    @Column(name = "agn_name", nullable = true)
    var agentName: String?,

    @Column(name = "agn_sht_desc", nullable = true)
    var agentShtDesc: String?,

    @Column(name = "agn_pass_reset", nullable = true)
    var agentPasswordReset: String? = "N",

    @Column(name = "agn_pass_reset_code", nullable = true)
    var agentPasswordResetCode: Long? = null,

    @Column(name = "agn_pass_reset_date", nullable = true)
    var agentPasswordResetDate: Date? = null,

    @Column(name = "agn_email", nullable = true)
    var agentEmail: String? = null,

    @Column(name = "agn_physical_address", nullable = true)
    var agentPhysicalAddr: String? = null,

    @Column(name = "agn_postal_address", nullable = true)
    var agentPostalAddr: String? = null,

    @Column(name = "agn_tel1", nullable = true)
    var agentTelephone: String? = null,

    @Column(name = "agn_pin", nullable = true)
    var agentPIN: String? = null,

    @Column(name = "agn_id_no", nullable = true)
    var agentIDNo: String? = null,

    @Column(name = "agn_status", nullable = true)
    var agentStatus: String? = null,

    @Column(name = "agn_nic_number", nullable = true)
    var agentRegNo: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agn_act_code")
    var agentAccountType: AccountTypesModel? = null,

    @Column(name = "agn_brn_code", nullable = true)
    var agentBranchCode: Long? = null,
)