package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActivePetPoliciesModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TpActivePetPoliciesRepository : JpaRepository<TpActivePetPoliciesModel, Long> {
    fun findByPolicyBatchNo(policyBatchNo: Long) : Optional<TpActivePetPoliciesModel>
}