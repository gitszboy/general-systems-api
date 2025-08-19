package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.payload.OrganizationRequest
import com.ag.generalsystemsapi.api.repository.OrganizationRepository
import com.ag.generalsystemsapi.api.service.IOrganizationService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrganizationServiceImpl : IOrganizationService {

    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    override fun findOrganizations() : Result<Iterable<OrganizationModel>> {
        return ResultFactory.getSuccessResult(organizationRepository.findAll())
    }

    override fun saveOrganization(organization: OrganizationRequest){
        val org = OrganizationModel(
            orgCode = organization.orgCode,
            orgName = organization.orgName,
            orgPostalAddress = organization.orgPostalAddress,
            orgPhysicalAddress = organization.orgPhysicalAddress,
            orgPrimaryTelephone = organization.orgPrimaryTelephone,
            orgSecondaryTelephone = organization.orgSecondaryTelephone,
            orgMobileTelephone = organization.orgMobileTelephone,
            orgPin = organization.orgPin,
            orgMoto = organization.orgMoto,
            orgWebsite = organization.orgWebsite,
            orgSchemeName = organization.orgSchemeName,
            orgStatus = organization.orgStatus,
            orgActivationDate = organization.orgActivationDate,
            orgDeactivationDate = organization.orgDeactivationDate,
        )
        organizationRepository.save(org)
    }
}