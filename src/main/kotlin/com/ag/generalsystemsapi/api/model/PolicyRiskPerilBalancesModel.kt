package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "pol_risk_peril_balances")
class PolicyRiskPerilBalancesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prpb_code")
    var polRskPerBalCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prpb_ipu_code", nullable = true)
    var polRskPerBalRisk: PolicyRisksModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prpb_sspr_code", nullable = true)
    var polRskPerBalPeril: ClassPerilsModel? = null,

    @Column(name = "prpb_limit_per_clm", nullable = true)
    var polRskPerBalLimitPerClaim: Double? = null,

    @Column(name = "prpb_total_limit", nullable = true)
    var polRskPerBalTotalLimit: Double? = null,

    @Column(name = "prpb_max_clm_limit", nullable = true)
    var polRskPerBalMaxClmLimit: Double? = null,

    @Column(name = "prpb_balance", nullable = true)
    var polRskPerBalBalance: Double? = null,

    @Column(name = "prpb_actual_balance", nullable = true)
    var polRskPerBalActualBal: Double? = null,

    @Column(name = "prpb_virtual_balance", nullable = true)
    var polRskPerBalVirtualBal: Double? = null,

    @Column(name = "prpb_total_clms", nullable = true)
    var polRskPerBalTotalClaims: Double? = null,

    @Column(name = "prpb_status", nullable = true)
    var polRskPerBalStatus: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prpb_cv_code", nullable = true)
    var polRskPerBalCurrVisit: ClinicalVisitModel? = null,

)