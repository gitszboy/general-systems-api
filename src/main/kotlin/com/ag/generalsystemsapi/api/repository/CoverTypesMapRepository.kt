package com.ag.generalsystemsapi.api.repository

import com.ag.generalsystemsapi.api.model.CoverTypesMapModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface CoverTypesMapRepository : JpaRepository<CoverTypesMapModel, Long> {
    @Query(value = "SELECT r FROM CoverTypesMapModel r " +
            "WHERE r.coverMapAnimal = UPPER(:animal) " +
            "AND  (:age) BETWEEN IFNULL(r.coverMapMinAge,0) AND IFNULL(r.coverMapMaxAge,0) ")
    fun findCoverMapping(animal: String, age: Long) : Optional<CoverTypesMapModel>
}