package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PerilsModel
import org.springframework.data.jpa.repository.JpaRepository

interface PerilsRepository : JpaRepository<PerilsModel, Long> {
}