package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpAccountTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpAccountTypesRepository : JpaRepository<TpAccountTypesModel, Long> {
}