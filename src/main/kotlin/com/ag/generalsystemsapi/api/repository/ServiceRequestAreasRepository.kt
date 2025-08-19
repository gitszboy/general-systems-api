package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ServiceRequestAreasModel
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceRequestAreasRepository : JpaRepository<ServiceRequestAreasModel, Long> {

    fun findByServReqAreaName(name: String) : ServiceRequestAreasModel
}