package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpPremiumRatesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpPremiumRatesRepository : JpaRepository<TpPremiumRatesModel, Long> {
    fun findByRateSubClassCode(sclCode: Long) : Iterable<TpPremiumRatesModel>
}