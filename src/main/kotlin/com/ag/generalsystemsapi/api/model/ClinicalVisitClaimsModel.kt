package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "clinical_visit_claims")
class ClinicalVisitClaimsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cvcl_code", nullable = false)
    var clClaimCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvcl_org_code", nullable = true)
    var clClaimOrganization: OrganizationModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvcl_cv_code", nullable = true)
    var clClaimVisit: ClinicalVisitModel? = null,

    @Column(name = "cvcl_claim_date", nullable = false)
    var clClaimDate: Date? = null,

    @Column(name = "cvcl_claim_amt", nullable = true)
    var clClaimAmount: Double? = null,

    @Column(name = "cvcl_search_status", nullable = true)
    var clClaimSearchStatus: String? = null,

    @Column(name = "cvcl_status", nullable = true)
    var clClaimStatus: String? = null,

    @Column(name = "cvcl_paid_status", nullable = true)
    var clClaimPaidStatus: String? = "Pending",
)