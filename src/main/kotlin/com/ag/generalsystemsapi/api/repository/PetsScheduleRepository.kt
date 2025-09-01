package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.PetsScheduleModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.thirdparty.model.TpPetsScheduleModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PetsScheduleRepository : JpaRepository<PetsScheduleModel, Long> {
    fun findByPetSchIpuCode(petSchIpuCode: PolicyRisksModel?) : Optional<PetsScheduleModel>
}