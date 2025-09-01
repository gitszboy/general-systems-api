package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "binder_limits")
class BinderLimitsModel  (
    @Id
    @Column(name = "bindl_code", nullable = false)
    var bindLimitCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bindl_bind_code", nullable = true)
    var bindLimitBinders: BindersModel? = null,

    @Column(name = "bind_limit_amt", nullable = true)
    var bindLimitAmount: Double? = null,
)