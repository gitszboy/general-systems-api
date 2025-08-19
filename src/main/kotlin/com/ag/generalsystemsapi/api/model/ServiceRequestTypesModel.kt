package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "service_request_types")
class ServiceRequestTypesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srt_id", nullable = false)
    var servReqTypeId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "srt_sra_id")
    var servReqTypeArea: ServiceRequestAreasModel? = null,

    @Column(name = "srt_type", nullable = false)
    var servReqType: String,

    @Column(name = "srt_assign_to", nullable = false)
    var servReqTypeAssignTo: String,

    @Column(name = "srt_active", nullable = true)
    var servReqTypeActive: Boolean,
)