package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "perils")
class PerilsModel  (
    @Id
    @Column(name = "per_code", nullable = false)
    var perilCode: Long,

    @Column(name = "per_sht_desc", nullable = true)
    var perilShtDesc: String? = null,

    @Column(name = "per_desc", nullable = true)
    var perilDescription: String? = null,

    @Column(name = "per_full_desc", nullable = true)
    var perilFullDescription: String? = null,

    @Column(name = "per_payment_type", nullable = true)
    var perilPaymentType: String? = null,

    @Column(name = "per_wef", nullable = true)
    var perilWef: Date? = null,

    @Column(name = "per_wet", nullable = true)
    var perilWet: Date? = null,

    @Column(name = "per_type", nullable = true)
    var perilType: String? = null,

    )