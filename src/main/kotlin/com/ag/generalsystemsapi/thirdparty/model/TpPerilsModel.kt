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
@Table(name = "gin_perils")
class TpPerilsModel  (
    @Id
    @Column(name = "per_code", nullable = false)
    var perilCode: Long,

    @Column(name = "per_sht_desc", nullable = false)
    var perilShtDesc: String? = null,

    @Column(name = "per_desc", nullable = false)
    var perilDescription: String? = null,

    @Column(name = "per_full_desc", nullable = false)
    var perilFullDescription: String? = null,

    @Column(name = "per_payment_type", nullable = false)
    var perilPaymentType: String? = null,

    @Column(name = "per_wef", nullable = false)
    var perilWef: Date? = null,

    @Column(name = "per_wet", nullable = false)
    var perilWet: Date? = null,

    @Column(name = "per_type", nullable = false)
    var perilType: String? = null,

    )