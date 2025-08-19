package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SystemRolesModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SystemRolesRepository : JpaRepository<SystemRolesModel, Long> {

    fun findByRoleDefault(trueValue: Boolean) : Iterable<SystemRolesModel>

    fun findByRoleId(roleId: Long?) : Optional<SystemRolesModel>

    fun findByRoleName(roleName: String?) : Optional<SystemRolesModel>
}