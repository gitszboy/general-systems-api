package com.ag.generalsystemsapi.api.model.payload

class PolicyVestingScaleRequest (
    var policyVestCode: Long? = null,
    var policyVestPolicy: Long? = null,
    var policyVestEndorsement: Long? = null,
    var policyVestYearFrom: Long? = null,
    var policyVestYearTo: Long? = null,
    var policyVestEmplyrEnt: Double? = null,
    var policyVestEmplyeEnt: Double? = null,
)