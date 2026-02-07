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

    public boolean login(int id, String password) {
        User user = userRepository.findById(id);
        if (user == null) return false;
        if (!user.getPassword().trim().equals(password.trim())) return false;
        currentUser = user;
        System.out.println("Welcome, " + currentUser.getName() + " (" + currentUser.getRole() + ")");
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUserDisplay() {
        return currentUser.getName() + " (" + currentUser.getRole() + ")";
    }

    public boolean canAddCar() {
        return currentUser.getRole() == Role.ADMIN;
    }

    public boolean canUpdateCar() {
        return currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER;
    }

    public boolean canDeleteCar() {
        return currentUser.getRole() == Role.ADMIN;
    }

    public boolean canRentCar() {
       return currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.USER;
    }

    public boolean canReturnCar() {
        return currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER;
    }

    public boolean canRegisterUser() {
        return currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER;
    }

    public boolean canViewFullRentalInfo() {
       return currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER;
    }

    public boolean canRegisterManager() {
        return currentUser.getRole() == Role.ADMIN;
    }
}
