package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.helpers.FileUploadResourceHelper
import com.ag.generalsystemsapi.api.model.*
import com.ag.generalsystemsapi.api.model.payload.ProspectPetsRequest
import com.ag.generalsystemsapi.api.model.payload.ProspectsRequest
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.responses.QuoteResponse
import com.ag.generalsystemsapi.api.model.view.*
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IQuotationsService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPoliciesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Service
class QuotationsServiceImpl : IQuotationsService {

    @Autowired
    lateinit var prospectsRepo: ProspectsRepository

    @Autowired
    lateinit var clientPetsRepo: ClientPetsRepository

    @Autowired
    lateinit var productsRepo: ProductsRepository

    @Autowired
    lateinit var organizationRepo: OrganizationRepository

    @Autowired
    lateinit var quotationRepo: QuotationRepository

    @Autowired
    lateinit var productSubClassesRepo: ProductSubClassesRepository

    @Autowired
    lateinit var quoteRisksRepo: QuotationRisksRepository

    @Autowired
    lateinit var bindersRepo: BindersRepository

    @Autowired
    lateinit var subClassCoverTypesRepo: SubClassCoverTypesRepository

    @Autowired
    lateinit var binderGroupLimitsRepo: BinderGroupLimitsRepository

    @Autowired
    lateinit var binderGroupsRepo: BinderGroupsRepository

    @Autowired
    lateinit var fileUploadResourceHelper: FileUploadResourceHelper

    @Autowired
    lateinit var fileDetailsRepo: FileDetailsRepository

    @Autowired
    lateinit var fileTypesRepo: FileTypesRepository

    @Autowired
    lateinit var quotationUploadsRepo: QuotationUploadsRepository

    @Autowired
    lateinit var coverTypesMapRepository: CoverTypesMapRepository

    @Autowired
    lateinit var tpActivePetPoliciesRepo: TpActivePetPoliciesRepository

    @Autowired
    lateinit var premiumRatesRespo: PremiumRatesRespository

    @Autowired
    lateinit var tpTicketServiceImpl: TpTicketServiceImpl

    override fun saveQuotation(request: QuotationRequest) : Result<QuotationSummary>{
        var totalPremium: Double = 0.0
        val product = productsRepo.findById(request.quoteProduct)
            .orElseThrow {Exception("Product not found") }

        val organization = organizationRepo.findById(request.quoteOrganization)
            .orElseThrow {Exception("Organization not found") }

        val subclass = productSubClassesRepo.findByProdSubClassProdCodeAndProdSubClassDefault(product, "Y")
            .orElseThrow {Exception("Sub class not found") }

        //val coverType = subClassCoverTypesRepo.findByScCoverSubClassAndScCoverDefault(subclass.prodSubClassSubclassCode, "Y")
        //    .orElseThrow {Exception("Cover type not found") }

        //Create the prospect.
        val prospect = saveProspect(request.quoteProspect)

        val coverToDate = addYears(request.quoteEffectiveDate, product.productDefaultDuration?:1)

        //Create the quotation.
        var quote = QuotationModel(
            quoteEffectiveDate = request.quoteEffectiveDate,
            quoteProduct = product,
            quoteProspect = prospect,
            quoteOrganization = organization,
            quoteFreqOfPayment = request.quotePaymentFrequency,
            quoteTerm = product.productDefaultDuration,
            quoteStatus = "Draft",
            quoteModeOfPayment = request.quotePaymentMethod,
            quoteCoverFromDate = request.quoteEffectiveDate,
            quoteCoverToDate = coverToDate
        )

        quote = quotationRepo.save(quote)

        //Create the Prospects Pets.
        for(p in request.quoteProspectPets){
            val binderGroup = binderGroupsRepo.findById(p.prospectBinderCode)
                .orElseThrow {Exception("Binder Group not found") }

            val binder = binderGroupLimitsRepo.findByBindGroupLimitGroupAndBindGroupLimitAmount(binderGroup, p.prospectPetAnnualLimit)
                .orElseThrow {Exception("Binder not found") }

            val cover = coverTypesMapRepository.findCoverMapping(p.prospectPetType!!, getAgeFromDate(p.prospectPetDateOfBirth!!).toLong())
                .orElseThrow {Exception("Cover not found") }

            val pet = saveProspectPet(p, prospect)

            var quotePet = QuotationRisksModel(
                quoteRiskQuotation = quote,
                quoteRiskProspect = prospect,
                quoteRiskProspectPet = pet,
                quoteRiskSubClassCode = subclass.prodSubClassSubclassCode,
                quoteRiskBindCode = binder.bindGroupLimitBinders,
                quoteRiskCoverType = cover.coverMapCover,
                quoteRiskWef = request.quoteEffectiveDate,
                quoteRiskWet = coverToDate,
                quoteRiskPropertyId = pet.prospectPetMicroNumber,
                quoteRiskItemDesc = pet.prospectPetName,
                quoteRiskValue = p.prospectPetAnnualLimit,
                quoteRiskStatus = "Draft"
            )
            quotePet = quoteRisksRepo.save(quotePet)

            //Compute the premium values. TODO.
            val rates = premiumRatesRespo.findPremiumRate(subclass.prodSubClassSubclassCode?.subClassCode,
                                                          binder.bindGroupLimitBinders?.bindCode,
                                                          request.quotePaymentFrequency,
                                                          cover.coverMapSection?.sectionCode,
                                                          request.quoteEffectiveDate
                                                         )
            for(r in rates){
                val premium = r.rateValue.div(r.rateDivFactor?:1)
                quotePet.quoteRiskPremium = premium
                quoteRisksRepo.save(quotePet)
                totalPremium = totalPremium.plus(premium)
            }
        }
        //Update Premium Values.
        quote.quotePremium = totalPremium
        quote = quotationRepo.save(quote)
        val result = constructQuoteSummary(quote.quoteCode!!)

        return ResultFactory.getSuccessResult(result)

    }

