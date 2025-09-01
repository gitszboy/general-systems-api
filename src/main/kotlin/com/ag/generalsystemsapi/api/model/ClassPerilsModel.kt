package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "subcl_sction_perils")
class ClassPerilsModel   (
    @Id
    @Column(name = "sspr_code", nullable = true)
    var clPerilCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sspr_per_code", nullable = true)
    var clPerilPerilCode: PerilsModel? = null,

    @Column(name = "sspr_peril_type", nullable = true)
    var clPerilType: String? = null,

    @Column(name = "sspr_si_or_limit", nullable = true)
    var clPerilSIorLimit: String? = null,

    @Column(name = "sspr_peril_limit", nullable = true)
    var clPerilLimit: Double? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sspr_cla_code", nullable = true)
    var clPerilClassCode: ClassesModel? = null,

    @Column(name = "sspr_expire_on_claim", nullable = true)
    var clPerilExpireOnClaim: String? = null,

)