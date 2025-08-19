package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "sections")
class SectionsModel (
    @Id
    @Column(name = "sect_code", nullable = false)
    var sectionCode: Long,

    @Column(name = "sect_sht_desc", nullable = false)
    var sectionShtDesc: String,

    @Column(name = "sect_desc", nullable = false)
    var sectionDescription: String,

    @Column(name = "sect_type", nullable = false)
    var sectionType: String,

)