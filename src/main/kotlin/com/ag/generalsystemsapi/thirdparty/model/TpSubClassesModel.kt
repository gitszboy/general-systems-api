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
@Table(name = "gin_sub_classes")
class TpSubClassesModel (
    @Id
    @Column(name = "scl_code", nullable = false)
    var subClassCode: Long,

    @Column(name = "scl_desc", nullable = true)
    var subClassDescription: String?,

    @Column(name = "scl_sht_desc", nullable = true)
    var subClassShortDesc: String?,

    @Column(name = "scl_cla_code", nullable = true)
    var subClassClassCode: Long? = null,

    @Column(name = "scl_wef", nullable = true)
    var subClassWef: Date? = null,

    @Column(name = "scl_wet", nullable = true)
    var subClassWet: Date? = null,
)