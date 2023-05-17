package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.DrugRequest
import ua.kpi.its.lab.rest.dto.DrugResponse
import ua.kpi.its.lab.rest.entity.Drugs
import ua.kpi.its.lab.rest.repository.DrugsRepository
import ua.kpi.its.lab.rest.svc.DrugsService
import org.springframework.security.access.prepost.PreAuthorize
@Service
class DrugsServiceImpl(private val drugsRepository: DrugsRepository) : DrugsService {
    @PreAuthorize("hasAuthority('EDITOR')")
    override fun createDrugs(drugsRequest: DrugRequest): DrugResponse {
        val drugs = Drugs(name = drugsRequest.name, description = drugsRequest.description)
        val savedDrugs = drugsRepository.save(drugs)
        return DrugResponse.fromEntity(savedDrugs)
    }

    @PreAuthorize("permitAll()")
    override fun getDrugsById(id: Long): DrugResponse {
        val drugs = drugsRepository.findById(id).orElseThrow()
        return DrugResponse.fromEntity(drugs)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun updateDrugs(id: Long, drugsRequest: DrugRequest): DrugResponse {
        val drugs = drugsRepository.findById(id).orElseThrow()
        drugs.name = drugsRequest.name
        drugs.description = drugsRequest.description
        val updatedDrugs = drugsRepository.save(drugs)
        return DrugResponse.fromEntity(updatedDrugs)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun deleteDrugs(id: Long) {
        drugsRepository.deleteById(id)
    }
}