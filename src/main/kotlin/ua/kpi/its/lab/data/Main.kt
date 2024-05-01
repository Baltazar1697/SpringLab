package ua.kpi.its.lab.data

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ua.kpi.its.lab.data.config.Config
import ua.kpi.its.lab.data.svc.EntityService

fun main() {
    val context = AnnotationConfigApplicationContext(Config::class.java)
    // Your code here
    // For example:
    // val service = context.getBean(EntityService::class.java)
}