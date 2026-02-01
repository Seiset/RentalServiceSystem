import front.MyApplication;
import controllers.RentalService;
import repositories.*;

public class Main {

    public static void main(String[] args) throws Exception {
        CarRepository carRepository = new CarRepositoryImplсation();
        UserRepository userRepository = new UserRepositoryImplication();
        RentalRepositoryImplication rentalRepository = new RentalRepositoryImplication();

        RentalService rentalService = new RentalService(carRepository, rentalRepository, userRepository);

        MyApplication app = new MyApplication(carRepository, rentalService, userRepository);
        app.run();
    }
}