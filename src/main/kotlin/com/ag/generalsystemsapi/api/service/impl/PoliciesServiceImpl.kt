package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ClientsModel
import com.ag.generalsystemsapi.api.model.PetsScheduleModel
import com.ag.generalsystemsapi.api.model.PoliciesModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.api.model.responses.PolicyRiskResponse
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IPoliciesService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPoliciesRepository
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPolicyRisksRepository
import com.ag.generalsystemsapi.thirdparty.repository.TpPetsScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PoliciesServiceImpl : IPoliciesService {

    @Autowired
    lateinit var tpActivePetPoliciesRepo: TpActivePetPoliciesRepository

    @Autowired
    lateinit var policiesRepo: PoliciesRepository

    @Autowired
    lateinit var productsRepo: ProductsRepository

    @Autowired
    lateinit var clientsRepo: ClientsRepository

    @Autowired
    lateinit var tpActivePetPolicyRisksRepo: TpActivePetPolicyRisksRepository

    @Autowired
    lateinit var bindersRepo: BindersRepository

    @Autowired
    lateinit var subClassesRepo: SubClassesRepository

    @Autowired
    lateinit var policyRisksRepo: PolicyRisksRepository

    @Autowired
    lateinit var tpPetsScheduleRepo: TpPetsScheduleRepository

    @Autowired
    lateinit var petsScheduleRepo: PetsScheduleRepository

    override fun populateAllPetPolicies(){
        for(p in tpActivePetPoliciesRepo.findAll()){
            //Create the client.
            var client = ClientsModel(
                clientCode = p.policyClientCode,
                clientName = p.policyClientName,
                clientOtherNames = p.policyClientOtherNames,
                clientPIN = p.policyClientPIN,
                clientIdNumber = p.policyClientIDNumber,
                clientDateOfBirth = p.policyClientDOB,
                clientGender = p.policyClientGender,
                clientTelephone = p.policyClientTelephone,
                clientPhysicalAddress = p.policyClientPhyAddress,
                clientEmail = p.policyClientEmailAddress,
                clientType = p.policyClientType
            )
            client = clientsRepo.save(client)

            //Create the policy record.
            var policy = PoliciesModel(
                policyBatchNo = p.policyBatchNo,
                policyNumber = p.policyNumber,
                policyProductCode = productsRepo.findByProductCode(p.policyProductCode!!),
                policyClient = client,
                policyCoverFromDate = p.policyCoverFromDate,
                policyCoverToDate = p.policyCoverToDate,
                policyTotalSumInsured = p.policyTotalSumInsured,
                policyTotalNetPremium = p.policyTotalNetPremium,
                policyFrequencyOfPayment = p.policyFrequencyOfPayment,
                policyStatus = p.policyStatus,
                policyCurrentStatus = p.policyCurrentStatus
            )
            policy = policiesRepo.save(policy)

            //Create the Policy Risks.
            for(r in tpActivePetPolicyRisksRepo.findByPolicyRiskPolicyBatchNo(p.policyBatchNo)){
                val risk = PolicyRisksModel(
                    policyRiskCode = r.policyRiskCode,
                    policyRiskBindCode = bindersRepo.findByBindCode(r.policyRiskBindCode),
                    policyRiskCoverType = r.policyRiskCoverType,
                    policyRiskPolicyBatchNo = policy,
                    policyRiskItemDesc = r.policyRiskItemDesc,
                    policyRiskPropertyID = r.policyRiskPropertyID,
                    policyRiskProrata = r.policyRiskProrata,
                    policyRiskSubClassCode = subClassesRepo.findBySubClassCode(r.policyRiskSubClassCode),
                    policyRiskValue = r.policyRiskValue,
                    policyRiskWef = r.policyRiskWef,
                    policyRiskWet = r.policyRiskWet
                )
                policyRisksRepo.save(risk)
            }
        }
    }

    fun constructRiskSummary(riskCode: Long) : PolicyRiskResponse{
        val risk = policyRisksRepo.findById(riskCode)
            .orElseThrow { Exception("Policy Risk not found") }

        val schedule = petsScheduleRepo.findByPetSchIpuCode(risk)
            .orElseThrow { Exception("Policy Risk Schedule not found") }

        return PolicyRiskResponse(
             clientCode = risk.policyRiskPolicyBatchNo?.policyClient?.clientCode,
             clientName = risk.policyRiskPolicyBatchNo?.policyClient?.clientName,
             clientOtherNames = risk.policyRiskPolicyBatchNo?.policyClient?.clientOtherNames,
             clientPIN = risk.policyRiskPolicyBatchNo?.policyClient?.clientPIN,
             clientIdNumber = risk.policyRiskPolicyBatchNo?.policyClient?.clientIdNumber,
             clientDateOfBirth = risk.policyRiskPolicyBatchNo?.policyClient?.clientDateOfBirth,
             clientGender = risk.policyRiskPolicyBatchNo?.policyClient?.clientGender,
             clientTelephone = risk.policyRiskPolicyBatchNo?.policyClient?.clientTelephone,
             clientPhysicalAddress = risk.policyRiskPolicyBatchNo?.policyClient?.clientPhysicalAddress,
             clientEmail = risk.policyRiskPolicyBatchNo?.policyClient?.clientEmail,
             policyBatchNo = risk.policyRiskPolicyBatchNo?.policyBatchNo,
             policyNumber = risk.policyRiskPolicyBatchNo?.policyNumber,
             policyProduct = risk.policyRiskPolicyBatchNo?.policyProductCode?.productDesc,
             policyCoverFromDate = risk.policyRiskPolicyBatchNo?.policyCoverFromDate,
             policyCoverToDate = risk.policyRiskPolicyBatchNo?.policyCoverToDate,
             policyTotalSumInsured = risk.policyRiskPolicyBatchNo?.policyTotalSumInsured,
             policyTotalNetPremium = risk.policyRiskPolicyBatchNo?.policyTotalNetPremium,
             policyFrequencyOfPayment = risk.policyRiskPolicyBatchNo?.policyFrequencyOfPayment,
             policyStatus = risk.policyRiskPolicyBatchNo?.policyStatus,
             policyRiskCode = risk.policyRiskCode,
             policyRiskPropertyID = risk.policyRiskPropertyID,
             policyRiskItemDesc = risk.policyRiskItemDesc,
             policyRiskBinder = risk.policyRiskBindCode?.bindName,
             policyRiskValue = risk.policyRiskValue,
             policyRiskBalance = risk.policyRiskBalance,
             petSchCode = schedule.petSchCode,
             petSchAnimal = schedule.petSchAnimal,
             petSchBreed = schedule.petSchBreed,
             petSchGender = schedule.petSchGender,
             petSchDOB = schedule.petSchDOB,
             petSchWeight = schedule.petSchWeight,
             petSchMicrochipped = schedule.petSchMicrochipped,
             petSchMicroshipNo = schedule.petSchMicroshipNo,
             petSchVaccinated = schedule.petSchVaccinated,
             petSchMedicalConditions = schedule.petSchMedicalConditions,
             petSchSurgeries = schedule.petSchSurgeries,
             petSchMedications = schedule.petSchMedications,
             petSchIllnessSign = schedule.petSchIllnessSign,
             petSchInjureOthers = schedule.petSchInjureOthers,
             petSchCommercial = schedule.petSchCommercial,
             petSchComments = schedule.petSchComments,
             petSchClinicName = schedule.petSchClinicName,
             petSchClinicTel = schedule.petSchClinicTel,
             petSchClinicTown = schedule.petSchClinicTown,
        )

    }

    override fun findPolicyRiskDetails(riskID: String) : Result<PolicyRiskResponse>{
        val risk = policyRisksRepo.findByPolicyRiskPropertyID(riskID)
            .orElseThrow { Exception("Policy not found") }

        return ResultFactory.getSuccessResult(constructRiskSummary(risk.policyRiskCode!!))
    }

    override fun populatePetPolicy(policyBatchNo: Long){
        val p = tpActivePetPoliciesRepo.findByPolicyBatchNo(policyBatchNo)
            .orElseThrow { Exception("Policy not found") }

        //Create the client.
        var client = ClientsModel(
            clientCode = p.policyClientCode,
            clientName = p.policyClientName,
            clientOtherNames = p.policyClientOtherNames,
            clientPIN = p.policyClientPIN,
            clientIdNumber = p.policyClientIDNumber,
            clientDateOfBirth = p.policyClientDOB,
            clientGender = p.policyClientGender,
            clientTelephone = p.policyClientTelephone,
            clientPhysicalAddress = p.policyClientPhyAddress,
            clientEmail = p.policyClientEmailAddress,
            clientType = p.policyClientType
        )
        client = clientsRepo.save(client)

        //Create the policy record.
        var policy = PoliciesModel(
            policyBatchNo = p.policyBatchNo,
            policyNumber = p.policyNumber,
            policyProductCode = productsRepo.findByProductCode(p.policyProductCode!!),
            policyClient = client,
            policyCoverFromDate = p.policyCoverFromDate,
            policyCoverToDate = p.policyCoverToDate,
            policyTotalSumInsured = p.policyTotalSumInsured,
            policyTotalNetPremium = p.policyTotalNetPremium,
            policyFrequencyOfPayment = p.policyFrequencyOfPayment,
            policyStatus = p.policyStatus,
            policyCurrentStatus = p.policyCurrentStatus
        )
        policy = policiesRepo.save(policy)

        //Create the Policy Risks.
        for(r in tpActivePetPolicyRisksRepo.findByPolicyRiskPolicyBatchNo(p.policyBatchNo)){
            var risk = PolicyRisksModel(
                policyRiskCode = r.policyRiskCode,
                policyRiskBindCode = bindersRepo.findByBindCode(r.policyRiskBindCode),
                policyRiskCoverType = r.policyRiskCoverType,
                policyRiskPolicyBatchNo = policy,
                policyRiskItemDesc = r.policyRiskItemDesc,
                policyRiskPropertyID = r.policyRiskPropertyID,
                policyRiskProrata = r.policyRiskProrata,
                policyRiskSubClassCode = subClassesRepo.findBySubClassCode(r.policyRiskSubClassCode),
                policyRiskValue = r.policyRiskValue,
                policyRiskWef = r.policyRiskWef,
                policyRiskWet = r.policyRiskWet
            )
            risk = policyRisksRepo.save(risk)

            for(s in tpPetsScheduleRepo.findByPetSchIpuCode(risk.policyRiskCode!!)){
                val schRisk = policyRisksRepo.findById(s.petSchIpuCode!!)
                    .orElseThrow { Exception("Risk not found") }

                val schedule = PetsScheduleModel(
                    petSchCode = s.petSchCode,
                    petSchIpuCode =  schRisk,
                    petSchAnimal = s.petSchAnimal,
                    petSchBreed = s.petSchBreed,
                    petSchGender = s.petSchGender,
                    petSchDOB = s.petSchDOB,
                    petSchWeight = s.petSchWeight,
                    petSchMicrochipped = s.petSchMicrochipped,
                    petSchMicroshipNo = s.petSchMicroshipNo,
                    petSchVaccinated = s.petSchVaccinated,
                    petSchMedicalConditions = s.petSchMedicalConditions,
                    petSchSurgeries = s.petSchSurgeries,
                    petSchQrCode = s.petSchQrCode,
                    petSchQsrCode = s.petSchQsrCode,
                    petSchMedications = s.petSchMedications,
                    petSchIllnessSign = s.petSchIllnessSign,
                    petSchInjureOthers = s.petSchInjureOthers,
                    petSchCommercial = s.petSchCommercial,
                    petSchComments = s.petSchComments,
                    petSchClinicName = s.petSchClinicName,
                    petSchClinicTel = s.petSchClinicTel,
                    petSchClinicTown = s.petSchClinicTown
                )
                petsScheduleRepo.save(schedule)
            }
        }
    }

    override fun findPolicyRisks() : Result<Iterable<PolicyRisksModel>>{
        return ResultFactory.getSuccessResult(policyRisksRepo.findAll())
    }
}