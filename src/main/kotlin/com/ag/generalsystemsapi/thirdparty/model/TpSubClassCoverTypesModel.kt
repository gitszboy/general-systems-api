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
@Table(name = "gin_subclass_cover_types")
class TpSubClassCoverTypesModel (
    @Id
    @Column(name = "sclcovt_code", nullable = false)
    var scCoverCode: Long,

    @Column(name = "sclcovt_covt_code", nullable = false)
    var scCoverCoverTypeCode: Long?,

    @Column(name = "sclcovt_covt_sht_desc", nullable = false)
    var scCoverCoverTypeShtDesc: String? = null,

    @Column(name = "sclcovt_desc", nullable = false)
    var scCoverCoverTypeDesc: String? = null,

    @Column(name = "sclcovt_scl_code", nullable = false)
    var scCoverSubClass: Long? = null,

    @Column(name = "sclcovt_default", nullable = false)
    var scCoverDefault: String? = null,

 )