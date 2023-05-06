package ua.kpi.its.lab.rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.DrugRequest
import ua.kpi.its.lab.rest.dto.DrugResponse
import ua.kpi.its.lab.rest.svc.impl.DrugsServiceImpl

@RestController
@RequestMapping("/drugs")
class DrugController(private val drugsService: DrugsServiceImpl) {
    @PostMapping
    fun createDrugs(@RequestBody drugsRequest: DrugRequest): ResponseEntity<DrugResponse> {
        val drugsResponse = drugsService.createDrugs(drugsRequest)
        return ResponseEntity(drugsResponse, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getDrugsById(@PathVariable id: Long): ResponseEntity<DrugResponse> {
        val drugsResponse = drugsService.getDrugsById(id)
        return ResponseEntity(drugsResponse, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateDrugs(@PathVariable id: Long, @RequestBody drugsRequest: DrugRequest): ResponseEntity<DrugResponse> {
        val drugsResponse = drugsService.updateDrugs(id, drugsRequest)
        return ResponseEntity(drugsResponse, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteDrugs(@PathVariable id: Long): ResponseEntity<Void> {
        drugsService.deleteDrugs(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}