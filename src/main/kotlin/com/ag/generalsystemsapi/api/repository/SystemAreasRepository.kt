package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SystemAreasModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SystemAreasRepository : JpaRepository<SystemAreasModel, Long> {
    fun findBySysAreaId(sysAreaId: Long?) : Optional<SystemAreasModel>
}
