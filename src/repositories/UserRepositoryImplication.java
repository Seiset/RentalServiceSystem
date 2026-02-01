package repositories;

import models.User;
import models.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplication implements UserRepository {

    private final Connection conn;

    public UserRepositoryImplication() {
        conn = Database.getConnection();
    }

   @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (name, role) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getRole().name());
            ps.executeUpdate();
            System.out.println("User added: " + user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Role.fromString(rs.getString("role"))
                );
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Role.fromString(rs.getString("role"))
                ));
              }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}        
