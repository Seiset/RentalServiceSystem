package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/car_rental_service";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    private Database() {}
    private static class Holder {
        private static final Connection CONNECTION = createConnection();
    }

    private static Connection createConnection() {
        try {
            System.out.println("PostgreSQL connected");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("DB connection error", e);
        }
    }

    public static Connection getConnection() {
        return Holder.CONNECTION;
    }
}

