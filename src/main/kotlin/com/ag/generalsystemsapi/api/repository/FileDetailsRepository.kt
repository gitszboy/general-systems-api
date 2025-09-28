package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.FileDetailsModel
import org.springframework.data.jpa.repository.JpaRepository

interface FileDetailsRepository : JpaRepository<FileDetailsModel, Long> {
}