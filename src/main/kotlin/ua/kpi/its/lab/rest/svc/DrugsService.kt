package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.DrugRequest
import ua.kpi.its.lab.rest.dto.DrugResponse
interface DrugsService {
    fun createDrugs(drugsRequest: DrugRequest): DrugResponse
    fun getDrugsById(id: Long): DrugResponse
    fun updateDrugs(id: Long, drugsRequest: DrugRequest): DrugResponse
    fun deleteDrugs(id: Long)
}