package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "policy_risk_uploads")
class PolicyRiskUploadsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pru_code")
    var polRiskUploadsCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pru_ipu_code")
    var polRiskUploadsRisk: PolicyRisksModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pru_cv_code")
    var polRiskUploadsVisit: ClinicalVisitModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pru_ft_code")
    var polRiskUploadsFileType: FileTypesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pru_fle_code")
    var polRiskUploadsFileDetails: FileDetailsModel? = null,

    @Column(name = "pru_upload_date", nullable = true)
    var polRiskUploadDate: Date? = null,

)