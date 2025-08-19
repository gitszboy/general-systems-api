package com.ag.generalsystemsapi.api.model.payload

class ProductRequiredDocumentsRequest (
    var prodReqDocCode: Long? = null,
    var prodReqDocProduct: Long? = null,
    var prodReqDocDocument: Long? = null,
    var prodReqDocMandatory: String? = null,
    )