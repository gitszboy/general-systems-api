package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "binder_group_limits")
class BinderGroupLimitsModel  (
    @Id
    @Column(name = "bindlg_code", nullable = false)
    var bindGroupLimitCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bindlg_bindg_code", nullable = true)
    var bindGroupLimitGroup: BinderGroupsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bindlg_bind_code", nullable = true)
    var bindGroupLimitBinders: BindersModel? = null,

    @Column(name = "bindlg_limit_amt", nullable = true)
    var bindGroupLimitAmount: Double? = null,
)