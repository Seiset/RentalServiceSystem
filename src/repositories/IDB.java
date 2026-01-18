package repositories;

import java.sql.Connection;

public interface IDB {
    static Connection getConnection() {
        return null;
    }

    Connection getConnection();

    void close();
}
