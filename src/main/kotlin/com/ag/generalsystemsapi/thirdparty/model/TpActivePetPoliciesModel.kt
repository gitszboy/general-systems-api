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
@Table(name = "active_pet_policies_view")
class TpActivePetPoliciesModel (
    @Id
    @Column(name = "clnt_code", nullable = false)
    var policyClientCode: Long,

    @Column(name = "clnt_name", nullable = false)
    var policyClientName: String? = null,

    @Column(name = "clnt_other_names", nullable = false)
    var policyClientOtherNames: String? = null,

    @Column(name = "clnt_pin", nullable = false)
    var policyClientPIN: String? = null,

    @Column(name = "clnt_id_reg_no", nullable = true)
    var policyClientIDNumber: String? = null,

    @Column(name = "clnt_dob", nullable = true)
    var policyClientDOB: Date? = null,

    @Column(name = "clnt_gender", nullable = true)
    var policyClientGender: String?,

    @Column(name = "clnt_tel", nullable = true)
    var policyClientTelephone: String? = null,

    @Column(name = "clnt_physical_addrs", nullable = true)
    var policyClientPhyAddress: String? = null,

    @Column(name = "clnt_email_addrs", nullable = true)
    var policyClientEmailAddress: String? = null,

    @Column(name = "clnt_type", nullable = true)
    var policyClientType: String? = null,

    @Column(name = "pol_batch_no", nullable = true)
    var policyBatchNo: Long? = null,

    @Column(name = "pol_policy_no", nullable = true)
    var policyNumber: String? = null,

    @Column(name = "pol_pro_code", nullable = true)
    var policyProductCode: Long? = null,

    @Column(name = "pol_policy_cover_from", nullable = true)
    var policyCoverFromDate: Date? = null,

    @Column(name = "pol_policy_cover_to", nullable = true)
    var policyCoverToDate: Date? = null,

    @Column(name = "pol_total_sum_insured", nullable = true)
    var policyTotalSumInsured: Double? = null,

    @Column(name = "pol_nett_premium", nullable = true)
    var policyTotalNetPremium: Double? = null,

    @Column(name = "pol_freq_of_payment", nullable = true)
    var policyFrequencyOfPayment: String? = null,

    @Column(name = "pol_policy_status", nullable = true)
    var policyStatus: String? = null,

    @Column(name = "pol_current_status", nullable = true)
    var policyCurrentStatus: String? = null,
)