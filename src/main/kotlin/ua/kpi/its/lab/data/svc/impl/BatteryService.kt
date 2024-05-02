import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.kpi.its.lab.data.entity.Battery
import ua.kpi.its.lab.data.repo.BatteryRepository
import ua.kpi.its.lab.data.svc.EntityService

@Service
class BatteryService @Autowired constructor(
    private val batteryRepository: BatteryRepository
) : EntityService<Battery> {

    /**
     * This method saves input [Battery] entity into DB
     *
     * @param [entity] [Battery] object to be saved in DB
     * @return saved [Battery] object
     */
    override fun save(entity: Battery): Battery {
        return batteryRepository.save(entity)
    }

    /**
     * This method returns [Battery] object from DB by input id
     *
     * @param [id] [Battery] object id in DB
     * @return [Battery] object or [null]
     */
    override fun findById(id: Long): Battery? {
        return batteryRepository.findById(id).orElse(null)
    }

    /**
     * This method [Battery] updates entity from DB
     *
     * @param [entity] [Battery] object to be updated in DB
     * @return updated [Battery] object
     */
    override fun update(entity: Battery): Battery {
        return batteryRepository.save(entity)
    }

    /**
     * This deletes [Battery] entity from DB
     *
     * @param [id] [Battery] object id in DB
     * @return updated [Battery] object
     */
    override fun deleteById(id: Long) {
        batteryRepository.deleteById(id)
    }
}
