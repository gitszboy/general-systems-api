package com.ag.generalsystemsapi.api.model.payload

import com.ag.generalsystemsapi.api.model.view.ClinicalVisitServicesView

class ClinicalVisitServicesRequest (
    var visitCode: Long,
    var servicesList: ArrayList<ClinicalVisitServicesView>
)