package com.ag.generalsystemsapi.api.model.payload

import java.util.Date

class ProductRequest (
    var productCode: Long?,
    var productPrefix: String?,
    var productDescription: String?,
    var productType: String?,
    var productPensionType: String?,
    var productStatus: String?,
    var productStartDate: Date?,
    var productEndDate: Date?,
    var productUmbrella: String?,
    var productUmbrellaType: String?,
    var productPensAdminType: String?,
    var productMinAge: Long?,
    var productMaxAge: Long?
)