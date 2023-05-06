package ua.kpi.its.lab.rest.entity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="drugs")
data class Drugs(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val description: String,
    val manufacturer: String,
    val price: Double,
    val expirationDate: LocalDate,
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    val hospital: Hospital
) : Comparable<Drugs> {
    override fun compareTo(other: Drugs): Int {
        val cmp = name.compareTo(other.name)
        return if (cmp != 0) cmp else price.compareTo(other.price)
    }
}