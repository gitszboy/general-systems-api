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
@Table(name = "gin_product_sub_classes")
class TpProductSubClassesModel (
    @Id
    @Column(name = "clp_code", nullable = false)
    var prodSubClassCode: Long,

    @Column(name = "clp_pro_code", nullable = false)
    var prodSubClassProdCode: Long,

    @Column(name = "clp_scl_code", nullable = false)
    var prodSubClassSubclassCode: Long,

    @Column(name = "clp_wef", nullable = true)
    var prodSubClassWef: Date? = null,

    @Column(name = "clp_wet", nullable = true)
    var prodSubClassWet: Date? = null,

    @Column(name = "clp_mandatory", nullable = true)
    var prodSubClassMandatory: String? = null,

    )