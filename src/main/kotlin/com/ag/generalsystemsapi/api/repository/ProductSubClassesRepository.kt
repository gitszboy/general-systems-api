package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProductSubClassesModel
import com.ag.generalsystemsapi.api.model.ProductsModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProductSubClassesRepository : JpaRepository<ProductSubClassesModel, Long> {

    fun findByProdSubClassProdCodeAndProdSubClassDefault(product: ProductsModel, default: String) : Optional<ProductSubClassesModel>
}