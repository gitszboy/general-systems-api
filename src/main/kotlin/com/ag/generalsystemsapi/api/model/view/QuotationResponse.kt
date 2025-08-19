package com.ag.generalsystemsapi.api.model.view

import com.ag.generalsystemsapi.api.model.ClientsModel
import java.util.*
import kotlin.collections.ArrayList

class QuotationResponse(
    var quoteCode: Long?,
    var prodCode: Long?,
    var productName: String?,
    var optionCode: Long?,
    var optionName: String?,
    var client: ClientsModel? = null,
    var ownerClient: ClientsModel? = null,
    var term: Long?,
    var freqOfPayment: String?,
    var sumAssured: Double?,
    var premium: Double?,
    var reversionaryBonus: Double?,
    var terminalBonus: Double?,
    var totalBenefit: Double?,
    var modeOfPayment: String? = null,
    var lienApplicable: String? = null,
    var totalContribution: Double? = null,
    var totalInvestmentAmt: Double? = null,
    var totalEMVAmt: Double? = null,
    var totalEMVIntrAmt: Double? = null,
    var savePoint: String? = null,
    var quoteBankBranch: Long? = null,
    var quoteBankBranchName: String? = null,
    var quoteBankAccountNo: String? = null,
    var quoteCheckOffInstitution: Long? = null,
    var quoteCheckOffInstitutionName: String? = null,
    var quoteCheckOffPayrollNo: String? = null,
    var quoteMpesaTelephone: String? = null,
    var quoteDeductionDate: Date? = null,
    var quoteReportName: String? = "quote_summary",
    var benefits: ArrayList<QuotationBenefitResponse>? = null,
    var maturities: ArrayList<QuotationMaturityResponse>? = null,
    var beneficiaries: ArrayList<QuoteBeneficiaryRequest>? = null,
    var clientMedHistory: ArrayList<ClientsMedHistoryRequest>? = null,
    var clientFamHistory: ArrayList<ClientsFamHistoryRequest>? = null,
    var clientPhotos: ArrayList<ClientPhotosResponse>? = null,
    var intrProjections: ArrayList<QuoteIntrProjResponse>? = null
)