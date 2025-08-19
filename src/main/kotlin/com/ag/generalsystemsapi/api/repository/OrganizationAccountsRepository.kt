package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.OrganizationAccountsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationAccountsRepository : JpaRepository<OrganizationAccountsModel, Long> {

    fun findByOrgAccUserId(orgAccUserId: UsersModel?) : OrganizationAccountsModel?
}