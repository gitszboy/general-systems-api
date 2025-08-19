package com.ag.generalsystemsapi.api.service

import com.ag.generalsystemsapi.api.model.PolicyRisksModel
import com.ag.generalsystemsapi.api.util.Result

interface IPoliciesService {
    fun populateAllPetPolicies()
    fun findPolicyRisks() : Result<Iterable<PolicyRisksModel>>
}