package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "active_pet_policies_view")
@NamedStoredProcedureQueries(
    NamedStoredProcedureQuery(
        name = "save_policy",
        procedureName = "TQ_GIS.CREATE_PET_POLICY",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_idno", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_pinno", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_mobno", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_firstname", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_middlename", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_lastname", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_gender", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_physical_addrs", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_postal_addrs", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_email_addrs", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_type", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_brn_sht_desc", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_from", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_to", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_cur_symbol", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_pymt_mode_sht", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_old_pol_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_pro_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_gen_pol_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_batch_no", type = Long::class)
        )
    ),
    NamedStoredProcedureQuery(
        name = "save_policy_risk",
        procedureName = "TQ_GIS.CREATE_PET_POLICY_RISK",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_batch_no", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_type", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_bind_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_risk_id", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_risk_desc", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_scl_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_sum_assured", type = Double::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_from", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_covt_to", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_new_ipu_code", type = Long::class)
        )
    ),
    NamedStoredProcedureQuery(
        name = "save_policy_risk_sch",
        procedureName = "TQ_GIS.CREATE_PET_POLICY_RISK_SCH",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_ipu_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_animal", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_breed", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_gender", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_dob", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_weight", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_microchip", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_microchip_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_sterilized", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_vaccinated", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_med_conditions", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_surgeries", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_medications", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_illness", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_injure_others", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_commercial", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_comments", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_vet_name", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clinic_name", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clinic_tel", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clinic_town", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_new_ptvs_code", type = Long::class)
        )
    )
)
class TpActivePetPoliciesModel (
    @Id
    @Column(name = "clnt_code", nullable = false)
    var policyClientCode: Long,

    @Column(name = "clnt_name", nullable = false)
    var policyClientName: String? = null,

    @Column(name = "clnt_other_names", nullable = false)
    var policyClientOtherNames: String? = null,

    @Column(name = "clnt_pin", nullable = false)
    var policyClientPIN: String? = null,

    @Column(name = "clnt_id_reg_no", nullable = true)
    var policyClientIDNumber: String? = null,

    @Column(name = "clnt_dob", nullable = true)
    var policyClientDOB: Date? = null,

    @Column(name = "clnt_gender", nullable = true)
    var policyClientGender: String?,

    @Column(name = "clnt_tel", nullable = true)
    var policyClientTelephone: String? = null,

    @Column(name = "clnt_physical_addrs", nullable = true)
    var policyClientPhyAddress: String? = null,

    @Column(name = "clnt_email_addrs", nullable = true)
    var policyClientEmailAddress: String? = null,

    @Column(name = "clnt_type", nullable = true)
    var policyClientType: String? = null,

    @Column(name = "pol_batch_no", nullable = true)
    var policyBatchNo: Long? = null,

    @Column(name = "pol_policy_no", nullable = true)
    var policyNumber: String? = null,

    @Column(name = "pol_pro_code", nullable = true)
    var policyProductCode: Long? = null,

    @Column(name = "pol_policy_cover_from", nullable = true)
    var policyCoverFromDate: Date? = null,

    @Column(name = "pol_policy_cover_to", nullable = true)
    var policyCoverToDate: Date? = null,

    @Column(name = "pol_total_sum_insured", nullable = true)
    var policyTotalSumInsured: Double? = null,

    @Column(name = "pol_nett_premium", nullable = true)
    var policyTotalNetPremium: Double? = null,

    @Column(name = "pol_freq_of_payment", nullable = true)
    var policyFrequencyOfPayment: String? = null,

    @Column(name = "pol_policy_status", nullable = true)
    var policyStatus: String? = null,

    @Column(name = "pol_current_status", nullable = true)
    var policyCurrentStatus: String? = null,
)