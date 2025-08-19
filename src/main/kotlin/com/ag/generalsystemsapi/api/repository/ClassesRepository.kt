package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.ClassesModel
import org.springframework.data.jpa.repository.JpaRepository

interface ClassesRepository : JpaRepository<ClassesModel, Long> {
}