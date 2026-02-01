package front;

import models.Car;
import controllers.RentalService;
import models.Rental;
import repositories.CarRepository;

import java.util.Scanner;

public class MyApplication {

    private final CarRepository carRepository;
    private final RentalService rentalService;
    private final Scanner sc = new Scanner(System.in);

    public MyApplication(CarRepository carRepository, RentalService rentalService) {
        this.carRepository = carRepository;
        this.rentalService = rentalService;
    }

    public void run() {
        while (true) {
            printMenu();

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> showAllCars();
                case 2 -> addCar();
                case 3 -> updateCar();
                case 4 -> deleteCar();
                case 5 -> rentCarMenu();
                case 6 -> returnCarMenu();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- CAR RENTAL SYSTEM ---");
        System.out.println("1. Show all cars");
        System.out.println("2. Add new car");
        System.out.println("3. Update car");
        System.out.println("4. Delete car");
        System.out.println("5. Rent a car");
        System.out.println("6. Return a car");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private void showAllCars() {
        if (carRepository.getAllCars().isEmpty()) {
            System.out.println("No cars available.");
            return;
        }

        for (Car car : carRepository.getAllCars()) {
            System.out.println(car);
        }
    }

    private void addCar() {
        System.out.print("Brand: ");
        String brand = sc.nextLine();

        System.out.print("Model: ");
        String model = sc.nextLine();

        System.out.print("Price per day: ");
        double price = sc.nextDouble();
        sc.nextLine();

        if (price <= 0) {
            System.out.println("Price must be greater than 0");
            return;
        }

        System.out.print("Status (AVAILABLE / RENTED): ");
        String status = sc.nextLine();
        rentalService.rentalTime();


        Car car = new Car(0, brand, model, price, status);
        carRepository.addCar(car);

        System.out.println("Car added successfully!");
    }

    private void updateCar() {
        System.out.print("Enter car ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Car existingCar = carRepository.findById(id);
        if (existingCar == null) {
            System.out.println("Car not found.");
            return;
        }

        System.out.print("New brand: ");
        String brand = sc.nextLine();

        System.out.print("New model: ");
        String model = sc.nextLine();

        System.out.print("New price per day: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("New status (AVAILABLE / RENTED): ");
        String status = sc.nextLine();

        Car updatedCar = new Car(id, brand, model, price, status);
        carRepository.updateCar(updatedCar);

        System.out.println("Car updated successfully!");
    }

    private void deleteCar() {
        System.out.print("Enter car ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        carRepository.deleteCar(id);
        System.out.println("Car deleted.");
    }

    private void rentCarMenu() {
        System.out.print("Enter car ID to rent: ");
        int carId = sc.nextInt();
        sc.nextLine();
        rentalService.rentCar(carId);
    }

    private void returnCarMenu() {
        System.out.print("Enter car ID to return: ");
        int carId = sc.nextInt();
        sc.nextLine();
        rentalService.returnCar(carId);
    }
}