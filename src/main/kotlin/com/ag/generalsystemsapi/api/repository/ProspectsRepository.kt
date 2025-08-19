package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProspectsModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProspectsRepository : JpaRepository<ProspectsModel, Long> {

    fun findByProspectCode(prospectCode: Long?) : Optional<ProspectsModel>
}