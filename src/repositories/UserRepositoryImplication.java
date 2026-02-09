package repositories;

import models.User;
import models.Role;
import java.sql.*;

public class UserRepositoryImplication implements UserRepository {

    private final Connection conn;

    public UserRepositoryImplication() {
        conn = Database.getConnection();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (id, name, role, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getRole().name());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Role.fromString(rs.getString("role")),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
