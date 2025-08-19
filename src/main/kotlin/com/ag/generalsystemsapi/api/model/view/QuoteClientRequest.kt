package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.ClientsModel

class QuoteClientRequest (
    var quoteCode: Long,
    var client: ClientsModel? = null,
    var ownerClient: ClientsModel? = null,
    var clientPhotos: Iterable<ClientPhotos>? = null
)