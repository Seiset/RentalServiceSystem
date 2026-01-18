import controllers.Car;
import repositories.CarRepository;
import repositories.CarRepositoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CarRepository carRepository = new CarRepositoryImpl();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- CAR RENTAL SYSTEM ---");
            System.out.println("1. Show all cars");
            System.out.println("2. Add new car");
            System.out.println("3. Update car");
            System.out.println("4. Delete car");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (option) {

                case 1:
                    if (carRepository.getAllCars().isEmpty()) {
                        System.out.println("No cars available.");
                    } else {
                        for (Car car : carRepository.getAllCars()) {
                            System.out.println(car);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Brand: ");
                    String brand = sc.nextLine();

                    System.out.print("Model: ");
                    String model = sc.nextLine();

                    System.out.print("Price per day: ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    if (price <= 0) {
                        System.out.println("Price must be greater than 0");
                        break;
                    }

                    System.out.print("Status (AVAILABLE / RENTED): ");
                    String status = sc.nextLine();

                    Car car = new Car(0, brand, model, price, status);
                    carRepository.addCar(car);

                    System.out.println("Car added successfully!");
                    break;

                case 3:
                    System.out.print("Enter car ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Car existingCar = carRepository.findById(updateId);
                    if (existingCar == null) {
                        System.out.println("Car not found.");
                        break;
                    }

                    System.out.print("New brand: ");
                    String newBrand = sc.nextLine();

                    System.out.print("New model: ");
                    String newModel = sc.nextLine();

                    System.out.print("New price per day: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("New status (AVAILABLE / RENTED): ");
                    String newStatus = sc.nextLine();

                    Car updatedCar = new Car(updateId, newBrand, newModel, newPrice, newStatus);
                    carRepository.updateCar(updatedCar);

                    System.out.println("Car updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter car ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    carRepository.deleteCar(deleteId);
                    System.out.println("Car deleted.");
                    break;

                case 0:
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}