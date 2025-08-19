package com.ag.generalsystemsapi.api.model.payload

class prospectPetsRequest  (
    var prospectPetCode: Long? = null,
    var prospectPetProspect: Long,
    var prospectPetType: String? = null,
    var prospectPetBreed: String?,
    var prospectPetGender: String? = null,
    var prospectPetDateOfBirth: String? = null,
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
)