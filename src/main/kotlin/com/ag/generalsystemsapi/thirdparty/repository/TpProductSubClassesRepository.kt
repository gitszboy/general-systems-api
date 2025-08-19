package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpProductSubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpProductSubClassesRepository : JpaRepository<TpProductSubClassesModel, Long> {

    fun findByProdSubClassSubclassCode(prodSubClassSubclassCode: Long) : Iterable<TpProductSubClassesModel>
}