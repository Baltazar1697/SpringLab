package ua.kpi.its.lab.data.svc.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.kpi.its.lab.data.entity.Battery
import ua.kpi.its.lab.data.entity.Car
import ua.kpi.its.lab.data.repo.BatteryRepository
import ua.kpi.its.lab.data.repo.CarRepository
import ua.kpi.its.lab.data.svc.EntityService

@Service
class CarService @Autowired constructor(
    private val carRepository: CarRepository,
    private val batteryRepository: BatteryRepository
) : EntityService<Car> {

    /**
     * This method saves input [Car] entity into DB
     *
     * @param [entity] [Car] object to be saved in DB
     * @return saved [Car] object
     */
    override fun save(entity: Car): Car {
        return carRepository.save(entity)
    }

    /**
     * This method returns [Car] object from DB by input id
     *
     * @param [id] [Car] object id in DB
     * @return [Battery] object or [null]
     */
    override fun findById(id: Long): Car? {
        return carRepository.findById(id).orElse(null)
    }

    /**
     * This method updates [Car] entity from DB
     *
     * @param [entity] [Car] object to be updated in DB
     * @return updated [Car] object
     */
    override fun update(entity: Car): Car {
        return carRepository.save(entity)
    }

    /**
     * This deletes [Car] entity from DB
     *
     * @param [id] [Car] object id in DB
     * @return updated [Car] object
     */
    override fun deleteById(id: Long) {
        carRepository.deleteById(id)
    }
}
