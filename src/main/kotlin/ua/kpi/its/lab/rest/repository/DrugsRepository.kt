package ua.kpi.its.lab.rest.repository

import ua.kpi.its.lab.rest.entity.Drugs
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DrugsRepository : JpaRepository<Drugs, Long>