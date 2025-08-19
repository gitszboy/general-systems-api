package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpProductsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TpProductsRepository : JpaRepository<TpProductsModel, Long> {
    fun findByProductCode(productCode: Long) : TpProductsModel
}