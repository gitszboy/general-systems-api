package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "cover_types_map")
class CoverTypesMapModel (
    @Id
    @Column(name = "covtm_code", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var coverMapCode: Long? = null,

    @Column(name = "covtm_animal", nullable = true)
    var coverMapAnimal: String? = null,

    @Column(name = "covtm_min_age", nullable = true)
    var coverMapMinAge: Long? = null,

    @Column(name = "covtm_max_age", nullable = true)
    var coverMapMaxAge: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "covtm_covt_age", nullable = true)
    var coverMapCover: CoverTypesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "covtm_sect_code", nullable = true)
    var coverMapSection: SectionsModel? = null,
)