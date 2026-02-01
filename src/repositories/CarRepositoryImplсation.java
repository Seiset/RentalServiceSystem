
package repositories;

import models.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImplсation implements CarRepository {

    private final Connection conn;

    public CarRepositoryImplсation() {
        conn = Database.getConnection();
    }

    @Override
    public void addCar(Car car) {
        try {
            String sql = "INSERT INTO cars (brand, model, price, rented) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setDouble(3, car.getPrice());
            ps.setBoolean(4, car.getStatus().equalsIgnoreCase("RENTED"));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars");
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getBoolean("rented") ? "RENTED" : "AVAILABLE"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Car findById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cars WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getBoolean("rented") ? "RENTED" : "AVAILABLE"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCar(Car car) {
        try {
            String sql = "UPDATE cars SET brand=?, model=?, price=?, rented=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setDouble(3, car.getPrice());
            ps.setBoolean(4, car.getStatus().equalsIgnoreCase("RENTED"));
            ps.setInt(5, car.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cars WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
