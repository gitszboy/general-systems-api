package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "subclass_cover_types")
class SubClassCoverTypesModel  (
    @Id
    @Column(name = "sclcovt_code", nullable = true)
    var scCoverCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sclcovt_covt_code", nullable = true)
    var scCoverCoverTypeCode: CoverTypesModel? = null,

    @Column(name = "sclcovt_covt_sht_desc", nullable = true)
    var scCoverCoverTypeShtDesc: String? = null,

    @Column(name = "sclcovt_desc", nullable = true)
    var scCoverCoverTypeDesc: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sclcovt_scl_code", nullable = true)
    var scCoverSubClass: SubClassesModel? = null,

    @Column(name = "sclcovt_default", nullable = true)
    var scCoverDefault: String? = null,

)