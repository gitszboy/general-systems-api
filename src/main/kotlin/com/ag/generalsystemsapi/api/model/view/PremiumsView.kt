package com.ag.generalsystemsapi.api.model.view

class PremiumsView (
    var premiumPaid: Double?,
    var premiumDue: Double?,
    var loanOs: Double?,
    val surrVal: Double? = null,
    val policyVal: Double? = null,
    val policyCount: Double? = null
)