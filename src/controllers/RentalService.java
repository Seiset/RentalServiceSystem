package controllers;

import models.Car;
import repositories.CarRepository;
import repositories.RentalRepository;

import java.time.LocalDate;

public class RentalService implements RentalRepository {

    private CarRepository carRepository;
    private RentalRepository rentalRepository;

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
        rentalRepository.rentalTime();

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


    @Override
    public String rentalTime() {
        LocalDate registration = LocalDate.now();
        LocalDate expiry = registration.plusDays(7);

        return "Registration date: " + registration +
                ", Expiry date: " + expiry;
    }
}