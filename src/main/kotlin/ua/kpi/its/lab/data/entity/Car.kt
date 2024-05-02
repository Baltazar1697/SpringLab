package ua.kpi.its.lab.data.entity

import java.util.Date

import jakarta.persistence.*

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val brand: String,
    val model: String,
    val manufacturer: String,
    val productionDate: Date,
    val maxSpeed: Int,
    val price: Double,
    val absAvailable: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    val battery: Battery
) : Comparable<Car> {

    override fun compareTo(other: Car): Int {
        return this.id!!.compareTo(other.id!!)
    }
}