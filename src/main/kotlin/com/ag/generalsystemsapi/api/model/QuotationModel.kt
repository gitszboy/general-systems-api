package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "quotations")
class QuotationModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quo_code")
    var quoteCode: Long? = null,

    @Column(name = "quo_effective_date", nullable = true)
    var quoteEffectiveDate: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quo_pro_code", nullable = true)
    var quoteProduct: ProductsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quo_prosp_code", nullable = true)
    var quoteProspect: ProspectsModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "quo_org_code", nullable = true)
    var quoteOrganization: OrganizationModel? = null,

    @Column(name = "quo_freq_of_payment", nullable = false)
    var quoteFreqOfPayment: String? = null,

    @Column(name = "quo_term", nullable = false)
    var quoteTerm: Long? = null,

    @Column(name = "quo_tot_sa", nullable = true)
    var quoteSumAssured: Double? = null,

    @Column(name = "quo_tot_premium", nullable = true)
    var quotePremium: Double? = null,

    @Column(name = "quo_status", nullable = true)
    var quoteStatus: String? = null,

    @Column(name = "quo_mode_of_payment", nullable = true)
    var quoteModeOfPayment: String? = null,

    @Column(name = "quo_save_point", nullable = true)
    var quoteSavePoint: String? = null,

    @Column(name = "quo_cover_from_date", nullable = true)
    var quoteCoverFromDate: Date? = null,

    @Column(name = "quo_cover_to_date", nullable = true)
    var quoteCoverToDate: Date? = null,
)