package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpSubClassCoverTypesSectionsModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpSubClassCoverTypesSectionsRepository : JpaRepository<TpSubClassCoverTypesSectionsModel, Long> {

    fun findByScCoverSectSubClassCoverTypeCode(scCoverSectSubClassCoverTypeCode: Long?) : Iterable<TpSubClassCoverTypesSectionsModel>
}