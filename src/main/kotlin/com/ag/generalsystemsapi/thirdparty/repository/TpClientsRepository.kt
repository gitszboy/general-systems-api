package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpClientsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface TpClientsRepository : JpaRepository<TpClientsModel, Long> {

    @Transactional
    @Procedure(name = "save_client")
    fun saveClient(
        @Param("v_clnt_code") clntCode: Long?,
        @Param("v_title") clntTitle: String?,
        @Param("v_surname") clntSurname: String?,
        @Param("v_other_names") clntOtherNames: String?,
        @Param("v_dob") clntDob: Date?,
        @Param("v_gender") clntGender: String?,
        @Param("v_marital_status") clntMaritalStatus: String?,
        @Param("v_email") clntEmail: String?,
        @Param("v_telephone") clntTelephone: String?,
        @Param("v_sms") clntSms: String?,
        @Param("v_id_no") clntIdNumber: String?,
        @Param("v_pin_no") clntPinNo: String?,
        @Param("v_occupation") clntOccupation: String?,
        @Param("v_country") clntCountry: String?,
        @Param("v_town") clntTown: String?,
        @Param("v_bank_name") clntBankName: String?,
        @Param("v_bank_branch") clntBankBranch: String?,
        @Param("v_bank_acc_no") clntBankAccNo: String?,
        @Param("v_effective_date") clntEffectiveDate: Date?,
        @Param("v_client_name") clntClientName: String?
    ): Map<String, Any?>

    fun findByClientIdNumber(clientIdNumber: String?) : Iterable<TpClientsModel>
}