package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "pets_sch")
class PetsScheduleModel (
    @Id
    @Column(name = "ptvs_code", nullable = true)
    var petSchCode: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ptvs_ipu_code", nullable = true)
    var petSchIpuCode: PolicyRisksModel? = null,

    @Column(name = "ptvs_animal", nullable = true)
    var petSchAnimal: String? = null,

    @Column(name = "ptvs_breed", nullable = true)
    var petSchBreed: String? = null,

    @Column(name = "ptvs_gender", nullable = true)
    var petSchGender: String? = null,

    @Column(name = "ptvs_date_of_birth", nullable = true)
    var petSchDOB: Date? = null,

    @Column(name = "ptvs_weight", nullable = true)
    var petSchWeight: String? = null,

    @Column(name = "ptvs_microchipped", nullable = true)
    var petSchMicrochipped: String? = null,

    @Column(name = "ptvs_microchip_no", nullable = true)
    var petSchMicroshipNo: String? = null,

    @Column(name = "ptvs_vaccinated", nullable = true)
    var petSchVaccinated: String? = null,

    @Column(name = "ptvs_med_conditions", nullable = true)
    var petSchMedicalConditions: String? = null,

    @Column(name = "ptvs_surgeries", nullable = true)
    var petSchSurgeries: String? = null,

    @Column(name = "ptvs_qr_code", nullable = true)
    var petSchQrCode: Long? = null,

    @Column(name = "ptvs_qsr_code", nullable = true)
    var petSchQsrCode: Long? = null,

    @Column(name = "ptvs_medications", nullable = true)
    var petSchMedications: String? = null,

    @Column(name = "ptvs_illness_sign", nullable = true)
    var petSchIllnessSign: String? = null,

    @Column(name = "ptvs_injure_others", nullable = true)
    var petSchInjureOthers: String? = null,

    @Column(name = "ptvs_commercial", nullable = true)
    var petSchCommercial: String? = null,

    @Column(name = "ptvs_comments", nullable = true)
    var petSchComments: String? = null,

    @Column(name = "ptvs_clinic_name", nullable = true)
    var petSchClinicName: String? = null,

    @Column(name = "ptvs_clinic_tel", nullable = true)
    var petSchClinicTel: String? = null,

    @Column(name = "ptvs_clinic_town", nullable = true)
    var petSchClinicTown: String? = null,

)