package ua.kpi.its.lab.data.entity

import java.util.Date

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Battery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val model: String,
    val manufacturer: String,
    val type: String,
    val capacity: Int,
    val productionDate: Date,
    val chargeTime: Int,
    val fastChargeAvailable: Boolean
) : Comparable<Battery> {

    override fun compareTo(other: Battery): Int {
        return this.id!!.compareTo(other.id!!)
    }
}