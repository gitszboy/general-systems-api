package com.ag.generalsystemsapi.thirdparty.model

import lombok.Getter
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Setter
@Getter
@Entity
@Table(name = "gin_subcl_covt_sections")
class TpSubClassCoverTypesSectionsModel  (
    @Id
    @Column(name = "scvts_code", nullable = false)
    var scCoverSectCode: Long,

    @Column(name = "scvts_scl_code", nullable = false)
    var scCoverSectSubClassCode: Long?,

    @Column(name = "scvts_covt_code", nullable = false)
    var scCoverSectCoverTypeCode: Long?,

    @Column(name = "scvts_sclcovt_code", nullable = false)
    var scCoverSectSubClassCoverTypeCode: Long?,

    @Column(name = "scvts_sect_code", nullable = false)
    var scCoverSectSectionCode: Long?,

)