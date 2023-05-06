package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.HospitalRequest
import ua.kpi.its.lab.rest.dto.HospitalResponse
import ua.kpi.its.lab.rest.entity.Hospital
import ua.kpi.its.lab.rest.repository.HospitalRepository
import ua.kpi.its.lab.rest.svc.HospitalService

@Service
class HospitalServiceImpl(private val hospitalRepository: HospitalRepository) : HospitalService {
    override fun createHospital(hospitalRequest: HospitalRequest): HospitalResponse {
        val hospital = Hospital(name = hospitalRequest.name, address = hospitalRequest.address)
        val savedHospital = hospitalRepository.save(hospital)
        return HospitalResponse.fromEntity(savedHospital)
    }

    override fun getHospitalById(id: Long): HospitalResponse {
        val hospital = hospitalRepository.findById(id).orElseThrow()
        return HospitalResponse.fromEntity(hospital)
    }

    override fun updateHospital(id: Long, hospitalRequest: HospitalRequest): HospitalResponse {
        val hospital = hospitalRepository.findById(id).orElseThrow()
        hospital.name = hospitalRequest.name
        hospital.address = hospitalRequest.address
        val updatedHospital = hospitalRepository.save(hospital)
        return HospitalResponse.fromEntity(updatedHospital)
    }

    override fun deleteHospital(id: Long) {
        hospitalRepository.deleteById(id)
    }
}