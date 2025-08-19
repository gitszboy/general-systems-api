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
@Table(name = "tqc_account_types")
class TpAccountTypesModel (
    @Id
    @Column(name = "act_code")
    var accountTypeCode: Long? = null,

    @Column(name = "act_account_type", nullable = true)
    var accountTypeName: String?,
)