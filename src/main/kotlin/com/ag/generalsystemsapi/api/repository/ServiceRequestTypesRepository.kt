package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ServiceRequestAreasModel
import com.ag.generalsystemsapi.api.model.ServiceRequestTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRequestTypesRepository : JpaRepository<ServiceRequestTypesModel, Long> {

    fun findByServReqTypeId(servReqTypeId : Long?) : ServiceRequestTypesModel

    fun findByServReqTypeArea(serviceArea: ServiceRequestAreasModel) : Iterable<ServiceRequestTypesModel>
}