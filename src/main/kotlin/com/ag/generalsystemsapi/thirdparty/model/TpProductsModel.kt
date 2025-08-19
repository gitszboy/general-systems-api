package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "gin_products")
class TpProductsModel (
    @Id
    @Column(name = "pro_code", nullable = false)
    var productCode: Long,

    @Column(name = "pro_sht_desc", nullable = false)
    var productShtDesc: String,

    @Column(name = "pro_desc", nullable = false)
    var productDesc: String,

    @Column(name = "pro_wef", nullable = true)
    var productWef: Date? = null,

    @Column(name = "pro_wet", nullable = true)
    var productWet: Date? = null,

    )