package com.ag.generalsystemsapi.api.model.responses

import java.util.*

class ClinicalVisitResponse (
    var visitCode: Long? = null,
    var visitPatientCode: Long? = null,
    var visitPatientID: String? = null,
    var visitPatientName: String? = null,
    var visitPatientCover: String? = null,
    var visitPatientCoverWef: Date? = null,
    var visitPatientCoverWet: Date? = null,
    var visitPatientCoverAmount: Double? = null,
    var visitTotalAmount: Double? = null,
    var visitInsuredAmount: Double? = null,
    var visitExcessAmount: Double? = null,
    var visitOrganizationCode: Long? = null,
    var visitOrganizationName: String? = null,
    var visitDate: Date? = null,
    var visitDetails: String? = null,
    var visitType: String? = null,
    var visitBillable: String? = null,
    var visitCurrentService: String? = null,
    var visitComplaints: String? = null,
    var visitExaminations: String? = null,
    var visitClinicalDiagnosis: String? = null,
    var visitFinalDiagnosis: String? = null,
    var visitStatus: String? = null
)