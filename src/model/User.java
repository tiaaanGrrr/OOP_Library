package model;

public abstract class User {
    protected String id;
    protected String username;
    protected String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public abstract String getRole();
}
