package models;

public enum Role {
    ADMIN,
    MANAGER,
    USER;

     public static Role fromString(String roleStr) {
        if (roleStr == null) return null;
        try {
            return Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // or throw exception - depending on your needs
        }
    }

    public String getDisplayName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}


