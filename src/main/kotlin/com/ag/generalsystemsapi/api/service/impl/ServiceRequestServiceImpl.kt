package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.helpers.NotificationResourceHelper
import com.ag.generalsystemsapi.api.model.PortalNotificationsModel
import com.ag.generalsystemsapi.api.model.ServiceRequestTypesModel
import com.ag.generalsystemsapi.api.model.ServiceRequestsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import com.ag.generalsystemsapi.api.model.payload.ServiceRequest
import com.ag.generalsystemsapi.api.model.view.PortalNotificationsView
import com.ag.generalsystemsapi.api.model.view.ServiceRequestView
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IServiceRequestService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServiceRequestServiceImpl : IServiceRequestService {

    @Autowired
    lateinit var serviceRequestAreasRepo: ServiceRequestAreasRepository

    @Autowired
    lateinit var serviceRequestTypesRepo: ServiceRequestTypesRepository

    @Autowired
    lateinit var serviceRequestsRepo: ServiceRequestsRepository

    @Autowired
    lateinit var portalNotificationsRepo: PortalNotificationsRepository

    @Autowired
    lateinit var usersRepo: UsersRepository

    @Autowired
    lateinit var clientsRepo: ClientsRepository

    @Autowired
    lateinit var agentsRepo: AgentsRepository

    @Autowired
    lateinit var notificationResourceHelper: NotificationResourceHelper

    override fun saveServiceRequest(serviceRequest: ServiceRequest){
        val user: UsersModel = usersRepo.findByUserId(serviceRequest.servReqUserRequesting)
            .orElseThrow { Exception("User not found") }

        val srt = serviceRequestTypesRepo.findByServReqTypeId(serviceRequest.servReqType)

        //Fetch the default user to assign to.
        val userAssignee: UsersModel = usersRepo.findByUsername(srt.servReqTypeAssignTo)
            .orElseThrow { Exception("User to be assigned to not found") }

        var sr = ServiceRequestsModel(
             servReqId = serviceRequest.servReqId,
             servReqUserRequesting = user,
             servReqType = srt,
             servReqPolicyCode = serviceRequest.servReqPolicyCode,
             servReqAgentCode = serviceRequest.servReqAgentCode,
             servReqDescription = serviceRequest.servReqDescription,
             servReqDate = serviceRequest.servReqDate?:Calendar.getInstance().time,
             servReqAssignee = srt.servReqTypeAssignTo,
             servReqAssignComments = serviceRequest.servReqAssignComments,
             servReqStatus = "Submitted to Insurer",
             servReqUserAssignedTo = userAssignee,
             servReqClientCode = serviceRequest.servReqClientCode
        )
        sr = serviceRequestsRepo.save(sr)

        //create notification to user.
        saveUserNotification(null ,
                             sr.servReqUserAssignedTo?.userId!!,
                             "New Service Request Notification",
                            "A Service request by the user ${user.userFullName} has been created for your action:\n " +
                                 "${serviceRequest.servReqDescription} \n",
                            serviceRequest.servReqStatus)

        val receipent = sr.servReqUserAssignedTo?.userEmail
        if(receipent != null){
            val emailSubject = "New Service Request Notification"
            val emailBody: String =
                "<p>Dear ${sr.servReqUserAssignedTo?.userFullName}.</p>" +
                        "<p>A Service request by the user ${user.userFullName} has been created for your action: </p>. " +
                        "<p><b>Description: ${serviceRequest.servReqDescription}</p></b>" +
                        "<p>Geminia Life Assurance Limited</p>. "
            notificationResourceHelper.sendSimpleEmail(receipent,emailSubject, emailBody, true)
        }

    }

    override fun updateServiceRequest(serviceRequest: ServiceRequest){
        var request = serviceRequestsRepo.findByServReqId(serviceRequest.servReqId)
            .orElseThrow { Exception("Service Requests not found") }

        //Fetch the default user to assign to.
        val userAssignee: UsersModel = usersRepo.findByUsername(serviceRequest.servReqAssignee)
            .orElseThrow { Exception("User to be assigned to not found") }

        val srt = serviceRequestTypesRepo.findByServReqTypeId(serviceRequest.servReqType)

        request.servReqAssignComments = serviceRequest.servReqAssignComments
        request.servReqStatus = serviceRequest.servReqStatus
        request.servReqUpdateDate = serviceRequest.servReqUpdateDate
        request.servReqUserAssignedTo = userAssignee

        serviceRequestsRepo.save(request)

        //create notification to user.
        saveUserNotification(null ,
                             request.servReqUserRequesting?.userId!!,
                             "Service Request Notification Update",
                             "Your request on the following has been updated:\n " +
                                   "${serviceRequest.servReqDescription} \n" +
                                   "Update: ${serviceRequest.servReqAssignComments} ",
                             serviceRequest.servReqStatus)

    }

    override fun findClientServiceRequests(clientCode: Long): Result<Iterable<ServiceRequestView>> {
        val serviceRequests: ArrayList<ServiceRequestView> = ArrayList()
        for(s in serviceRequestsRepo.findByServReqClientCode(clientCode)) {
            var req = ServiceRequestView(
                servReqId = s.servReqId,
                servReqType = s.servReqType,
                servReqUserRequesting = s.servReqUserRequesting?.userId,
                servReqUserRequestingName = s.servReqUserRequesting?.userFullName,
                servReqPolicyCode = s.servReqPolicyCode,
                //servReqPolicyNo = tqActivePoliciesRepository.findByActiveId(s.servReqPolicyCode)?.activePolicyNo,
                servReqAgentCode = s.servReqAgentCode,
                servReqAgentName = agentsRepo.findByAgentCode(s.servReqAgentCode)?.agentName,
                servReqDescription = s.servReqDescription,
                servReqDate = s.servReqDate,
                servReqAssignee = s.servReqAssignee,
                servReqAssignComments = s.servReqAssignComments,
                servReqStatus = s.servReqStatus,
                servReqUpdateDate = s.servReqUpdateDate,
                servReqUserAssignedTo = s.servReqUserAssignedTo?.userId,
                servReqUserAssignedToUserName = s.servReqUserAssignedTo?.username,
                servReqUserAssignedToName = s.servReqUserAssignedTo?.userFullName,
            )
            serviceRequests.add(req)
        }
        return ResultFactory.getSuccessResult(serviceRequests)
    }

    override fun findAgentServiceRequests(agentCode: Long): Result<Iterable<ServiceRequestView>> {
        val serviceRequests: ArrayList<ServiceRequestView> = ArrayList()
        for(s in serviceRequestsRepo.findByServReqAgentCode(agentCode)) {
            var req = ServiceRequestView(
                servReqId = s.servReqId,
                servReqType = s.servReqType,
                servReqUserRequesting = s.servReqUserRequesting?.userId,
                servReqUserRequestingName = s.servReqUserRequesting?.userFullName,
                servReqPolicyCode = s.servReqPolicyCode,
                //servReqPolicyNo = tqActivePoliciesRepository.findByActiveId(s.servReqPolicyCode)?.activePolicyNo,
                servReqAgentCode = s.servReqAgentCode,
                servReqAgentName = agentsRepo.findByAgentCode(s.servReqAgentCode)?.agentName,
                servReqDescription = s.servReqDescription,
                servReqDate = s.servReqDate,
                servReqAssignee = s.servReqAssignee,
                servReqAssignComments = s.servReqAssignComments,
                servReqStatus = s.servReqStatus,
                servReqUpdateDate = s.servReqUpdateDate,
                servReqUserAssignedTo = s.servReqUserAssignedTo?.userId,
                servReqUserAssignedToUserName = s.servReqUserAssignedTo?.username,
                servReqUserAssignedToName = s.servReqUserAssignedTo?.userFullName,
            )
            serviceRequests.add(req)
        }
        return ResultFactory.getSuccessResult(serviceRequests)
    }

    override fun findPolicyServiceRequests(policyCode: Long): Result<Iterable<ServiceRequestView>> {
        val serviceRequests: ArrayList<ServiceRequestView> = ArrayList()
        for(s in serviceRequestsRepo.findByServReqPolicyCode(policyCode)) {
            var req = ServiceRequestView(
                servReqId = s.servReqId,
                servReqType = s.servReqType,
                servReqUserRequesting = s.servReqUserRequesting?.userId,
                servReqUserRequestingName = s.servReqUserRequesting?.userFullName,
                servReqPolicyCode = s.servReqPolicyCode,
                //servReqPolicyNo = tqActivePoliciesRepository.findByActiveId(s.servReqPolicyCode)?.activePolicyNo,
                servReqAgentCode = s.servReqAgentCode,
                servReqAgentName = agentsRepo.findByAgentCode(s.servReqAgentCode)?.agentName,
                servReqDescription = s.servReqDescription,
                servReqDate = s.servReqDate,
                servReqAssignee = s.servReqAssignee,
                servReqAssignComments = s.servReqAssignComments,
                servReqStatus = s.servReqStatus,
                servReqUpdateDate = s.servReqUpdateDate,
                servReqUserAssignedTo = s.servReqUserAssignedTo?.userId,
                servReqUserAssignedToUserName = s.servReqUserAssignedTo?.username,
                servReqUserAssignedToName = s.servReqUserAssignedTo?.userFullName,
            )
            serviceRequests.add(req)
        }
        return ResultFactory.getSuccessResult(serviceRequests)
    }

    override fun findServiceRequestTypes(area: String): Result<Iterable<ServiceRequestTypesModel>> {
        return ResultFactory.getSuccessResult(serviceRequestTypesRepo.findByServReqTypeArea(serviceRequestAreasRepo.findByServReqAreaName(area)))
    }
    override fun deleteServiceRequest(serviceRequest: Long){
        serviceRequestsRepo.deleteById(serviceRequest)
    }
    override fun findAllServiceRequests(): Result<Iterable<ServiceRequestView>> {
        val serviceRequests: ArrayList<ServiceRequestView> = ArrayList()
        for(s in serviceRequestsRepo.findAll()){
            var req = ServiceRequestView(
                 servReqId = s.servReqId,
                 servReqType = s.servReqType,
                 servReqUserRequesting = s.servReqUserRequesting?.userId,
                 servReqUserRequestingName = s.servReqUserRequesting?.userFullName,
                 servReqPolicyCode = s.servReqPolicyCode,
                 //servReqPolicyNo = tqActivePoliciesRepository.findByActiveId(s.servReqPolicyCode)?.activePolicyNo,
                 servReqAgentCode = s.servReqAgentCode,
                 servReqAgentName = agentsRepo.findByAgentCode(s.servReqAgentCode)?.agentName,
                 servReqDescription = s.servReqDescription,
                 servReqDate = s.servReqDate,
                 servReqAssignee = s.servReqAssignee,
                 servReqAssignComments = s.servReqAssignComments,
                 servReqStatus = s.servReqStatus,
                 servReqUpdateDate = s.servReqUpdateDate,
                 servReqUserAssignedTo = s.servReqUserAssignedTo?.userId,
                 servReqUserAssignedToUserName = s.servReqUserAssignedTo?.username,
                 servReqUserAssignedToName = s.servReqUserAssignedTo?.userFullName,
            )
            serviceRequests.add(req)
        }
        return ResultFactory.getSuccessResult(serviceRequests)
    }

    override fun findUserNotifications(userCode: Long) : Result<Iterable<PortalNotificationsView>> {
        val notificationsL: ArrayList<PortalNotificationsView> = ArrayList()
        val user: UsersModel = usersRepo.findByUserId(userCode)
            .orElseThrow { Exception("User not found") }

        for(p in portalNotificationsRepo.findByNotificationUser(user)){
            var notification = PortalNotificationsView(
                 notificationCode = p.notificationCode,
                 notificationUser = p.notificationUser?.userId,
                 notificationTitle = p.notificationTitle,
                 notificationBody = p.notificationBody,
                 notificationDate = p.notificationDate,
                 notificationStatus = p.notificationStatus,
            )
            notificationsL.add(notification)
        }
        return ResultFactory.getSuccessResult(notificationsL)
    }

    override fun findActiveUserNotifications(userCode: Long) : Result<Iterable<PortalNotificationsView>> {

        val notificationsL: ArrayList<PortalNotificationsView> = ArrayList()
        val user: UsersModel = usersRepo.findByUserId(userCode)
            .orElseThrow { Exception("User not found") }

        for(p in portalNotificationsRepo.findByNotificationUserAndNotificationStatus(user, "ACTIVE")){
            var notification = PortalNotificationsView(
                notificationCode = p.notificationCode,
                notificationUser = p.notificationUser?.userId,
                notificationTitle = p.notificationTitle,
                notificationBody = p.notificationBody,
                notificationDate = p.notificationDate,
                notificationStatus = p.notificationStatus,
            )
            notificationsL.add(notification)
        }
        return ResultFactory.getSuccessResult(notificationsL)

    }

    override fun deactivateNotification(notificationCode: Long){
        val note = portalNotificationsRepo.findById(notificationCode)
            .orElseThrow { Exception("Notification not found") }

        note.notificationStatus = "INACTIVE"
        portalNotificationsRepo.save(note)

    }

    override fun saveUserNotification(notificationCode: Long?,
                                      userCode: Long,
                                      title: String?,
                                      body: String?,
                                      status: String?){
        //Fetch user Details
        val user: UsersModel = usersRepo.findByUserId(userCode)
            .orElseThrow { Exception("User not found") }

        val notification = PortalNotificationsModel(
            notificationCode = notificationCode,
            notificationUser = user,
            notificationTitle = title,
            notificationBody = body,
            notificationDate = Calendar.getInstance().time,
            notificationStatus = "ACTIVE"
        )
        portalNotificationsRepo.save(notification)
    }

}