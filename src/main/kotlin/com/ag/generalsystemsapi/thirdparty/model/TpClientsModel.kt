package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "lms_proposers")
@NamedStoredProcedureQueries(
    NamedStoredProcedureQuery(
        name = "save_client",
        procedureName = "INTERGRATION_LAYER_PKG.create_new_client_complete",
        parameters = arrayOf(
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_clnt_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_title", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_surname", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_other_names", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_dob", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_gender", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_marital_status", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_email", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_telephone", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_sms", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_id_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_pin_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_occupation", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_country", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_town", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_bank_name", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_bank_branch", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_bank_acc_no", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_effective_date", type = Date::class),
            StoredProcedureParameter(mode = ParameterMode.IN, name = "v_client_name", type = String::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_client_code", type = Long::class),
            StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_success", type = String::class)
        )
    )
)
class TpClientsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prp_code", nullable = false)
    var clientCode: Long,

    @Column(name = "prp_surname", nullable = true)
    var clientName: String?,

    @Column(name = "prp_email", nullable = true)
    var clientEmail: String?,

    @Column(name = "prp_dob", nullable = true)
    var clientDateOfBirth: Date?,

    @Column(name = "prp_other_names", nullable = true)
    var clientOtherNames: String? = null,

    @Column(name = "prp_sex", nullable = true)
    var clientGender: String? = null,

    @Column(name = "prp_business", nullable = true)
    var clientOccupation: String? = null,

    @Column(name = "prp_employer", nullable = true)
    var clientEmployer: String? = null,

    @Column(name = "prp_physical_addr", nullable = true)
    var clientPhysicalAddress: String? = null,

    @Column(name = "prp_pin", nullable = true)
    var clientPIN: String? = null,

    @Column(name = "prp_id_reg_no", nullable = true)
    var clientIdNumber: String? = null,

    )