package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.OrganizationModel
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository : JpaRepository<OrganizationModel, Long> {

    fun findByOrgCode(orgCode: Long?) : OrganizationModel

    fun existsByOrgCode(orgCode: Long?) : Boolean

    fun existsByOrgDefault(default: Boolean) : Boolean

    fun findByOrgDefault(default: Boolean) : OrganizationModel
}