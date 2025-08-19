package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.OrganizationModel
import com.ag.generalsystemsapi.api.model.payload.OrganizationRequest
import com.ag.generalsystemsapi.api.util.Result

interface IOrganizationService {

    fun findOrganizations() : Result<Iterable<OrganizationModel>>

    fun saveOrganization(organization: OrganizationRequest)
}