package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "cover_types")
class CoverTypesModel  (
    @Id
    @Column(name = "covt_code", nullable = true)
    var coverCode: Long? = null,

    @Column(name = "covt_sht_desc", nullable = true)
    var coverShtDesc: String? = null,

    @Column(name = "covt_desc", nullable = true)
    var coverDesc: String? = null,

    @Column(name = "covt_details", nullable = true)
    var coverDetails: String? = null,

)