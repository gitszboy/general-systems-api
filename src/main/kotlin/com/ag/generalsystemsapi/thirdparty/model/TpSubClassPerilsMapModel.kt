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
@Table(name = "gin_subcl_sction_perils_map")
class TpSubClassPerilsMapModel (
    @Id
    @Column(name = "ssprm_code", nullable = true)
    var sclPerilMapCode: Long,

    @Column(name = "ssprm_scl_code", nullable = true)
    var sclPerilMapSubClass: Long? = null,

    @Column(name = "ssprm_sect_code", nullable = true)
    var sclPerilMapSection: Long? = null,

    @Column(name = "ssprm_bind_code", nullable = true)
    var sclPerilMapBinder: Long? = null,

    @Column(name = "ssprm_sspr_code", nullable = true)
    var sclPerilMapClassPeril: Long? = null,

)