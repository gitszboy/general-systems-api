package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.SectionsModel
import org.springframework.data.jpa.repository.JpaRepository

interface SectionsRepository : JpaRepository<SectionsModel, Long> {
}