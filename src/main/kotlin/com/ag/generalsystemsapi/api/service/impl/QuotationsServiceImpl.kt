package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ProspectPetsModel
import com.ag.generalsystemsapi.api.model.ProspectsModel
import com.ag.generalsystemsapi.api.model.QuotationModel
import com.ag.generalsystemsapi.api.model.QuotationRisksModel
import com.ag.generalsystemsapi.api.model.payload.ProspectPetsRequest
import com.ag.generalsystemsapi.api.model.payload.ProspectsRequest
import com.ag.generalsystemsapi.api.model.payload.QuotationRequest
import com.ag.generalsystemsapi.api.model.view.QuotationRisksSummary
import com.ag.generalsystemsapi.api.model.view.QuotationSummary
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IQuotationsService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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

    override fun saveQuotation(request: QuotationRequest) : Result<QuotationSummary>{
        val product = productsRepo.findById(request.quoteProduct)
            .orElseThrow {Exception("Product not found") }

        val organization = organizationRepo.findById(request.quoteOrganization)
            .orElseThrow {Exception("Organization not found") }

        val subclass = productSubClassesRepo.findByProdSubClassProdCodeAndProdSubClassDefault(product, "Y")
            .orElseThrow {Exception("Sub class not found") }

        val coverType = subClassCoverTypesRepo.findByScCoverSubClassAndScCoverDefault(subclass.prodSubClassSubclassCode, "Y")
            .orElseThrow {Exception("Cover type not found") }

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

            val pet = saveProspectPet(p, prospect)

            val quotePet = QuotationRisksModel(
                quoteRiskQuotation = quote,
                quoteRiskProspect = prospect,
                quoteRiskProspectPet = pet,
                quoteRiskSubClassCode = subclass.prodSubClassSubclassCode,
                quoteRiskBindCode = binder.bindGroupLimitBinders,
                quoteRiskCoverType = coverType.scCoverCoverTypeCode,
                quoteRiskWef = request.quoteEffectiveDate,
                quoteRiskWet = coverToDate,
                quoteRiskPropertyId = pet.prospectPetMicroNumber,
                quoteRiskItemDesc = pet.prospectPetName,
                quoteRiskValue = p.prospectPetAnnualLimit,
                quoteRiskStatus = "Draft"
            )
            quoteRisksRepo.save(quotePet)
        }

        //Compute the premium values. TODO.

        val result = constructQuoteSummary(quote.quoteCode!!)

        return ResultFactory.getSuccessResult(result)

    }

    override fun findOrganizationQuotes(orgCode: Long): Result<Iterable<QuotationSummary>> {
        val organization = organizationRepo.findById(orgCode).orElse(null)
            ?: return ResultFactory.getFailResult(emptyList(), "Organization not found")

        val quoteList = quotationRepo.findByQuoteOrganization(organization)
            .mapNotNull { q -> q.quoteCode?.let { constructQuoteSummary(it) } }

        return ResultFactory.getSuccessResult(quoteList)
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
            quoteRisks = risks
        )
    }
}