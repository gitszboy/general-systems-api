package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActiveBanksModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpActiveBanksRepository : JpaRepository<TpActiveBanksModel, Long> {

    fun findByBankBranchCode(bankBranchCode: Long) : TpActiveBanksModel
}