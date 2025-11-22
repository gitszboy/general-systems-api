package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SubClassCoverTypesSectionsModel
import org.springframework.data.jpa.repository.JpaRepository

interface SubClassCoverTypesSectionsRepository : JpaRepository<SubClassCoverTypesSectionsModel, Long> {
}