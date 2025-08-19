package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActivePetPolicyRisksModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpActivePetPolicyRisksRepository : JpaRepository<TpActivePetPolicyRisksModel, Long> {

    fun findByPolicyRiskPolicyBatchNo(policyRiskPolicyBatchNo: Long?) : Iterable<TpActivePetPolicyRisksModel>
}