package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Id

@Setter
@Getter
@Entity
@Table(name = "gin_subcl_sction_perils")
class TpClassPerilsModel   (
    @Id
    @Column(name = "sspr_code", nullable = true)
    var clPerilCode: Long,

    @Column(name = "sspr_per_code", nullable = true)
    var clPerilPerilCode: Long? = null,

    @Column(name = "sspr_peril_type", nullable = true)
    var clPerilType: String? = null,

    @Column(name = "sspr_si_or_limit", nullable = true)
    var clPerilSIorLimit: String? = null,

    @Column(name = "sspr_peril_limit", nullable = true)
    var clPerilLimit: Double? = null,

    @Column(name = "sspr_cla_code", nullable = true)
    var clPerilClassCode: Long? = null,

    @Column(name = "sspr_expire_on_claim", nullable = true)
    var clPerilExpireOnClaim: String? = null,

)