    override fun saveDirectQuote(request: QuotationRequest) : Result<QuoteResponse>{
        val response = QuoteResponse(
            policyNumber = null
        )
        //validations.
        //TODO

        //create Quotation on portal.
        var result = saveQuotation(request)

        //push quote to core system.
        //TODO

        return ResultFactory.getSuccessResult(response,"Policy Created Successfully")
    }

    override fun findOrganizationQuotes(orgCode: Long, clientCode: Long?): Result<Iterable<QuotationSummary>> {
        val organization = organizationRepo.findById(orgCode).orElse(null)
            ?: return ResultFactory.getFailResult(emptyList(), "Organization not found")

        if(clientCode != null){
            val prospect = prospectsRepo.findById(clientCode).orElse(null)
                ?: return ResultFactory.getFailResult(emptyList(), "Client not found")
            return ResultFactory.getSuccessResult(quotationRepo.findByQuoteProspect(prospect)
                .mapNotNull { q -> q.quoteCode?.let { constructQuoteSummary(it) } })
        }else{
            return ResultFactory.getSuccessResult(quotationRepo.findByQuoteOrganization(organization)
                .mapNotNull { q -> q.quoteCode?.let { constructQuoteSummary(it) } })
        }
    }

    override fun findQuoteDetails(quoteCode: Long) : Result<QuotationSummary>{
        return ResultFactory.getSuccessResult(constructQuoteSummary(quoteCode))
    }

