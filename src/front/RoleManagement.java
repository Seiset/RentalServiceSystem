package front;

import models.Role;
import models.User;
import repositories.UserRepository;

public class RoleManagement {

    private User currentUser;
    private final UserRepository userRepository;

    public RoleManagement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
