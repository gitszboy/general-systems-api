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
@Table(name = "active_banks_view")
class TpActiveBanksModel (
    @Id
    @Column(name = "bbr_code", nullable = false)
    var bankBranchCode: Long,

    @Column(name = "bank_name", nullable = false)
    var bankBranchName: String?,

    )