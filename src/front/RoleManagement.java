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

    public boolean login(int userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return false;
        }
        this.currentUser = user;
        return true;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUserDisplay() {
        if (!isLoggedIn()) return "Not logged in";
        return currentUser.getName() + " (" + currentUser.getRole() + ")";
    }

    public boolean canAddCar() {
        return hasRole(Role.ADMIN);
    }

    public boolean canUpdateCar() {
        return hasRole(Role.ADMIN, Role.MANAGER);
    }

    public boolean canDeleteCar() {
        return hasRole(Role.ADMIN);
    }

    public boolean canRentCar() {
        return hasRole(Role.ADMIN, Role.USER);
    }

    public boolean canReturnCar() {
        return hasRole(Role.ADMIN, Role.MANAGER);
    }

    public boolean canRegisterUser() {
        return hasRole(Role.ADMIN, Role.MANAGER);
    }

    public boolean canViewFullRentalInfo() {
        return hasRole(Role.ADMIN, Role.MANAGER);
    }

