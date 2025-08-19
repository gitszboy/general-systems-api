package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "service_request_areas")
class ServiceRequestAreasModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sra_id", nullable = false)
    var servReqAreaId: Long,

    @Column(name = "sra_name", nullable = true)
    var servReqAreaName: String?,

    @Column(name = "sra_active", nullable = true)
    var servReqAreaActive: Boolean,
)