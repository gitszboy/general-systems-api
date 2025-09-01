package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SubClassCoverTypesModel
import com.ag.generalsystemsapi.api.model.SubClassesModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SubClassCoverTypesRepository : JpaRepository<SubClassCoverTypesModel, Long> {

    fun findByScCoverSubClassAndScCoverDefault(subClass: SubClassesModel?, default: String) : Optional<SubClassCoverTypesModel>
}