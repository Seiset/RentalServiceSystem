import front.MyApplication;
import controllers.RentalService;
import repositories.CarRepository;
import repositories.CarRepositoryImplсation;

public class Main {

    public static void main(String[] args) throws Exception {

        CarRepository carRepository = new CarRepositoryImplсation();
        RentalService rentalService = new RentalService(carRepository);

        MyApplication app = new MyApplication(carRepository, rentalService);
        app.run();
    }
}