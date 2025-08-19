package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SystemRolesModel
import com.ag.generalsystemsapi.api.model.UserRolesModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRolesRepository : JpaRepository<UserRolesModel, Long> {
    fun findByUserRolesUserId(user: Optional<UsersModel>) : Iterable<UserRolesModel>
    fun existsByUserRolesUserIdAndUserRolesRlId(user: UsersModel, role: SystemRolesModel): Boolean

    fun findByUserRolesUserIdAndUserRolesRlId(user: UsersModel, role: SystemRolesModel): UserRolesModel
}