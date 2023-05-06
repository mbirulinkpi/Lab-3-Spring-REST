package ua.kpi.its.lab.rest.entity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "hospital")
data class Hospital(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val address: String,
    val profile: String,
    val openingDate: LocalDate,
    val numOfDepartments: Int,
    val numOfBeds: Int,
    val hasPediatricsDepartment: Boolean,
    @OneToMany(mappedBy = "hospital", cascade = [CascadeType.ALL], orphanRemoval = true)
    val drugs: List<Drugs>
) : Comparable<Hospital> {
    override fun compareTo(other: Hospital): Int {
        val cmp = name.compareTo(other.name)
        return if (cmp != 0) cmp else numOfBeds.compareTo(other.numOfBeds)
    }
}