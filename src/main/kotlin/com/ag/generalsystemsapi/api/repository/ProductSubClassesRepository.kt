package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProductSubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface ProductSubClassesRepository : JpaRepository<ProductSubClassesModel, Long> {
}