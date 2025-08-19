package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UsersRepository : JpaRepository<UsersModel, Long> {

    fun findByUsername(username: String?) : Optional<UsersModel>

    fun existsByUsername(username: String): Boolean

    fun existsByUserEmail(username: String): Boolean

    fun findByUserId(userId: Long?) : Optional<UsersModel>

    fun findByUserType(userType: String) : Iterable<UsersModel>

    fun findByUserEmail(username: String): UsersModel?
}