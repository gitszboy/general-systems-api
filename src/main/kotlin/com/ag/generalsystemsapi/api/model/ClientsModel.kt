package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "clients")
class ClientsModel (
    @Id
    @Column(name = "prp_code")
    var clientCode: Long? = null,

    @Column(name = "prp_title", nullable = true)
    var clientTitle: String? = null,

    @Column(name = "prp_surname", nullable = true)
    var clientName: String?,

    @Column(name = "prp_other_names", nullable = true)
    var clientOtherNames: String? = null,

    @Column(name = "prp_gender", nullable = true)
    var clientGender: String? = null,

    @Column(name = "prp_email", nullable = true)
    var clientEmail: String?,

    @Column(name = "prp_dob", nullable = true)
    var clientDateOfBirth: Date?,

    @Column(name = "prp_tel", nullable = true)
    var clientTelephone: String? = null,

    @Column(name = "prp_marital_status", nullable = true)
    var clientMaritalStatus: String? = null,

    @Column(name = "prp_occupation", nullable = true)
    var clientOccupation: String? = null,

    @Column(name = "prp_employer", nullable = true)
    var clientEmployer: String? = null,

    @Column(name = "prp_physical_addr", nullable = true)
    var clientPhysicalAddress: String? = null,

    @Column(name = "prp_pin", nullable = true)
    var clientPIN: String? = null,

    @Column(name = "prp_id_image", nullable = true)
    var clientIDImage: String? = null,

    @Column(name = "prp_good_health", nullable = true)
    var clientGoodHealth: String? = null,

    @Column(name = "prp_med_condition", nullable = true)
    var clientMedCondition: String? = null,

    @Column(name = "prp_height", nullable = true)
    var clientHeight: String? = null,

    @Column(name = "prp_weight", nullable = true)
    var clientWeight: String? = null,

    @Column(name = "prp_med_checkup", nullable = true)
    var clientMedCheckup: String? = null,

    @Column(name = "prp_client_type", nullable = true)
    var clientType: String? = null,

    @Column(name = "prp_id_reg_no", nullable = true)
    var clientIdNumber: String? = null,

    @Column(name = "prp_nationality", nullable = true)
    var clientNationality: String? = null,

    @Column(name = "prp_tq_prp_code", nullable = true)
    var clientTqClientCode: Long? = null,

    @Column(name = "prp_good_health_comments", nullable = true)
    var clientGoodHealthComments: String? = null,

    @Column(name = "prp_med_condition_comments", nullable = true)
    var clientMedConditionComments: String? = null,

    @Column(name = "prp_med_checkup_comments", nullable = true)
    var clientMedCheckupComments: String? = null,

    @Column(name = "prp_additional_comments", nullable = true)
    var clientAdditionalComments: String? = null,

    @Column(name = "prp_client_stage", nullable = true)
    var clientStage: String? = null,

    @Column(name = "prp_pass_reset", nullable = true)
    var clientPasswordReset: String? = "N",

    @Column(name = "prp_pass_reset_code", nullable = true)
    var clientPasswordResetCode: Long? = null,

    @Column(name = "prp_pass_reset_date", nullable = true)
    var clientPasswordResetDate: Date? = null,

    @Column(name = "prp_tp_client", nullable = true)
    var clientThirdPartySystem: String? = "N",

    )