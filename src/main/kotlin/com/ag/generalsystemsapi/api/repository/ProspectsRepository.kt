package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProspectsModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProspectsRepository : JpaRepository<ProspectsModel, Long> {

    fun findByProspectCode(prospectCode: Long?) : Optional<ProspectsModel>

    fun existsByProspectIdNumber(prospectIdNumber: String?) : Boolean
    fun findByProspectIdNumber(prospectIdNumber: String?) : Optional<ProspectsModel>
    fun findByProspectEmail(prospectEmail: String) : Optional<ProspectsModel>
}