package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SystemAreasModel
import com.ag.generalsystemsapi.api.model.SystemRoleAreasModel
import com.ag.generalsystemsapi.api.model.SystemRolesModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SystemRoleAreasRepository : JpaRepository<SystemRoleAreasModel, Long> {
    fun findByRoleAreaId(roleAreaId: Long) : Optional<SystemRoleAreasModel>
    fun findByRoleAreaRole(roleAreaRole: Optional<SystemRolesModel>) : Iterable<SystemRoleAreasModel>
    fun existsByRoleAreaRoleAndRoleAreaSysArea(role: SystemRolesModel, sysArea: SystemAreasModel): Boolean
    fun findByRoleAreaRoleAndRoleAreaSysArea(role: SystemRolesModel, sysArea: SystemAreasModel): SystemRoleAreasModel
    fun findByRoleAreaRole(role: SystemRolesModel) : Iterable<SystemRoleAreasModel>
}