package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.MpesaPaymentsModel
import org.springframework.data.jpa.repository.JpaRepository

interface MpesaPaymentsRepository : JpaRepository<MpesaPaymentsModel, Long> {

    fun findByMpesaPayCheckReqId(mpesaPayCheckReqId: String?) : Iterable<MpesaPaymentsModel>
}