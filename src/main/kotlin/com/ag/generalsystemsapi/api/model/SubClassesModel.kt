package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "sub_classes")
class SubClassesModel (
    @Id
    @Column(name = "scl_code", nullable = false)
    var subClassCode: Long,

    @Column(name = "scl_desc", nullable = true)
    var subClassDescription: String?,

    @Column(name = "scl_sht_desc", nullable = true)
    var subClassShortDesc: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scl_cla_code", nullable = true)
    var subClassClassCode: ClassesModel? = null,

    @Column(name = "scl_wef", nullable = true)
    var subClassWef: Date? = null,

    @Column(name = "scl_wet", nullable = true)
    var subClassWet: Date? = null,
)