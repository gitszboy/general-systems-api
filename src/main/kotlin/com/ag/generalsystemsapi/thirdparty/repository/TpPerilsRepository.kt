package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpPerilsModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpPerilsRepository : JpaRepository<TpPerilsModel, Long> {
}