package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.BinderLimitsModel
import com.ag.generalsystemsapi.api.model.BindersModel
import org.springframework.data.jpa.repository.JpaRepository

interface BinderLimitsRepository : JpaRepository<BinderLimitsModel, Long> {
    fun findByBindLimitBinders(bindCode: BindersModel?) : Iterable<BinderLimitsModel>
}