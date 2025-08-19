package com.ag.generalsystemsapi.thirdparty.repository

import com.ag.generalsystemsapi.thirdparty.model.TpBindersModel
import org.springframework.data.jpa.repository.JpaRepository

interface TpBindersRepository : JpaRepository<TpBindersModel, Long> {

    fun findByBindSubClassCode(bindSubClassCode: Long) : Iterable<TpBindersModel>

}