package ua.kpi.its.lab.data.svc

interface EntityService<T> {
    fun save(entity: T): T
    fun findById(id: Long): T?
    fun update(entity: T): T
    fun deleteById(id: Long)
}
