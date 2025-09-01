package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "quotation_risks")
class QuotationRisksModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_code", nullable = false)
    var quoteRiskCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_quo_code", nullable = true)
    var quoteRiskQuotation: QuotationModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_prosp_code", nullable = true)
    var quoteRiskProspect: ProspectsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_prpt_code", nullable = true)
    var quoteRiskProspectPet: ProspectPetsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_scl_code", nullable = true)
    var quoteRiskSubClassCode: SubClassesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_bind_code", nullable = true)
    var quoteRiskBindCode: BindersModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_covt_code", nullable = true)
    var quoteRiskCoverType: CoverTypesModel? = null,

    @Column(name = "qr_wef", nullable = true)
    var quoteRiskWef: Date? = null,

    @Column(name = "qr_wet", nullable = true)
    var quoteRiskWet: Date? = null,

    @Column(name = "qr_property_id", nullable = true)
    var quoteRiskPropertyId: String? = null,

    @Column(name = "qr_item_desc", nullable = true)
    var quoteRiskItemDesc: String? = null,

    @Column(name = "qr_value", nullable = true)
    var quoteRiskValue: Double? = null,

    @Column(name = "qr_premium", nullable = true)
    var quoteRiskPremium: Double? = null,

    @Column(name = "qr_status", nullable = true)
    var quoteRiskStatus: String? = null,
)