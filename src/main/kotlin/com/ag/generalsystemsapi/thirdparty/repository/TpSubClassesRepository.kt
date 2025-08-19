package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSubClassesRepository : JpaRepository<TpSubClassesModel, Long> {

    fun findBySubClassClassCode(subClassClassCode: Long) : Iterable<TpSubClassesModel>
}