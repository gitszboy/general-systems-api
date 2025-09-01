package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "product_sub_classes")
class ProductSubClassesModel (
    @Id
    @Column(name = "clp_code", nullable = false)
    var prodSubClassCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clp_pro_code", nullable = true)
    var prodSubClassProdCode: ProductsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clp_scl_code", nullable = true)
    var prodSubClassSubclassCode: SubClassesModel? = null,

    @Column(name = "clp_wef", nullable = true)
    var prodSubClassWef: Date? = null,

    @Column(name = "clp_wet", nullable = true)
    var prodSubClassWet: Date? = null,

    @Column(name = "clp_mandatory", nullable = true)
    var prodSubClassMandatory: String? = null,

    @Column(name = "clp_default", nullable = true)
    var prodSubClassDefault: String? = null,

    )