package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.ProspectPetsModel
import com.ag.generalsystemsapi.api.model.ProspectsModel
import com.ag.generalsystemsapi.api.model.payload.prospectPetsRequest
import com.ag.generalsystemsapi.api.model.payload.ProspectsRequest
import com.ag.generalsystemsapi.api.repository.ClientPetsRepository
import com.ag.generalsystemsapi.api.repository.ProspectsRepository
import com.ag.generalsystemsapi.api.service.IQuotationsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuotationsServiceImpl : IQuotationsService {

    @Autowired
    lateinit var prospectsRepo: ProspectsRepository

    @Autowired
    lateinit var clientPetsRepo: ClientPetsRepository

    fun saveProspect(prospect: ProspectsRequest) : ProspectsModel{
        val newProspect = ProspectsModel(
             prospectCode = prospect.prospectCode,
             prospectName = prospect.prospectName,
             prospectDateOfBirth = prospect.prospectDateOfBirth,
             prospectTelephone = prospect.prospectTelephone,
             prospectEmail = prospect.prospectEmail,
             prospectIdNumber = prospect.prospectIdNumber,
             prospectPhysicalAddress = prospect.prospectPhysicalAddress,
             prospectOccupation = prospect.prospectOccupation,
        )
        return prospectsRepo.save(newProspect)
    }

    fun saveProspectPet(pet: prospectPetsRequest) : ProspectPetsModel {
        val client = prospectsRepo.findByProspectCode(pet.prospectPetProspect)
            .orElseThrow {Exception("Prospect not found") }

        val newPet = ProspectPetsModel(
            prospectPetCode = pet.prospectPetCode,
            prospectPetProspect = client,
            prospectPetType = pet.prospectPetType,
            prospectPetBreed = pet.prospectPetBreed,
            prospectPetGender = pet.prospectPetGender,
            prospectPetDateOfBirth = pet.prospectPetDateOfBirth,
            prospectPetWeight = pet.prospectPetWeight,
            prospectPetMicroAvail = pet.prospectPetMicroAvail,
            prospectPetMicroNumber = pet.prospectPetMicroNumber,
            prospectPetSterilized = pet.prospectPetSterilized,
            prospectPetVaccinated = pet.prospectPetVaccinated,
            prospectPetMedicalConditions = pet.prospectPetMedicalConditions,
            prospectPetMedicalSurgeries = pet.prospectPetMedicalSurgeries,
            prospectPetMedicalMedications = pet.prospectPetMedicalMedications,
            prospectPetMedicalSeizures = pet.prospectPetMedicalSeizures,
            prospectPetInjureOthers = pet.prospectPetInjureOthers,
            prospectPetBreeding = pet.prospectPetBreeding,
            prospectPetComments = pet.prospectPetComments
        )
        return clientPetsRepo.save(newPet)
    }
}