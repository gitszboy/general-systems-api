package com.ag.generalsystemsapi.api.model.view

import java.util.*

class ActivePolicyView (
    var activeId: Long,
    var activePolProduct: String,
    var activePolicyNo: String,
    var activePolPremium: Double,
    var activePolSA: Double,
    var activePolIdNo: String?,
    var activePolEffectiveDate: Date?,
)