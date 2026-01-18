package repositories;

import java.SQL.Connection;

public interface IDB {
   static Connection getConnection() {
      return null;
   }

   Connection getConnection();

   void close();

}
