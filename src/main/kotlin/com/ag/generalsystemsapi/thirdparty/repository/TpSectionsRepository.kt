package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSectionsModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSectionsRepository : JpaRepository<TpSectionsModel, Long> {
}