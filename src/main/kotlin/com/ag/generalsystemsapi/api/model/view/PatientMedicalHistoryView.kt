package com.ag.generalsystemsapi.api.model.view

import java.util.*

class PatientMedicalHistoryView  (
    var visitCode: Long? = null,
    var visitDate: Date? = null,
    var visitComplaints: String? = null,
    var visitExaminations: String? = null,
    var visitManagement: String? = null,
    var visitClinicalDiagnosis: String? = null,
    var visitFinalDiagnosis: String? = null,
    var visitStatus: String? = null,
)