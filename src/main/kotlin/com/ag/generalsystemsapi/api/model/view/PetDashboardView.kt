package com.ag.generalsystemsapi.api.model.view

class PetDashboardView (
    var monthPaidClaimsCount: Long? = null,
    var monthPendingClaimsCount: Long? = null,
    var allPaidClaimsCount: Long? = null,
    var allPendingClaimsCount: Long? = null,
    var monthActiveVisitsCount: Long? = null,
    var monthCompletedVisitsCount: Long? = null,
    var allActiveVisitsCount: Long? = null,
    var allCompletedVisitsCount: Long? = null,
    var barChartView: BarChartView? = null,
    var pieChartView: PieChartView? = null
)