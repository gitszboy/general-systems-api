package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActivePetPoliciesModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface TpActivePetPoliciesRepository : JpaRepository<TpActivePetPoliciesModel, Long> {
    fun findByPolicyBatchNo(policyBatchNo: Long) : Optional<TpActivePetPoliciesModel>
    @Transactional
    @Procedure(name = "save_policy")
    fun savePolicy(
        @Param("v_clnt_idno") clientId: String?,
        @Param("v_clnt_pinno") clientPin: String?,
        @Param("v_clnt_mobno") clientMobileNo: String?,
        @Param("v_clnt_firstname") clientFirstName: String?,
        @Param("v_clnt_middlename") clientMiddleName: String?,
        @Param("v_clnt_lastname") clientLastName: String?,
        @Param("v_clnt_gender") clientGender: String?,
        @Param("v_clnt_physical_addrs") clientPhyAddr: String?,
        @Param("v_clnt_postal_addrs") clientPostalAddr: String?,
        @Param("v_clnt_email_addrs") clientEmailAddr: String?,
        @Param("v_clnt_type") clientType: String?,
        @Param("v_brn_sht_desc") branchShtDesc: String?,
        @Param("v_covt_from") coverFrom: String?,
        @Param("v_covt_to") coverTo: String?,
        @Param("v_cur_symbol") currSymbol: String?,
        @Param("v_pymt_mode_sht") paymentMode: String?,
        @Param("v_old_pol_no") oldPolNo: String?,
        @Param("v_pro_code") productCode: Long?
    ): Map<String, Any?>

    @Transactional
    @Procedure(name = "save_policy_risk")
    fun savePolicyRisk(
        @Param("v_batch_no") policyBatchNo: Long?,
        @Param("v_covt_code") coverTypeCode: Long?,
        @Param("v_covt_type") coverType: String?,
        @Param("v_bind_code") binderCode: Long?,
        @Param("v_risk_id") riskId: String?,
        @Param("v_risk_desc") riskDesc: String?,
        @Param("v_scl_code") subClassCode: Long?,
        @Param("v_sum_assured") sumAssured: Double?,
        @Param("v_covt_from") coverFrom: String?,
        @Param("v_covt_to") coverTo: String?
    ): Map<String, Any?>

    @Transactional
    @Procedure(name = "save_policy_risk_sch")
    fun savePolicyRiskSchedule(
        @Param("v_ipu_code") ipuCode: Long?,
        @Param("v_animal") animal: String?,
        @Param("v_breed") breed: String?,
        @Param("v_gender") gender: String?,
        @Param("v_dob") dob: String?,
        @Param("v_weight") weight: String?,
        @Param("v_microchip") microchip: String?,
        @Param("v_microchip_no") microchipNo: String?,
        @Param("v_sterilized") sterilized: String?,
        @Param("v_vaccinated") vaccinated: String?,
        @Param("v_med_conditions") medConditions: String?,
        @Param("v_surgeries") surgeries: String?,
        @Param("v_medications") medications: String?,
        @Param("v_illness") illness: String?,
        @Param("v_injure_others") injureOthers: String?,
        @Param("v_commercial") commercial: String?,
        @Param("v_comments") comments: String?,
        @Param("v_vet_name") vetName: String?,
        @Param("v_clinic_name") clinicName: String?,
        @Param("v_clinic_tel") clinicTel: String?,
        @Param("v_clinic_town") clinicTown: String?,
    ): Map<String, Any?>
}