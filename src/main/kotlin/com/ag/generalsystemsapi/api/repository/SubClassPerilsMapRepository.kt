package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.BindersModel
import com.ag.generalsystemsapi.api.model.SubClassPerilsMapModel
import com.ag.generalsystemsapi.api.model.SubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface SubClassPerilsMapRepository : JpaRepository<SubClassPerilsMapModel, Long> {

    fun findBySclPerilMapSubClassAndSclPerilMapBinder(subclass: SubClassesModel, binder: BindersModel) : Iterable<SubClassPerilsMapModel>
}