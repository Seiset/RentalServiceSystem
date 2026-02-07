package models;

public class User {
  private int id;
  private String name;
  private Role role;
  private String password;

  public User(int id, String name, Role role, String password) {
      this.id = id;
      this.name = name;
      this.role = role;
      this.password = password;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public Role getRole() { return role; }
  public String getPassword() { return password; }
}
