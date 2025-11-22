package com.ag.generalsystemsapi.api.model

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
@Table(name = "premium_rates")
class PremiumRatesModel  (
    @Id
    @Column(name = "prr_code", nullable = false)
    var rateCode: Long,

    @Column(name = "prr_sect_code", nullable = false)
    var rateSectCode: Long,

    @Column(name = "prr_rate", nullable = false)
    var rateValue: Double,

    @Column(name = "prr_wef", nullable = true)
    var rateWef: Date? = null,

    @Column(name = "prr_wet", nullable = true)
    var rateWet: Date? = null,

    @Column(name = "prr_scl_code", nullable = true)
    var rateSubClassCode: Long? = null,

    @Column(name = "prr_bind_code", nullable = true)
    var rateBindCode: Long? = null,

    @Column(name = "prr_range_from", nullable = true)
    var rateRangeFrom: Long? = null,

    @Column(name = "prr_range_to", nullable = true)
    var rateRangeTo: Long? = null,

    @Column(name = "prr_division_factor", nullable = true)
    var rateDivFactor: Long? = null,

    @Column(name = "prr_rate_freq_type", nullable = true)
    var rateFreqType: String? = null,
)