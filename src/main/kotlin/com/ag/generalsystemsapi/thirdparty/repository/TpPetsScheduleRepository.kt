package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpPetsScheduleModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpPetsScheduleRepository : JpaRepository<TpPetsScheduleModel, Long> {
    fun findByPetSchIpuCode(petSchIpuCode: Long) : Iterable<TpPetsScheduleModel>
}