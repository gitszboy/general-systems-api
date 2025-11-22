package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProspectPetsModel
import com.ag.generalsystemsapi.api.model.ProspectsModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClientPetsRepository : JpaRepository<ProspectPetsModel, Long> {
    fun findByProspectPetProspect(prospect: ProspectsModel?) : Iterable<ProspectPetsModel>
}