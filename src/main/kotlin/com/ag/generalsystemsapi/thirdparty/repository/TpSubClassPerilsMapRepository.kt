package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSubClassPerilsMapModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSubClassPerilsMapRepository : JpaRepository<TpSubClassPerilsMapModel, Long> {
    fun findBySclPerilMapBinder(binderCode: Long) : Iterable<TpSubClassPerilsMapModel>
}