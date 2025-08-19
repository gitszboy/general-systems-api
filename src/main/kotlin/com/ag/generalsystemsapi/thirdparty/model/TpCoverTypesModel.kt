package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "gin_cover_types")
class TpCoverTypesModel (
    @Id
    @Column(name = "covt_code", nullable = false)
    var coverCode: Long,

    @Column(name = "covt_sht_desc", nullable = false)
    var coverShtDesc: String,

    @Column(name = "covt_desc", nullable = false)
    var coverDesc: String,

    @Column(name = "covt_details", nullable = false)
    var coverDetails: String,

)