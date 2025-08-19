package com.ag.generalsystemsapi.api.model.payload

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import java.util.*
import javax.persistence.*

class ClinicalVisitRequest (
    var visitCode: Long? = null,
    var visitPatient: Long,
    var visitOrganization: Long,
    var visitDate: Date? = null,
    var visitDetails: String? = null,
    var visitType: String? = null,
    var visitBillable: String? = null,
    var visitCurrentService: String? = null,
    var visitComplaints: String? = null,
    var visitExaminations: String? = null,
    var visitClinicalDiagnosis: String? = null,
    var visitFinalDiagnosis: String? = null,
)