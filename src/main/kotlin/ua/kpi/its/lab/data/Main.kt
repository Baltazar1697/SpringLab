package ua.kpi.its.lab.data

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ua.kpi.its.lab.data.config.Config
import ua.kpi.its.lab.data.entity.Battery
import ua.kpi.its.lab.data.entity.Car
import ua.kpi.its.lab.data.svc.EntityService
import ua.kpi.its.lab.data.svc.impl.CarService
import java.util.Date

fun main() {
    val context = AnnotationConfigApplicationContext(Config::class.java)

    val carService = context.getBean(CarService::class.java)

    val battery = Battery(null, "ABC123", "BatteryBrand1", "Type1", 5000, Date(), 3, true);
    // Creating of 5 car entities
    val car1 = Car(null, "Toyota", "Camry", "Toyota Motor Corporation", Date(), 200, 25000.0, true, battery.copy())
    val car2 = Car(null, "Honda", "Civic", "Honda Motor Co., Ltd.", Date(), 180, 20000.0, false, battery.copy())
    val car3 = Car(null, "Ford", "Focus", "Ford Motor Company", Date(), 190, 22000.0, true, battery.copy())
    val car4 = Car(null, "BMW", "3 Series", "Bayerische Motoren Werke AG", Date(), 210, 30000.0, true, battery.copy())
    val car5 = Car(null, "Mercedes-Benz", "C-Class", "Mercedes-Benz AG", Date(), 220, 35000.0, false, battery.copy())

    // Saving car entities into DB
    carService.save(car1)
    carService.save(car2)
    carService.save(car3)
    carService.save(car4)
    carService.save(car5)

    // Getting car with id = 3
    val receivedCar3 = carService.findById(3)
    println("Car with id 3: $receivedCar3")

    // Deleting car with id = 4
    carService.deleteById(4)
    println("Car with id 4 has been deleted")

    val car4Exists = carService.findById(4) == null
    println("Car with id 4 wasn't found in database: $car4Exists")
}
