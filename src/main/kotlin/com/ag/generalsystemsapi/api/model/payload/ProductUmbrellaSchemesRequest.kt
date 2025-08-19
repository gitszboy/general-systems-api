package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ProductUmbrellaSchemesRequest (
    var prodUmbSchemeCode: Long,
    var prodUmbSchemeProduct: Long?,
    var prodUmbSchemeOrganization: Long?,
    var prodUmbSchemeRefNo: String?,
    var prodUmbSchemeName: String?,
    var prodUmbSchemeEffDate: Date?,
    var prodUmbSchemePinNo: String?,
    var prodUmbSchemeTaxExcemptNo: String?,
    var prodUmbSchemeRegNo: String?,
    var prodUmbSchemeStatus: String?
)