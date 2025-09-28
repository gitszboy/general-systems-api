package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "file_upload_types")
class FileTypesModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ft_code")
    var fileTypeCode: Long? = null,

    @Column(name = "ft_type_name", nullable = true)
    var fileTypeName: String? = null,

    @Column(name = "ft_area", nullable = true)
    var fileTypeArea: String? = null,

    @Column(name = "ft_active", nullable = true)
    var fileTypeActive: Boolean? = null,
)