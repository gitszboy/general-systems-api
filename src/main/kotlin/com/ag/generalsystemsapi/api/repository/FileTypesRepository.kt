package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.FileTypesModel
import org.springframework.data.jpa.repository.JpaRepository

interface FileTypesRepository : JpaRepository<FileTypesModel, Long> {
    fun findByFileTypeArea(fileTypeArea: String) : Iterable<FileTypesModel>
}