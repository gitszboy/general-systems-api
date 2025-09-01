package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.BinderGroupsModel
import com.ag.generalsystemsapi.api.model.SubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface BinderGroupsRepository : JpaRepository<BinderGroupsModel, Long> {

    fun findByBindGroupSubClassCodeAndBindGroupWebDefault(subclass: SubClassesModel?, default: String) : Iterable<BinderGroupsModel>
}