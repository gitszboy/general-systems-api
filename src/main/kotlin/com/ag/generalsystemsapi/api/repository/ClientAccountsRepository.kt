package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClientAccountsModel
import com.ag.generalsystemsapi.api.model.ClientsModel
import com.ag.generalsystemsapi.api.model.UsersModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClientAccountsRepository : JpaRepository<ClientAccountsModel, Long> {
    fun findByClntAccUserId(clntAccUserId: UsersModel?) : ClientAccountsModel

    fun findByClntAccClientCode(clntAccClientCode: ClientsModel?) : ClientAccountsModel
}