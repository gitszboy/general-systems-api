package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.AccountTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface AccountTypesRepository : JpaRepository<AccountTypesModel, Long> {
    fun findByAccountTypeCode(accountTypeCode : Long?) : AccountTypesModel?
}