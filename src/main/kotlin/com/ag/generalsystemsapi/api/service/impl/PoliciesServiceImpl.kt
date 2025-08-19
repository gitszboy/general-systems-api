package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ClientsModel
import com.ag.generalsystemsapi.api.model.PoliciesModel
import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.api.repository.*
import com.ag.generalsystemsapi.api.service.IPoliciesService
import com.ag.generalsystemsapi.api.util.Result
import com.ag.generalsystemsapi.api.util.ResultFactory
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPoliciesRepository
import com.ag.generalsystemsapi.thirdparty.repository.TpActivePetPolicyRisksRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

    override fun findPolicyRisks() : Result<Iterable<PolicyRisksModel>>{
        return ResultFactory.getSuccessResult(policyRisksRepo.findAll())
    }
}