package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.ClientsModel

class FinalQuoteRequest (
    var quoteCode: Long,
    var quoteDeclaration: String,
    var quoteGoForMedicals: String,
    var quoteSourceFunds: String,
    var client: ClientsModel,
    var ownerClient: ClientsModel,
    var beneficiaries: ArrayList<QuoteBeneficiaryRequest>,
    var ailments: ArrayList<ClientsMedHistoryRequest>,
    var familyMember: ArrayList<ClientsFamHistoryRequest>,
    var clientPhotos: ArrayList<ClientPhotos>
)