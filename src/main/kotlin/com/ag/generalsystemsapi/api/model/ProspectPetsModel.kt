package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "client_pets")
class ProspectPetsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prpt_code")
    var prospectPetCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prpt_prosp_code", nullable = true)
    var prospectPetProspect: ProspectsModel? = null,

    @Column(name = "prpt_pet_type", nullable = true)
    var prospectPetType: String? = null,

    @Column(name = "prpt_breed", nullable = true)
    var prospectPetBreed: String? = null,

    @Column(name = "prpt_gender", nullable = true)
    var prospectPetGender: String? = null,

    @Column(name = "prpt_dob", nullable = true)
    var prospectPetDateOfBirth: String? = null,

    @Column(name = "prpt_weight", nullable = true)
    var prospectPetWeight: String? = null,

    @Column(name = "prpt_microchip_avail", nullable = true)
    var prospectPetMicroAvail: String? = null,

    @Column(name = "prpt_microchip_number", nullable = true)
    var prospectPetMicroNumber: String? = null,

    @Column(name = "prpt_sterilized", nullable = true)
    var prospectPetSterilized: String? = null,

    @Column(name = "prpt_vaccinated", nullable = true)
    var prospectPetVaccinated: String? = null,

    @Column(name = "prpt_medical_conditions", nullable = true)
    var prospectPetMedicalConditions: String? = null,

    @Column(name = "prpt_medical_surgeries", nullable = true)
    var prospectPetMedicalSurgeries: String? = null,

    @Column(name = "prpt_medical_medications", nullable = true)
    var prospectPetMedicalMedications: String? = null,

    @Column(name = "prpt_medical_seizures", nullable = true)
    var prospectPetMedicalSeizures: String? = null,

    @Column(name = "prpt_injure_others", nullable = true)
    var prospectPetInjureOthers: String? = null,

    @Column(name = "prpt_breeding", nullable = true)
    var prospectPetBreeding: String? = null,

    @Column(name = "prpt_comments", nullable = true)
    var prospectPetComments: String? = null,

)