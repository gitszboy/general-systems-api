package com.ag.generalsystemsapi.api.model.payload

import java.util.*

class ProspectPetsRequest  (
    var prospectPetCode: Long? = null,
    var prospectPetName: String? = null,
    var prospectPetType: String? = null,
    var prospectPetBreed: String?,
    var prospectPetGender: String? = null,
    var prospectPetDateOfBirth: Date? = null,
    var prospectPetWeight: String? = null,
    var prospectPetMicroAvail: String? = null,
    var prospectPetMicroNumber: String? = null,
    var prospectPetSterilized: String? = null,
    var prospectPetVaccinated: String? = null,
    var prospectPetMedicalConditions: String? = null,
    var prospectPetMedicalSurgeries: String? = null,
    var prospectPetMedicalMedications: String? = null,
    var prospectPetMedicalSeizures: String? = null,
    var prospectPetInjureOthers: String? = null,
    var prospectPetBreeding: String? = null,
    var prospectPetComments: String? = null,
    var prospectBinderCode: Long,
    var prospectPetAnnualLimit: Double? = null,
)