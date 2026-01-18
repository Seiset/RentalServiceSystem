package controllers;

import controllers.Car;
import controllers.Rental;
import repositories.CarRepository;
import repositories.IDB;
import repositories.RentalRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalService  {

    private CarRepository carRepository;
    private RentalRepository rentalRepository;

    public RentalService(CarRepository carRepository,
                         RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
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
        rentalRepository.save(new Rental(1, carId));
        System.out.println("Car rented successfully");
    }

    public void returnCar(int carId) {
        Car car = carRepository.findById(carId);

        if (car != null && car.isRented()) {
            car.setRented(false);
            System.out.println("Car returned");
        }
    }

    public List<Car> findAvailableCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE available = true";

        Connection conn = IDB.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            cars.add(new Car(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getDouble("price_per_day"),
                    rs.getString("available")
            ));
        }
        return cars;
    }

    public void updateAvailability(int carId, boolean status) throws SQLException {
        String sql = "UPDATE cars SET available = ? WHERE id = ?";
        PreparedStatement ps = IDB.getConnection().prepareStatement(sql);
        ps.setBoolean(1, status);
        ps.setInt(2, carId);
        ps.executeUpdate();
    }
} 
