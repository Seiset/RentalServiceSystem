import repositories.IDB;


import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {
    public PostgresDB(String host, String dbName, String user, String password) {
        try {
            String className;
            Class.forName( "org.postgresql.Driver");
            String url = host + "/"+ dbName;
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("PostgreSQL connected");
        } catch (Exception e) {
            System.out.println("DB error: " + e.getMessage());}}

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() {

    }
}      
