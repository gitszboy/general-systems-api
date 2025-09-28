package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "quote_risk_uploads")
class QuotationUploadsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qru_code")
    var quoRiskUploadsCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qru_qr_code")
    var quoRiskUploadsRisk: QuotationRisksModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qru_ft_code")
    var quoRiskUploadsFileType: FileTypesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qru_fle_code")
    var quoRiskUploadsFileDetails: FileDetailsModel? = null,

    @Column(name = "qru_upload_date", nullable = true)
    var quoRiskUploadDate: Date? = null,

)