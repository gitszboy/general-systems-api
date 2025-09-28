package com.ag.generalsystemsapi.api.model.view

import java.util.*

class QuotationRiskUploadsView (
    var quoRiskUploadsCode: Long? = null,
    var quoRiskUploadsRisk: Long? = null,
    var fileTypeName: String? = null,
    var fileUploadFileName: String? = null,
    var fileUploadFileUri: String? = null,
    var fileUploadFileDownloadUri: String? = null,
    var fileUploadFileSize: Long? = null,
    var fileUploadDate: Date? = null,
)