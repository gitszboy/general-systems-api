package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpClassPerilsModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpClassPerilsRepository : JpaRepository<TpClassPerilsModel, Long> {

    fun findByClPerilClassCode(classCode: Long) : Iterable<TpClassPerilsModel>
}