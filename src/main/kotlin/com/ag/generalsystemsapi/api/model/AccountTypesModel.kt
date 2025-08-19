package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "account_types")
class AccountTypesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_code")
    var accountTypeCode: Long? = null,

    @Column(name = "act_account_type", nullable = true)
    var accountTypeName: String?,
)