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

     public void showFullRentalInfo() {
        try {
            String sql = """
                SELECT r.id AS rental_id, u.name AS user_name, u.role AS user_role,
                       c.brand, c.model, c.category, c.price, c.rented
                FROM rentals r
                JOIN users u ON r.user_id = u.id
                JOIN cars c ON r.car_id = c.id
            """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        "Rental ID: " + rs.getInt("rental_id") +
                                " | User: " + rs.getString("user_name") +
                                " (" + rs.getString("user_role") + ")" +
                                " | Car: " + rs.getString("brand") + " " + rs.getString("model") +
                                " | Category: " + rs.getString("category") +
                                " | Price: " + rs.getDouble("price") +
                                " | Rented: " + rs.getBoolean("rented")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
