package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "system_areas")
class SystemAreasModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sa_id", nullable = false)
    var sysAreaId: Long,

    @Column(name = "sa_name", nullable = false)
    var sysAreaName: String,

    @Column(name = "sa_module", nullable = false)
    var sysAreaModule: String,
)