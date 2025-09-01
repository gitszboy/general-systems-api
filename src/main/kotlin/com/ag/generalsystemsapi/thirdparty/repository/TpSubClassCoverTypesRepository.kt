package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSubClassCoverTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSubClassCoverTypesRepository : JpaRepository<TpSubClassCoverTypesModel, Long> {

    fun findByScCoverSubClass(scCoverSubClass: Long) : Iterable<TpSubClassCoverTypesModel>
}