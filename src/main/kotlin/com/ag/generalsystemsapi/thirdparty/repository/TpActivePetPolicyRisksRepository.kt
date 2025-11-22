package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActivePetPolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface TpActivePetPolicyRisksRepository : JpaRepository<TpActivePetPolicyRisksModel, Long> {

    @Transactional
    @Procedure(name = "save_claim")
    fun saveClaim(
        @Param("v_ipu_code") riskCode: Long?,
        @Param("v_pol_batch_no") polBatchNo: Long?,
        @Param("v_l_date") lossDate: String?,
        @Param("v_clm_report_date") reportDate: String?,
        @Param("v_cas_code") casCode: Long?,
        @Param("v_cas_sht_desc") casShtDesc: String?,
        @Param("v_coin_pay_full") coinPayFull: String?,
        @Param("v_loss_desc") lossDesc: String?,
        @Param("v_doc_ref") docRef: String?,
        @Param("v_user") user: String?,
        @Param("v_serial") serial: Long?,
        @Param("v_peril_lvl") perilLvl: String?,
        @Param("v_peril_code") perilCode: Long?,
        @Param("v_peril_amnt") perilAmount: Double?,
        @Param("v_no_ri") noRI: String?,
        @Param("v_self_as_clmant") selfClaimant: String?,
        @Param("v_liability_admtd") liabilityAdmitted: String?,
        @Param("v_claim_not_date") claimNotDate: Date?,
        @Param("v_next_rev_date") nextRevDate: String?,
        @Param("v_eve_code") eveCode: Long?,
        @Param("v_cata_code") cataCode: Long?,
        @Param("v_ref_no") refNo: String?,
        @Param("v_serial_no") serialNo: String?,
        @Param("v_peril_pay_type") perilPayType: String?,
        @Param("v_basic_sal") basicSal: Double?,
        @Param("v_avg_earnings") avgEarnings: Double?,
        @Param("v_offduty_wef_dt") offDutyWefdt: Date?,
        @Param("v_offduty_wet_dt") offDutyWetdt: Date?,
        @Param("v_tp") tp: String?,
        @Param("v_cmb_priority_lvl") priorityLvl: String?,
        @Param("v_cmb_location") location: String?,
        @Param("v_commmode") commmode: String?,
        @Param("v_clmnt_liab_adm") clmtLiabAdm: String?,
        @Param("v_cmb_veh_onmotion") vehOnMotion: String?,
        @Param("v_cmb_tentative_loss_date") tentativeLossDate: String?,
        @Param("v_claimnextuserreview") claimnextuserreview: String?
    ): Map<String, Any?>

    @Transactional
    @Procedure(name = "save_claim_perils")
    fun saveClaimPerils(
        @Param("v_add_edit") addEdit: String?,
        @Param("v_cpt_code") cptCode: Long?,
        @Param("v_cpt_peril_code") cptPerilCode: Long?,
        @Param("v_cpt_peril_level") cptPerilLevel: String?,
        @Param("v_cpt_peril") cptPeril: String?,
        @Param("v_cpt_peril_amt") cptPerilAmt: Double?,
        @Param("v_cpt_peril_estmate") cptPerilEstmate: Double?,
        @Param("v_cpt_third_party") cptThirdParty: String?,
        @Param("v_cpv_cld_code") cpvCldCode: String?,
        @Param("v_cpt_grp_code") cptGrpCode: Long?,
        @Param("v_cpt_communication_mode") cptCommunicationMode: String?,
        @Param("v_cpt_payment_mode") cptPaymentMode: String?,
        @Param("v_prp_code") prpCode: Long?,
        @Param("v_cpt_liability_addmission") cptLiabilityAddmission: String?,
        @Param("v_cpt_liab_addm_date") cptLiabAddmDate: Date?,
        @Param("v_cpt_main_peril_code") cptMainPerilCode: Long?,
        @Param("v_cpt_peril_rate") cptPerilRate: Long?
    ): Map<String, Any?>

    fun findByPolicyRiskPolicyBatchNo(policyRiskPolicyBatchNo: Long?) : Iterable<TpActivePetPolicyRisksModel>

    fun findByPolicyRiskPropertyID(policyRiskPropertyID: String?) : TpActivePetPolicyRisksModel?
}