package repositories;

import models.User;
import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User findById(int id);
}
