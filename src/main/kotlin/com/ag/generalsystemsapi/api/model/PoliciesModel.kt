package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "policies")
class PoliciesModel (
    @Id
    @Column(name = "pol_batch_no", nullable = true)
    var policyBatchNo: Long? = null,

    @Column(name = "pol_policy_no", nullable = true)
    var policyNumber: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pol_pro_code", nullable = true)
    var policyProductCode: ProductsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pol_prp_code", nullable = true)
    var policyClient: ClientsModel? = null,

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