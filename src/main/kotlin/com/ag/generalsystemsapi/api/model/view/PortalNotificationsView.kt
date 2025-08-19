package com.ag.generalsystemsapi.api.model.view

import java.util.Date

class PortalNotificationsView (
    var notificationCode: Long?,
    var notificationUser: Long?,
    var notificationTitle: String?,
    var notificationBody: String?,
    var notificationDate: Date?,
    var notificationStatus: String?
)