package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "subcl_sction_perils_map")
class SubClassPerilsMapModel (
    @Id
    @Column(name = "ssprm_code", nullable = true)
    var sclPerilMapCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssprm_scl_code", nullable = true)
    var sclPerilMapSubClass: SubClassesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssprm_sect_code", nullable = true)
    var sclPerilMapSection: SectionsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssprm_bind_code", nullable = true)
    var sclPerilMapBinder: BindersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssprm_sspr_code", nullable = true)
    var sclPerilMapClassPeril: ClassPerilsModel? = null,

)