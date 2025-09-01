package com.ag.generalsystemsapi.api.model.view

import java.util.*

class ClinicalVisitServicesView  (
    var visitServCode: Long? = null,
    var visitServVisitCode: Long? = null,
    var visitServPerilCode: Long? = null,
    var visitServPerilName: String? = null,
    var visitServDate: Date? = null,
    var visitServLimitAmt: Double? = null,
    var visitServClaimAmt: Double? = null,
    var visitServNoOfClaims: Long? = null,
    var visitServInsuredAmt: Double? = null,
    var visitServExcessAmt: Double? = null,
    var visitServStatus: String? = null,
)