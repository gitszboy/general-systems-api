package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "clinical_visit")
class ClinicalVisitModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_code", nullable = false)
    var visitCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_ipu_code", nullable = true)
    var visitPatient: PolicyRisksModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_org_code", nullable = true)
    var visitOrganization: OrganizationModel? = null,

    @Column(name = "cv_date", nullable = false)
    var visitSystemDate: Date? = null,

    @Column(name = "cv_visit_date", nullable = false)
    var visitDate: Date? = null,

    @Column(name = "cv_visit_details", nullable = true)
    var visitDetails: String? = null,

    @Column(name = "cv_visit_type", nullable = true)
    var visitType: String? = null,

    @Column(name = "cv_billable", nullable = true)
    var visitBillable: String? = null,

    @Column(name = "cv_current_service", nullable = true)
    var visitCurrentService: String? = null,

    @Column(name = "cv_complaints", nullable = true)
    var visitComplaints: String? = null,

    @Column(name = "cv_examinations", nullable = true)
    var visitExaminations: String? = null,

    @Column(name = "cv_clinical_diagnosis", nullable = true)
    var visitClinicalDiagnosis: String? = null,

    @Column(name = "cv_final_diagnosis", nullable = true)
    var visitFinalDiagnosis: String? = null,

    @Column(name = "cv_total_amt", nullable = true)
    var visitTotalAmount: Double? = null,

    @Column(name = "cv_insured_amt", nullable = true)
    var visitInsuredAmount: Double? = null,

    @Column(name = "cv_excess_amt", nullable = true)
    var visitExcessAmount: Double? = null,

    @Column(name = "cv_status", nullable = true)
    var visitStatus: String? = null,

    @Column(name = "cv_management", nullable = true)
    var visitManagement: String? = null,
)