package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PortalNotificationsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PortalNotificationsRepository : JpaRepository<PortalNotificationsModel, Long> {

    fun findByNotificationUser(user: UsersModel) : Iterable<PortalNotificationsModel>
    fun findByNotificationUserAndNotificationStatus(user: UsersModel, status: String) : Iterable<PortalNotificationsModel>
    fun findByNotificationCode(notificationCode: Long) : Optional<PortalNotificationsModel>

}