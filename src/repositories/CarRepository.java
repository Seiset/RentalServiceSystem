package repositories;

import controllers.Car;
import java.util.List;

public interface CarRepository {

    void addCar(Car car);

    void updateCar(Car updatedCar);

    void deleteCar(int id);

    List<Car> getAllCars();

    Car findById(int id);
}