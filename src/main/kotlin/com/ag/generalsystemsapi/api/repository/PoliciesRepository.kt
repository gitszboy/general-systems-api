package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PoliciesModel
import org.springframework.data.jpa.repository.JpaRepository

interface PoliciesRepository : JpaRepository<PoliciesModel, Long> {
}