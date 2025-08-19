package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "prospects")
class ProspectsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prosp_code")
    var prospectCode: Long? = null,

    @Column(name = "prosp_name", nullable = true)
    var prospectName: String,

    @Column(name = "prosp_dob", nullable = true)
    var prospectDateOfBirth: Date,

    @Column(name = "prosp_tel", nullable = true)
    var prospectTelephone: String,

    @Column(name = "prosp_email", nullable = true)
    var prospectEmail: String? = null,

    @Column(name = "prosp_id_number", nullable = true)
    var prospectIdNumber: String? = null,

    @Column(name = "prosp_phy_addr", nullable = true)
    var prospectPhysicalAddress: String? = null,

    @Column(name = "prosp_occupation", nullable = true)
    var prospectOccupation: String? = null,
)