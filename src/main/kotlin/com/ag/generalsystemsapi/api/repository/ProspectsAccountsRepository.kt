package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ProspectsAccountsModel
import com.ag.generalsystemsapi.api.model.ProspectsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface ProspectsAccountsRepository : JpaRepository<ProspectsAccountsModel, Long> {

    fun findByProsaAccUserId(prosaAccUserId: UsersModel) : ProspectsAccountsModel?

    fun findByProsaAccProspectCode(prospect: ProspectsModel) : ProspectsAccountsModel?
}