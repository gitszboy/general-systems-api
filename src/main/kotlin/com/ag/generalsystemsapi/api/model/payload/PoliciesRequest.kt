package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class PoliciesRequest (
    var policyCode: Long,
    var policyEffectiveDate: Date? = null,
    var policyDateSigned: Date? = null,
    var policyClient: Long,
    var policyProduct: Long,
    var policyProductUmbrellaScheme: Long? = null,
    var policyPaymentMethod: String? = null,
    var policyPaymentFrequency: String? = null,
    var policyPreparedBy: Long? = null,
    var endCode: Long,
    var endType: String,
    var endAgent: Long? = null,
    var endAgentCommissionRate: Double? = null,
    var endRetireAge: Long? = null,
    var endMonthlySalary: Double? = null,
    var endEmplyeContriType: String? = null,
    var endEmplyeContri: Double? = null,
    var endEmplyrContriType: String? = null,
    var endEmplyrContri: Double? = null,
    var endGuarrantedIntRate: Double? = null,
    var endSchemeName: String? = null,
    var endSchemeEffectiveDate: Date? = null,
    var endSchemeRegStatus: String? = null,
    var endSchemeRegNo: String? = null,
    var endSchemeRegDate: Date? = null,
    var endSchemePinNo: String? = null,
    var endSchemeTaxExcmptNo: String? = null,
)