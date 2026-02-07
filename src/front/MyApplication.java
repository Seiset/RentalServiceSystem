package front;

import models.Car;
import models.User;
import models.Role;
import controllers.RentalService;
import repositories.CarRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private final CarRepository carRepository;
    private final RentalService rentalService;
    private final UserRepository userRepository;
    private final Scanner sc = new Scanner(System.in);

    private final RoleManagement roleManagement;

    public MyApplication(CarRepository carRepository,
                         RentalService rentalService,
                         UserRepository userRepository) {
        this.carRepository = carRepository;
        this.rentalService = rentalService;
        this.userRepository = userRepository;
        this.roleManagement = new RoleManagement(userRepository);
    }

    public void run() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Registration");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) login();
            if (choice == 2) registerUser();
            if (choice == 0) return;
        }
    }

    private void login () {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!roleManagement.login(id, password)) {
            System.out.println("Inсorrect password");
            return;
        }

        System.out.println("Welcome, " +roleManagement.getUserDisplay());
        
        while (true) {
            printMenu();
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> showAllCars();

                case 2 -> {
                    if (roleManagement.canAddCar()) addCar();
                    else accessDenied();
                }

                case 3 -> {
                    if (roleManagement.canUpdateCar()) updateCar();
                    else accessDenied();
                }

                case 4 -> {
                    if (roleManagement.canDeleteCar()) deleteCar();
                    else accessDenied();
                }

                case 5 -> {
                    if (roleManagement.canRentCar()) rentCarMenu();
                    else accessDenied();
                }

                case 6 -> {
                    if (roleManagement.canReturnCar()) returnCarMenu();
                    else accessDenied();
                }

                case 7 -> {
                    if (roleManagement.canRegisterUser()) registerUser();
                    else accessDenied();
                }

                case 8 -> {
                    if (roleManagement.canViewFullRentalInfo()) showFullRentalInfo();
                    else accessDenied();
                }

                case 9 -> {
                    if (roleManagement.canRegisterManager()) registerManager();
                    else accessDenied();

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

        List<String> dynamicOptions = new ArrayList<>();

        if  ( roleManagement.showAddCarOption()) options.add("2. Add new car");
        if (roleManagement.showUpdateCarOption()) options.add("3. Update car");
        if (roleManagement.showDeleteCarOption()) options.add("4. Delete car");
        if ( roleManagement.showRentCarOption()) options.add("5. Rent a car");
        if (roleManagement.showReturnCarOption()) options.add("6. Return a car");
        if (roleManagement.showRegisterUserOption()) options.add("7. Register new user");
        if (roleManagement.showFullRentalInfoOption()) options.add("8. Show full rental info");
        if (roleManagement.canRegisterManager()) options.add("9. Register manager");

        options.forEach(option -> System.out.println(option));

        System.out.println("0. Logout");
        System.out.print("Choose option: ");
    }

    private void accessDenied() {
        System.out.println("Access denied");
    }

    private void showAllCars() {
        var cars = carRepository.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }

        cars.stream()
                .filter(car -> !car.isRented())
                .forEach(System.out::println);
    }

    private void addCar() {
        System.out.print("Brand: ");
        String brand = sc.nextLine();
        System.out.print("Model: ");
        String model = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Category: ");
        String category = sc.nextLine();
    
        carRepository.addCar(new Car(0, brand, model, price, category, "AVAILABLE"));
        System.out.println("Car added successfully!");
    }

    private void updateCar() {
        System.out.print("Enter car ID to update: "); int id = sc.nextInt(); sc.nextLine();
        Car car = carRepository.findById(id);
        if (car == null) { System.out.println("Car not found."); return; }

        System.out.print("New brand: "); String brand = sc.nextLine();
        System.out.print("New model: "); String model = sc.nextLine();
        System.out.print("New price: "); double price = sc.nextDouble(); sc.nextLine();
        System.out.print("New category: "); String category = sc.nextLine();
        System.out.print("New status (AVAILABLE/RENTED): "); String status = sc.nextLine();

        Car updated = new Car(id, brand, model, price, category, status);
        carRepository.updateCar(updated);
        System.out.println("Car updated successfully!");
    }

    private void deleteCar() {
        System.out.print("Enter car ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        carRepository.deleteCar(id);
        System.out.println("Car deleted.");
    }

    private void rentCarMenu() {
        System.out.print("Car ID to rent: "); int carId = sc.nextInt(); sc.nextLine();
        rentalService.rentCar(carId, roleManagement.getCurrentUser().getId());
    }

    private void returnCarMenu() {
        System.out.print("Car ID to return: "); int carId = sc.nextInt(); sc.nextLine();
        rentalService.returnCar(carId, roleManagement.getCurrentUser().getId());
    }

    private void registerUser() {
        System.out.print("Name: "); String name = sc.nextLine();

        System.out.println("Choose role:");
        System.out.println("1 - ADMIN");
        System.out.println("2 - MANAGER");
        System.out.println("3 - USER");
        System.out.print("Enter number (1-3): ");

        int choice = sc.nextInt(); sc.nextLine();

        Role role = switch (choice) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.MANAGER;
            case 3 -> Role.USER;
            default -> {
                System.out.println("Invalid choice → defaulting to USER");
                yield Role.USER;
            }
        };

        User user = new User(0, name, role);
        userRepository.addUser(user);
        System.out.println("User registered successfully!");
    }

    private void showFullRentalInfo() {
        new repositories.RentalRepositoryImplication().showFullRentalInfo();
    }
}
