package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "policy_risks")
class PolicyRisksModel(
    @Id
    @Column(name = "ipu_code", nullable = false)
    var policyRiskCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipu_pol_batch_no", nullable = true)
    var policyRiskPolicyBatchNo: PoliciesModel? = null,

    @Column(name = "ipu_property_id", nullable = false)
    var policyRiskPropertyID: String? = null,

    @Column(name = "ipu_item_desc", nullable = true)
    var policyRiskItemDesc: String? = null,

    @Column(name = "ipu_value", nullable = true)
    var policyRiskValue: Double? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipu_sec_scl_code", nullable = true)
    var policyRiskSubClassCode: SubClassesModel? = null,

    @Column(name = "ipu_covt_sht_desc", nullable = true)
    var policyRiskCoverType: String?,

    @Column(name = "ipu_prorata", nullable = true)
    var policyRiskProrata: String? = null,

    @Column(name = "ipu_wef", nullable = true)
    var policyRiskWef: Date? = null,

    @Column(name = "ipu_wet", nullable = true)
    var policyRiskWet: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipu_bind_code", nullable = true)
    var policyRiskBindCode: BindersModel? = null,

)