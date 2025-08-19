package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "gin_sections")
class TpSectionsModel (
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