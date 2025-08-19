package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProspectPetsModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClientPetsRepository : JpaRepository<ProspectPetsModel, Long> {
}