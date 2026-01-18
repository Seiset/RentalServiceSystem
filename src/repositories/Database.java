package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/car_rental_service";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    private static Connection connection;

    private Database() { }
   
    public static Connection getConnection() { 
        try {
           if (connection == null || connection.isClosed()) {
              connection = DriverManager.getConnection(URL, USER, PASSWORD);
              System.out.printin("PostreSQL connected");
            }
      } catch (SQLException e) {
         System.out.prinin("Connection: " + connection);
      }
      return connection;
   }
}
