package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProductsModel
import org.springframework.data.jpa.repository.JpaRepository

interface ProductsRepository : JpaRepository<ProductsModel, Long> {

    fun findByProductCode(productCode: Long) : ProductsModel?
}