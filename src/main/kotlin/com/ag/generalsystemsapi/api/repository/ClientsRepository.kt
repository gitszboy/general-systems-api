package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClientsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ClientsRepository: JpaRepository<ClientsModel, Long> {

    @Query(value = "SELECT count(r) " +
            "FROM ClientsModel r " +
            "WHERE r.clientName = (:surname) " +
            "AND r.clientTelephone = (:telephone) " +
            "AND r.clientIdNumber = (:id) ")
    fun countDuplicateIndividualClients(surname: String?, telephone: String?, id: String?) : Long?

    @Query(value = "SELECT count(r) " +
            "FROM ClientsModel r " +
            "WHERE r.clientName = (:name) " +
            "AND r.clientPIN = (:pin) ")
    fun countDuplicateCompanies(name: String?, pin: String?) : Long?

    @Query(value = "SELECT r " +
            "FROM ClientsModel r " +
            "WHERE r.clientName = (:surname) " +
            "AND r.clientTelephone = (:telephone) " +
            "AND r.clientIdNumber = (:id) ")
    fun findDuplicateIndividualClient(surname: String?, telephone: String?, id: String?) : ClientsModel?

    @Query(value = "SELECT r " +
            "FROM ClientsModel r " +
            "WHERE r.clientName = (:name) " +
            "AND r.clientPIN = (:pin) ")
    fun findDuplicateCompanies(name: String?, pin: String?) : ClientsModel?

    @Query(value = "SELECT r " +
            "FROM ClientsModel r " +
            "WHERE IFNULL(r.clientStage,'P') = (:clientStage) ")
    fun findAllClients(clientStage: String) : Iterable<ClientsModel>

    fun findByClientIdNumber(idNumber: String?) : ClientsModel?

    fun findByClientIdNumberAndClientThirdPartySystem(idNumber: String?, tpSystem: String) : ClientsModel?

    fun findByClientCode(clientCode: Long) : Optional<ClientsModel>

}