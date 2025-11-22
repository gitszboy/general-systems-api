package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "subcl_covt_sections")
class SubClassCoverTypesSectionsModel  (
    @Id
    @Column(name = "scvts_code", nullable = false)
    var scCoverSectCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scvts_scl_code", nullable = true)
    var scCoverSectSubClassCode: SubClassesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scvts_covt_code", nullable = true)
    var scCoverSectCoverTypeCode: CoverTypesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scvts_sclcovt_code", nullable = true)
    var scCoverSectSubClassCoverTypeCode: SubClassCoverTypesModel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scvts_sect_code", nullable = true)
    var scCoverSectSectionCode: SectionsModel? = null,

)