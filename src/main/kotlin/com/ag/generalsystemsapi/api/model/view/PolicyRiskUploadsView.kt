package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.ClinicalVisitModel
import com.ag.generalsystemsapi.api.model.FileTypesModel
import java.util.*

class PolicyRiskUploadsView (
    var polRiskUploadsCode: Long? = null,
    var polRiskUploadsVisit: Long? = null,
    var fileTypeName: String? = null,
    var fileUploadFileName: String? = null,
    var fileUploadFileUri: String? = null,
    var fileUploadFileDownloadUri: String? = null,
    var fileUploadFileSize: Long? = null,
    var fileUploadDate: Date? = null,
)