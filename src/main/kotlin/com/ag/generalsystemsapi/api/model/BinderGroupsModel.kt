package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "binder_groups")
class BinderGroupsModel   (
    @Id
    @Column(name = "bindg_code", nullable = false)
    var bindGroupCode: Long,

    @Column(name = "bindg_wef_date", nullable = true)
    var bindGroupWef: Date? = null,

    @Column(name = "bindg_wet_date", nullable = true)
    var bindGroupWet: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bindg_scl_code", nullable = true)
    var bindGroupSubClassCode: SubClassesModel? = null,

    @Column(name = "bindg_remarks", nullable = true)
    var bindGroupRemarks: String? = null,

    @Column(name = "bindg_name", nullable = true)
    var bindGroupName: String? = null,

    @Column(name = "bindg_sht_desc", nullable = true)
    var bindGroupShtDesc: String? = null,

    @Column(name = "bindg_type", nullable = true)
    var bindGroupType: String? = null,

    @Column(name = "bindg_default", nullable = true)
    var bindGroupDefault: String? = null,

    @Column(name = "bindg_web_default", nullable = true)
    var bindGroupWebDefault: String? = null,
)