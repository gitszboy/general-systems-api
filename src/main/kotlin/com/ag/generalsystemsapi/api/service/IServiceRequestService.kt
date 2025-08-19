package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.ServiceRequestTypesModel
import com.ag.generalsystemsapi.api.model.payload.ServiceRequest
import com.ag.generalsystemsapi.api.model.view.PortalNotificationsView
import com.ag.generalsystemsapi.api.model.view.ServiceRequestView
import com.ag.generalsystemsapi.api.util.Result

interface IServiceRequestService {
    fun saveServiceRequest(serviceRequest: ServiceRequest)
    fun findClientServiceRequests(clientCode: Long): Result<Iterable<ServiceRequestView>>
    fun findAgentServiceRequests(agentCode: Long): Result<Iterable<ServiceRequestView>>
    fun findPolicyServiceRequests(policyCode: Long): Result<Iterable<ServiceRequestView>>
    fun findServiceRequestTypes(area: String): Result<Iterable<ServiceRequestTypesModel>>
    fun deleteServiceRequest(serviceRequest: Long)
    fun findAllServiceRequests(): Result<Iterable<ServiceRequestView>>
    fun findUserNotifications(userCode: Long) : Result<Iterable<PortalNotificationsView>>
    fun findActiveUserNotifications(userCode: Long) : Result<Iterable<PortalNotificationsView>>
    fun saveUserNotification(notificationCode: Long?,
                             userCode: Long,
                             title: String?,
                             body: String?,
                             status: String?)
    fun updateServiceRequest(serviceRequest: ServiceRequest)
    fun deactivateNotification(notificationCode: Long)
}