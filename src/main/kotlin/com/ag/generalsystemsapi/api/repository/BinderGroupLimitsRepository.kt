package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.BinderGroupLimitsModel
import com.ag.generalsystemsapi.api.model.BinderGroupsModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BinderGroupLimitsRepository : JpaRepository<BinderGroupLimitsModel, Long> {

    fun findByBindGroupLimitGroup(bindLimitGroup: BinderGroupsModel) : Iterable<BinderGroupLimitsModel>
    fun findByBindGroupLimitGroupAndBindGroupLimitAmount(bindLimitGroup: BinderGroupsModel, limitAmt: Double?) : Optional<BinderGroupLimitsModel>
}