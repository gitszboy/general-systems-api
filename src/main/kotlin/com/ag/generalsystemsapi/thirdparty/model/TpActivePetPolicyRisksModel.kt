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
@Table(name = "active_pet_policy_risks_view")
class TpActivePetPolicyRisksModel (
    @Id
    @Column(name = "ipu_code", nullable = false)
    var policyRiskCode: Long? = null,

    @Column(name = "clnt_code", nullable = false)
    var policyRiskClientCode: Long,

    @Column(name = "pol_batch_no", nullable = false)
    var policyRiskPolicyBatchNo: Long? = null,

    @Column(name = "ipu_property_id", nullable = false)
    var policyRiskPropertyID: String? = null,

    @Column(name = "ipu_item_desc", nullable = true)
    var policyRiskItemDesc: String? = null,

    @Column(name = "ipu_sec_scl_code", nullable = true)
    var policyRiskSubClassCode: Long? = null,

    @Column(name = "ipu_covt_sht_desc", nullable = true)
    var policyRiskCoverType: String?,

    @Column(name = "ipu_prorata", nullable = true)
    var policyRiskProrata: String? = null,

    @Column(name = "ipu_wef", nullable = true)
    var policyRiskWef: Date? = null,

    @Column(name = "ipu_wet", nullable = true)
    var policyRiskWet: Date? = null,

    @Column(name = "ipu_bind_code", nullable = true)
    var policyRiskBindCode: Long? = null,

    @Column(name = "bind_name", nullable = true)
    var policyRiskBindName: String? = null,

    @Column(name = "ipu_value", nullable = true)
    var policyRiskValue: Double? = null,

)