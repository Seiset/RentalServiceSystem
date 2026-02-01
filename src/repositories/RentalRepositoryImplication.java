package repositories;

import models.Rental;
import java.sql.*;

public class RentalRepositoryImplication implements RentalRepository {

    private final Connection conn;

    public RentalRepositoryImplication() {
        conn = Database.getConnection();
    }

    @Override
    public void save(Rental rental) {
        try {
            String sql = "INSERT INTO rentals (user_id, car_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rental.getUserId());
            ps.setInt(2, rental.getCarId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

