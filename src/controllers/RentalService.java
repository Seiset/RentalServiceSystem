package controllers;

import models.Car;
import models.Rental;
import models.Role;
import models.User;
import repositories.CarRepository;
import repositories.RentalRepositoryImplication;
import repositories.UserRepository;

public class RentalService {

    private final CarRepository carRepository;
    private final RentalRepositoryImplication rentalRepository;
    private final UserRepository userRepository;  // ← added to check user role

    public RentalService(CarRepository carRepository,
                         RentalRepositoryImplication rentalRepository,
                         UserRepository userRepository) {
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public void rentCar(int carId, int userId) {
        Car car = carRepository.findById(carId);
        User user = userRepository.findById(userId);

        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        if (car.isRented()) {
            System.out.println("Car is already rented.");
            return;
        }

        // All users can rent cars (you can restrict later if needed)
        car.setRented(true);
        carRepository.updateCar(car);

        Rental rental = new Rental(0, userId, carId);
        rentalRepository.save(rental);

        System.out.println("Car rented successfully by " + user.getName() + " (" + user.getRole().getDisplayName() + ")");
    }

    public void returnCar(int carId, int userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }


        if (user.getRole() != Role.ADMIN && user.getRole() != Role.MANAGER) {
            System.out.println("Access denied: Only ADMIN or MANAGER can return cars.");
            return;
        }

        Car car = carRepository.findById(carId);
        if (car == null) {
            System.out.println("Car not found.");
            return;
        }
        if (!car.isRented()) {
            System.out.println("Car is not currently rented.");
            return;
        }

        car.setRented(false);
        carRepository.updateCar(car);

        System.out.println("Car returned successfully by " + user.getName() + " (" + user.getRole().getDisplayName() + ")");
    }
}