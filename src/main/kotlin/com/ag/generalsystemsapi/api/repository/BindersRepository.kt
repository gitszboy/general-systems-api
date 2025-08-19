package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.BindersModel
import org.springframework.data.jpa.repository.JpaRepository

interface BindersRepository : JpaRepository<BindersModel, Long> {

    fun findByBindCode(bindCode: Long?) : BindersModel?
}