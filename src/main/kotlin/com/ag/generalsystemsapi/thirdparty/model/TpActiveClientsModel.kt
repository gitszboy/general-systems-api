package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "active_clients_view")
class TpActiveClientsModel (

    @Id
    @Column(name = "prp_code", nullable = false)
    var clientCode: Long,

    @Column(name = "client_name", nullable = false)
    var clientName: String?,

    @Column(name = "prp_email", nullable = false)
    var clientEmail: String?,

    @Column(name = "prp_tel", nullable = false)
    var clientTelephone: String?,

    @Column(name = "prp_dob", nullable = true)
    var clientDOB: Date?,

    @Column(name = "prp_sex", nullable = true)
    var clientGender: String?,

    @Column(name = "prp_id_reg_no", nullable = true)
    var clientIDNumber: String?,

    @Column(name = "prp_pin", nullable = true)
    var clientPIN: String?,

    @Column(name = "prp_marital_status", nullable = true)
    var clientMartialStatus: String?,

    @Column(name = "prp_physical_addr", nullable = true)
    var clientPhysicalAddress: String?,

    @Column(name = "prp_nationality", nullable = true)
    var clientNationality: String?,

    @Column(name = "prp_employer", nullable = true)
    var clientEmployer: String?,

    @Column(name = "prp_business", nullable = true)
    var clientOccupation: String?,

    @Column(name = "prp_postal_address", nullable = true)
    var clientPostalAddress: String?,

    @Column(name = "agn_code", nullable = true)
    var clientAgentCode: Long?,

    )