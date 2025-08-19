package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpActivePetPoliciesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpActivePetPoliciesRepository : JpaRepository<TpActivePetPoliciesModel, Long> {
}