    fun addYears(date: Date, years: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.YEAR, years.toInt())
        return calendar.time
    }

    fun getAgeFromDate(date: Date): Int {
        val birthCal = Calendar.getInstance()
        birthCal.time = date
        val todayCal = Calendar.getInstance()
        var age = todayCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR)
        // Adjust if birthday hasn’t occurred yet this year
        if (todayCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }

    fun saveProspect(prospect: ProspectsRequest) : ProspectsModel{
        val newProspect = ProspectsModel(
             prospectCode = prospect.prospectCode,
             prospectName = prospect.prospectName,
             prospectDateOfBirth = prospect.prospectDateOfBirth,
             prospectTelephone = prospect.prospectTelephone,
             prospectEmail = prospect.prospectEmail,
             prospectIdNumber = prospect.prospectIdNumber,
             prospectPhysicalAddress = prospect.prospectPhysicalAddress,
             prospectOccupation = prospect.prospectOccupation,
        )
        return prospectsRepo.save(newProspect)
    }

    fun saveProspectPet(pet: ProspectPetsRequest, prospect: ProspectsModel) : ProspectPetsModel {

        val newPet = ProspectPetsModel(
            prospectPetCode = pet.prospectPetCode,
            prospectPetProspect = prospect,
            prospectPetName = pet.prospectPetName,
            prospectPetType = pet.prospectPetType,
            prospectPetBreed = pet.prospectPetBreed,
            prospectPetGender = pet.prospectPetGender,
            prospectPetDateOfBirth = pet.prospectPetDateOfBirth,
            prospectPetWeight = pet.prospectPetWeight,
            prospectPetMicroAvail = pet.prospectPetMicroAvail,
            prospectPetMicroNumber = pet.prospectPetMicroNumber,
            prospectPetSterilized = pet.prospectPetSterilized,
            prospectPetVaccinated = pet.prospectPetVaccinated,
            prospectPetMedicalConditions = pet.prospectPetMedicalConditions,
            prospectPetMedicalSurgeries = pet.prospectPetMedicalSurgeries,
            prospectPetMedicalMedications = pet.prospectPetMedicalMedications,
            prospectPetMedicalSeizures = pet.prospectPetMedicalSeizures,
            prospectPetInjureOthers = pet.prospectPetInjureOthers,
            prospectPetBreeding = pet.prospectPetBreeding,
            prospectPetComments = pet.prospectPetComments,
        )
        return clientPetsRepo.save(newPet)
    }

    fun constructQuoteSummary(quoteCode: Long): QuotationSummary{
        val quote = quotationRepo.findById(quoteCode)
            .orElseThrow {Exception("quotation not found") }

        val risks = quoteRisksRepo.findByQuoteRiskQuotation(quote)
            .map { r ->
                QuotationRisksSummary(
                    quoteRiskCode = r.quoteRiskCode,
                    quoteRiskQuotation = quote.quoteCode,
                    quoteRiskProspectPet = r.quoteRiskProspectPet,
                    quoteRiskBindCode = r.quoteRiskBindCode?.bindCode,
                    quoteRiskBindName = r.quoteRiskBindCode?.bindName,
                    quoteRiskWef = r.quoteRiskWef,
                    quoteRiskWet = r.quoteRiskWet,
                    quoteRiskValue = r.quoteRiskValue,
                    quoteRiskPremium = r.quoteRiskPremium,
                    quoteRiskStatus = r.quoteRiskStatus
                )
            }

        return QuotationSummary(
            quoteCode = quote.quoteCode,
            quoteEffectiveDate = quote.quoteEffectiveDate,
            quoteProduct = quote.quoteProduct,
            quoteProspect = quote.quoteProspect,
            quoteOrganization = quote.quoteOrganization,
            quoteFreqOfPayment = quote.quoteFreqOfPayment,
            quoteTerm = quote.quoteTerm,
            quoteSumAssured = quote.quoteSumAssured,
            quotePremium = quote.quotePremium,
            quoteModeOfPayment = quote.quoteModeOfPayment,
            quoteCoverFromDate = quote.quoteCoverFromDate,
            quoteCoverToDate = quote.quoteCoverToDate,
            quoteStatus = quote.quoteStatus,
            quoteRisks = risks,
            quotePolicyNo = quote.quoteTpPolicyNo
        )
    }

    override fun uploadQuoteRiskDocument(quoRiskCode: Long, fileTypeCode: Long, file: MultipartFile) : Result<Iterable<QuotationRiskUploadsView>>{
        val risk = quoteRisksRepo.findById(quoRiskCode)
            .orElseThrow { Exception("Pet not found") }

        val fileType = fileTypesRepo.findById(fileTypeCode)
            .orElseThrow { Exception("File Type not found") }

        //Upload File.
        var uplFile = fileUploadResourceHelper.uploadFile(file, "quoteDocuments", risk.quoteRiskPropertyId!!)
        uplFile = fileDetailsRepo.save(uplFile)

        //Add upload tracker.
        val upl = QuotationUploadsModel(
            quoRiskUploadsCode = null,
            quoRiskUploadsRisk = risk,
            quoRiskUploadDate = Calendar.getInstance().time,
            quoRiskUploadsFileDetails = uplFile,
            quoRiskUploadsFileType = fileType
        )
        quotationUploadsRepo.save(upl)

        val uploadList = quotationUploadsRepo
            .findByQuoRiskUploadsRisk(risk)
            .map { QuotationRiskUploadsView(
                quoRiskUploadsCode = it.quoRiskUploadsCode,
                quoRiskUploadsRisk = it.quoRiskUploadsRisk?.quoteRiskCode,
                fileTypeName = it.quoRiskUploadsFileType?.fileTypeName,
                fileUploadFileName = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileUri = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileDownloadUri = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileSize = it.quoRiskUploadsFileDetails?.fileUploadFileSize,
                fileUploadDate = it.quoRiskUploadsFileDetails?.fileUploadDate,
            ) }
        return ResultFactory.getSuccessResult(uploadList)
    }

    override fun findQuotationDocuments(quoRiskCode: Long) : Result<Iterable<QuotationRiskUploadsView>>{
        val risk = quoteRisksRepo.findById(quoRiskCode)
            .orElseThrow { Exception("Pet not found") }

        val uploadList = quotationUploadsRepo
            .findByQuoRiskUploadsRisk(risk)
            .map { QuotationRiskUploadsView(
                quoRiskUploadsCode = it.quoRiskUploadsCode,
                quoRiskUploadsRisk = it.quoRiskUploadsRisk?.quoteRiskCode,
                fileTypeName = it.quoRiskUploadsFileType?.fileTypeName,
                fileUploadFileName = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileUri = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileDownloadUri = it.quoRiskUploadsFileDetails?.fileUploadFileName,
                fileUploadFileSize = it.quoRiskUploadsFileDetails?.fileUploadFileSize,
                fileUploadDate = it.quoRiskUploadsFileDetails?.fileUploadDate,
            ) }
        return ResultFactory.getSuccessResult(uploadList)
    }

    override fun findQuotationFileTypes() : Result<Iterable<FileTypesModel>>{
        return ResultFactory.getSuccessResult(fileTypesRepo.findByFileTypeArea("U"))
    }

    override fun createPolicyInThirdPartySystem(quoteCode: Long) : Result<String>{
        var quote = quotationRepo.findById(quoteCode)
            .orElseThrow {Exception("quotation not found") }

        val oracleFormat = SimpleDateFormat("dd/MM/yyyy")
        val formattedCoverFromDate = oracleFormat.format(quote.quoteCoverFromDate)
        val formattedCoverToDate = oracleFormat.format(quote.quoteCoverToDate)
        var policyNumber: String? = null
        var polBatchNo: Long? = null

        //Push Policy Details.
        val tqPolicy = tpActivePetPoliciesRepo.savePolicy(
                            quote.quoteProspect?.prospectIdNumber,
                            quote.quoteProspect?.prospectIdNumber,
                            quote.quoteProspect?.prospectTelephone,
                            quote.quoteProspect?.prospectName,
                            null,
                            quote.quoteProspect?.prospectName,
                            "M",
                            quote.quoteProspect?.prospectPhysicalAddress,
                            null,
                            quote.quoteProspect?.prospectEmail,
                            "I",
                            "0101",
                            formattedCoverFromDate,
                            formattedCoverToDate,
                                "KSH",
                                "7",
                                null,
                            quote.quoteProduct?.productCode
                            )

        val policy : Any? = tqPolicy["v_batch_no"]
        if(policy != null){
            println("policy value== $policy")
            polBatchNo = policy.toString().toLong()
            val polNo: Any? = tqPolicy["v_gen_pol_no"]
            policyNumber = polNo.toString()

            quote.quoteStatus = "Submitted"
            quote.quoteTpPolicyNo = policyNumber
            quote.quoteTpPolBatchNo = polBatchNo
            quote = quotationRepo.save(quote)

            //Create the risk information.
            for(r in quoteRisksRepo.findByQuoteRiskQuotation(quote)){
                val tqPolicyRisk = tpActivePetPoliciesRepo.savePolicyRisk(
                                polBatchNo,
                                r.quoteRiskCoverType?.coverCode,
                                r.quoteRiskCoverType?.coverDesc,
                                r.quoteRiskBindCode?.bindCode,
                                r.quoteRiskPropertyId,
                                r.quoteRiskItemDesc,
                                r.quoteRiskSubClassCode?.subClassCode,
                                r.quoteRiskValue,
                                formattedCoverFromDate,
                                formattedCoverToDate
                            )

                val policyRisk : Any? = tqPolicyRisk
                if(policyRisk != null){
                    println("policy risk value== $policyRisk")
                    val ipuCode = policyRisk.toString().toLong()

                    //Fetch the schedule.
                    for(s in clientPetsRepo.findByProspectPetProspect(quote.quoteProspect)){
                        val formattedPetDOBDate = oracleFormat.format(quote.quoteCoverToDate)

                        val tqPolicyRisk = tpActivePetPoliciesRepo.savePolicyRiskSchedule(
                                    ipuCode,
                                    s.prospectPetType,
                                    s.prospectPetBreed,
                                    s.prospectPetGender,
                                    formattedPetDOBDate,
                                    s.prospectPetWeight,
                                    s.prospectPetMicroAvail,
                                    s.prospectPetMicroNumber,
                                    s.prospectPetSterilized,
                                    s.prospectPetVaccinated,
                                    s.prospectPetMedicalConditions,
                                    s.prospectPetMedicalSurgeries,
                                    s.prospectPetMedicalMedications,
                                    null,
                                    s.prospectPetInjureOthers,
                                    null,
                                    s.prospectPetComments,
                                    null,
                                    null,
                                    null,
                                    null
                                )
                    }
                }else{
                    return ResultFactory.getFailResult("Failed to Submit Quote Risk")
                }
            }
            //Create ticket.
            tpTicketServiceImpl.startNewWorkflowInstance(
                "GISUnderwriteProcess", "VNJUGUNA", "Underwrite Policy", "P", polBatchNo.toString(), null, null
            )
        }else{
            return ResultFactory.getFailResult("Failed to Submit Quote")
        }
        return ResultFactory.getSuccessResult("Successfully Submitted Quote")
    }
}