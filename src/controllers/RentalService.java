package controllers;

import models.Car;
import repositories.CarRepository;

public class RentalService {

    private CarRepository carRepository;

    public RentalService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void rentCar(int carId) {
        Car car = carRepository.findById(carId);

        if (car == null) {
            System.out.println("Car not found");
            return;
        }

        if (car.isRented()) {
            System.out.println("Car is already rented");
            return;
        }

        car.setRented(true);
        carRepository.updateCar(car);

        System.out.println("Car rented successfully");
    }

    public void returnCar(int carId) {
        Car car = carRepository.findById(carId);

        if (car == null) {
            System.out.println("Car not found");
            return;
        }

        if (!car.isRented()) {
            System.out.println("Car is not rented");
            return;
        }

        car.setRented(false);
        carRepository.updateCar(car);

        System.out.println("Car returned successfully");
    }
}
