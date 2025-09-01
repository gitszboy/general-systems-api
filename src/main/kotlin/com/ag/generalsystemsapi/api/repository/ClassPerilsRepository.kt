package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClassPerilsModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClassPerilsRepository : JpaRepository<ClassPerilsModel, Long> {
}