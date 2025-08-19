package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpClassesRepository : JpaRepository<TpClassesModel, Long> {
}