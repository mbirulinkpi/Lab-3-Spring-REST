package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.DrugRequest
import ua.kpi.its.lab.rest.dto.DrugResponse
import ua.kpi.its.lab.rest.entity.Drugs
import ua.kpi.its.lab.rest.repository.DrugsRepository
import ua.kpi.its.lab.rest.svc.DrugsService
@Service
class DrugsServiceImpl(private val drugsRepository: DrugsRepository) : DrugsService {
    override fun createDrugs(drugsRequest: DrugRequest): DrugResponse {
        val drugs = Drugs(name = drugsRequest.name, description = drugsRequest.description)
        val savedDrugs = drugsRepository.save(drugs)
        return DrugResponse.fromEntity(savedDrugs)
    }

    override fun getDrugsById(id: Long): DrugResponse {
        val drugs = drugsRepository.findById(id).orElseThrow()
        return DrugResponse.fromEntity(drugs)
    }

    override fun updateDrugs(id: Long, drugsRequest: DrugRequest): DrugResponse {
        val drugs = drugsRepository.findById(id).orElseThrow()
        drugs.name = drugsRequest.name
        drugs.description = drugsRequest.description
        val updatedDrugs = drugsRepository.save(drugs)
        return DrugResponse.fromEntity(updatedDrugs)
    }

    override fun deleteDrugs(id: Long) {
        drugsRepository.deleteById(id)
    }
}