package com.ag.generalsystemsapi.api.model.view

class ClientsFamHistoryRequest (
    var clientFamHCode: Long? = null,
    var clientFamHClientCode: Long? = null,
    var clientFamHLifeStatus: String?,
    var clientFamHHealthStatus: String?,
    var clientFamHAge: Long?,
    var clientFamHSicknessType: String? = null,
    var clientFamHSicknessDuration: Long? = null,
    var clientFamHDeathCause: String? = null,
)