package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "active_pet_policy_risks_view")
@NamedStoredProcedureQueries(
    NamedStoredProcedureQuery(
        name = "save_claim",
        procedureName = "GIN_STP_CLAIMS_PKG.create_new_claim_web",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_ipu_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_pol_batch_no", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_l_date", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clm_report_date", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cas_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cas_sht_desc", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_coin_pay_full", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_loss_desc", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_doc_ref", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_user", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_serial", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_peril_lvl", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_peril_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_peril_amnt", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_no_ri", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_self_as_clmant", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_liability_admtd", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_claim_not_date", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_next_rev_date", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_eve_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cata_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_ref_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_serial_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_peril_pay_type", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_basic_sal", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_avg_earnings", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_offduty_wef_dt", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_offduty_wet_dt", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_tp", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cmb_priority_lvl", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cmb_location", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_clm_rev_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_ggt_trans_no", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_paymode", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_commmode", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clmnt_liab_adm", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cmb_veh_onmotion", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cmb_tentative_loss_date", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_claimnextuserreview", type = String::class)
        )
    ),
    NamedStoredProcedureQuery(
        name = "save_claim_perils",
        procedureName = "GIS_SETUPS_PKG.create_temp_perils_web",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_add_edit", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril_level", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril_amt", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril_estmate", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_third_party", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpv_cld_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_grp_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_communication_mode", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_payment_mode", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_prp_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_liability_addmission", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_liab_addm_date", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_main_peril_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cpt_peril_rate", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_out_cpt_grp_code", type = Long::class)
        )
    )
)
class TpActivePetPolicyRisksModel (
    @Id
    @Column(name = "ipu_code", nullable = false)
    var policyRiskCode: Long? = null,

    @Column(name = "clnt_code", nullable = false)
    var policyRiskClientCode: Long,

    @Column(name = "pol_batch_no", nullable = false)
    var policyRiskPolicyBatchNo: Long? = null,

    @Column(name = "ipu_property_id", nullable = false)
    var policyRiskPropertyID: String? = null,

    @Column(name = "ipu_item_desc", nullable = true)
    var policyRiskItemDesc: String? = null,

    @Column(name = "ipu_sec_scl_code", nullable = true)
    var policyRiskSubClassCode: Long? = null,

    @Column(name = "ipu_covt_sht_desc", nullable = true)
    var policyRiskCoverType: String?,

    @Column(name = "ipu_prorata", nullable = true)
    var policyRiskProrata: String? = null,

    @Column(name = "ipu_wef", nullable = true)
    var policyRiskWef: Date? = null,

    @Column(name = "ipu_wet", nullable = true)
    var policyRiskWet: Date? = null,

    @Column(name = "ipu_bind_code", nullable = true)
    var policyRiskBindCode: Long? = null,

    @Column(name = "bind_name", nullable = true)
    var policyRiskBindName: String? = null,

    @Column(name = "ipu_value", nullable = true)
    var policyRiskValue: Double? = null,

)