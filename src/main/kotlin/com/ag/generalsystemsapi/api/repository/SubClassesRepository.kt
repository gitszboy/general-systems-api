package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SubClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface SubClassesRepository : JpaRepository<SubClassesModel, Long> {

    fun findBySubClassCode(subClassCode: Long?) : SubClassesModel?
}