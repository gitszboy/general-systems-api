package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "classes")
class ClassesModel (
    @Id
    @Column(name = "cla_code", nullable = false)
    var classCode: Long,

    @Column(name = "cla_descn", nullable = true)
    var classDescription: String?,

    @Column(name = "cla_sht_desc", nullable = true)
    var classShortDesc: String?,

    @Column(name = "cla_wef", nullable = true)
    var classWef: Date? = null,

    @Column(name = "cla_wet", nullable = true)
    var classWet: Date? = null,
)