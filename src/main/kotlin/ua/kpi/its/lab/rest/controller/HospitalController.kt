package ua.kpi.its.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.HospitalRequestDto
import ua.kpi.its.lab.rest.dto.HospitalResponseDto
import ua.kpi.its.lab.rest.exception.ResourceNotFoundException
import javax.validation.Valid


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
    open fun getAllHospitals(): ResponseEntity<MutableList<HospitalResponseDto?>?>? {
        val hospitalResponseDtos: kotlin.collections.List<HospitalResponseDto> = hospitalService.getAllHospitals()
        return ResponseEntity.ok<kotlin.collections.List<HospitalResponseDto>>(hospitalResponseDtos)
    }

    @PostMapping("/")
    fun createHospital(@Valid @RequestBody hospitalRequestDto: HospitalRequestDto?): ResponseEntity<HospitalResponseDto>? {
        val hospitalResponseDto: HospitalResponseDto = hospitalService.createHospital(hospitalRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body<HospitalResponseDto>(hospitalResponseDto)
    }

    @PutMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateHospital(
        @PathVariable id: Long,
        @Valid @RequestBody hospitalRequestDto: HospitalRequestDto?
    ): ResponseEntity<HospitalResponseDto>? {
        val hospitalResponseDto: HospitalResponseDto = hospitalService.updateHospital(id, hospitalRequestDto)
            ?: throw ResourceNotFoundException("Hospital with id $id not found")
        return ResponseEntity.ok<HospitalResponseDto>(hospitalResponseDto)
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