package com.ag.generalsystemsapi.api.controller

import com.ag.generalsystemsapi.api.model.ServiceRequestTypesModel
import com.ag.generalsystemsapi.api.model.payload.ServiceRequest
import com.ag.generalsystemsapi.api.model.view.PortalNotificationsView
import com.ag.generalsystemsapi.api.model.view.ServiceRequestView
import com.ag.generalsystemsapi.api.service.IServiceRequestService
import com.ag.generalsystemsapi.api.util.Result
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/serviceRequest")
@Tag(name = "Service Request Controller", description = "Endpoint - This service manages calls relating to Service Requests")
@CrossOrigin(origins = ["*"])
class ServiceRequestsController {

    @Autowired
    lateinit var iServiceRequest: IServiceRequestService

    @Operation(summary = "Save Service Request", description = "saves Service Request")
    @RequestMapping(value = ["/saveServiceRequest"], method = [RequestMethod.POST])
    fun saveServiceRequest(
        @RequestBody serviceReq: ServiceRequest
    ) : ResponseEntity<Void> {
        iServiceRequest.saveServiceRequest(serviceReq)

        return ResponseEntity<Void>(HttpStatus.OK)
    }

    @Operation(summary = "Find Client Service Requests", description = "Fetches All Client Service Requests")
    @GetMapping("/findClientServiceRequests", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findClientServiceRequests(@RequestParam(name= "clientCode", required = true) clientCode: Long): Result<Iterable<ServiceRequestView>> = iServiceRequest.findClientServiceRequests(clientCode)

    @Operation(summary = "Find Agent Service Requests", description = "Fetches All Agent Service Requests")
    @GetMapping("/findAgentServiceRequests", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAgentServiceRequests(@RequestParam(name= "agentCode", required = true) agentCode: Long): Result<Iterable<ServiceRequestView>> = iServiceRequest.findAgentServiceRequests(agentCode)

    @Operation(summary = "Find Policy Service Requests", description = "Fetches All Policy Service Requests")
    @GetMapping("/findPolicyServiceRequests", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPolicyServiceRequests(@RequestParam(name= "policyCode", required = true) policyCode: Long): Result<Iterable<ServiceRequestView>> = iServiceRequest.findPolicyServiceRequests(policyCode)

    @Operation(summary = "Find Service Requests Types", description = "Fetches All Service Requests Types")
    @GetMapping("/findServiceRequestTypes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findServiceRequestTypes(@RequestParam(name= "serviceArea", required = true) serviceArea: String): Result<Iterable<ServiceRequestTypesModel>> = iServiceRequest.findServiceRequestTypes(serviceArea)
    @Operation(summary = "delete Service Request", description = "delete Service Request")
    @RequestMapping(value = ["/deleteServiceRequest"], method = [RequestMethod.POST])
    fun deleteServiceRequest(
        @RequestParam(name= "serviceRequestCode", required = true) serviceRequestCode: Long
    ) : ResponseEntity<Void> {
        iServiceRequest.deleteServiceRequest(serviceRequestCode)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
    @Operation(summary = "Find All Service Requests", description = "Fetches All Service Requests")
    @GetMapping("/findAllServiceRequests", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllServiceRequests(): Result<Iterable<ServiceRequestView>> = iServiceRequest.findAllServiceRequests()

    @Operation(summary = "Find All User Notifications", description = "Fetches All User Notifications")
    @GetMapping("/findUserNotifications", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserNotifications(
        @RequestParam(name= "userCode", required = true) userCode: Long
    ): Result<Iterable<PortalNotificationsView>> = iServiceRequest.findUserNotifications(userCode)

    @Operation(summary = "Find Active User Notifications", description = "Find Active User Notifications")
    @GetMapping("/findActiveUserNotifications", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findActiveUserNotifications(
        @RequestParam(name= "userCode", required = true) userCode: Long
    ): Result<Iterable<PortalNotificationsView>> = iServiceRequest.findActiveUserNotifications(userCode)

    @Operation(summary = "Save User Notifications", description = "saves User Notifications")
    @RequestMapping(value = ["/saveUserNotification"], method = [RequestMethod.POST])
    fun saveUserNotification(
        @RequestParam(name= "notificationCode", required = false) notificationCode: Long?,
        @RequestParam(name= "userCode", required = true) userCode: Long,
        @RequestParam(name= "title", required = false) title: String?,
        @RequestParam(name= "body", required = false) body: String?,
        @RequestParam(name= "status", required = false) status: String?
    ) : ResponseEntity<Void> {
        iServiceRequest.saveUserNotification(notificationCode, userCode, title, body, status)

        return ResponseEntity<Void>(HttpStatus.OK)
    }

    @Operation(summary = "Update Service Request", description = "Update Service Request")
    @RequestMapping(value = ["/updateServiceRequest"], method = [RequestMethod.POST])
    fun updateServiceRequest(
        @RequestBody serviceReq: ServiceRequest
    ) : ResponseEntity<Void> {
        iServiceRequest.updateServiceRequest(serviceReq)

        return ResponseEntity<Void>(HttpStatus.OK)
    }

    @Operation(summary = "deactivate notification", description = "deactivate notification")
    @RequestMapping(value = ["/deactivateNotification"], method = [RequestMethod.POST])
    fun deactivateNotification(
        @RequestParam(name= "notificationCode", required = false) notificationCode: Long,
    ) : ResponseEntity<Void> {
        iServiceRequest.deactivateNotification(notificationCode)

        return ResponseEntity<Void>(HttpStatus.OK)
    }

}