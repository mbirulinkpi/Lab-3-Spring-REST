package ua.kpi.its.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.HospitalRequest
import ua.kpi.its.lab.rest.dto.HospitalResponse
import ua.kpi.its.lab.rest.exception.ResourceNotFoundException


@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService)
    {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDto> getHospitalById(@PathVariable Long id) throws ResourceNotFoundException
    {
        HospitalResponseDto hospitalResponseDto = hospitalService . getHospitalById (id);
        if (hospitalResponseDto == null) {
            throw new ResourceNotFoundException ("Hospital with id " + id + " not found");
        }
        return ResponseEntity.ok(hospitalResponseDto);
    }

    @GetMapping("/")
    open fun getAllHospitals(): ResponseEntity<MutableList<HospitalResponse?>?>? {
        val hospitalResponseDtos: kotlin.collections.List<HospitalResponse> = hospitalService.getAllHospitals()
        return ResponseEntity.ok<kotlin.collections.List<HospitalResponse>>(hospitalResponseDtos)
    }

    @PostMapping("/")
    fun createHospital(@Valid @RequestBody hospitalRequestDto: HospitalRequest?): ResponseEntity<HospitalResponse>? {
        val hospitalResponseDto: HospitalResponse = hospitalService.createHospital(hospitalRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body<HospitalResponse>(hospitalResponseDto)
    }

    @PutMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateHospital(
        @PathVariable id: Long,
        @Valid @RequestBody hospitalRequestDto: HospitalRequest?
    ): ResponseEntity<HospitalResponse>? {
        val hospitalResponseDto: HospitalResponse = hospitalService.updateHospital(id, hospitalRequestDto)
            ?: throw ResourceNotFoundException("Hospital with id $id not found")
        return ResponseEntity.ok<HospitalResponse>(hospitalResponseDto)
    }

    @DeleteMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteHospital(@PathVariable id: Long): ResponseEntity<Void?>? {
        val isDeleted: Boolean = hospitalService.deleteHospital(id)
        if (!isDeleted) {
            throw ResourceNotFoundException("Hospital with id $id not found")
        }
        return ResponseEntity.noContent().build()
    }
}