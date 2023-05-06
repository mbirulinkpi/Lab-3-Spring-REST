package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.HospitalRequest
import ua.kpi.its.lab.rest.dto.HospitalResponse

interface HospitalService {
    fun createHospital(hospitalRequest: HospitalRequest): HospitalResponse
    fun getHospitalById(id: Long): HospitalResponse
    fun updateHospital(id: Long, hospitalRequest: HospitalRequest): HospitalResponse
    fun deleteHospital(id: Long)
}