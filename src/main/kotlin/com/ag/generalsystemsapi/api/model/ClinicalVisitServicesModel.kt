package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "clinical_visit_services")
class ClinicalVisitServicesModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cvs_code", nullable = false)
    var visitServCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvs_cv_code", nullable = true)
    var visitServVisit: ClinicalVisitModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvs_sspr_code", nullable = true)
    var visitServPeril: ClassPerilsModel? = null,

    @Column(name = "cvs_date", nullable = false)
    var visitServDate: Date? = null,

    @Column(name = "cvs_limit_amt", nullable = true)
    var visitServLimitAmt: Double? = null,

    @Column(name = "cvs_claim_amt", nullable = true)
    var visitServClaimAmt: Double? = null,

    @Column(name = "cvs_no_of_claims", nullable = true)
    var visitServNoOfClaims: Long? = null,

    @Column(name = "cvs_insured_amt", nullable = true)
    var visitServInsuredAmt: Double? = null,

    @Column(name = "cvs_excess_amt", nullable = true)
    var visitServExcessAmt: Double? = null,

    @Column(name = "cvs_status", nullable = true)
    var visitServStatus: String? = null,
)