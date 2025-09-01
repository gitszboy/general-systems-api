package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.CoverTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface CoverTypesRepository : JpaRepository<CoverTypesModel, Long> {
    fun findByCoverCode(coverTypes: Long?) : CoverTypesModel?
}