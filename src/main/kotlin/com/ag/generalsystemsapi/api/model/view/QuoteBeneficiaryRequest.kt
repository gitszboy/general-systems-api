package com.ag.generalsystemsapi.api.model.view

import java.util.*

class QuoteBeneficiaryRequest (
    var quoteBenCode: Long?,
    var quoteBenQuote: Long?,
    var quoBenName: String? = null,
    var quoBenTel: String? = null,
    var quoBenIdNo: String? = null,
    var quoBenDateOfBirth: Date? = null,
    var quoBenPerc: Double? = null,
    var quoBenRelType: String? = null,
    var quoBenGuardianName: String? = null,
    var quoBenGuardianTel: String? = null,
    var quoBenGuardianIdNo: String? = null,
    var quoBenGuardianRelType: String? = null,
)