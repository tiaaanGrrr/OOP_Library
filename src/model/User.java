package model;

public abstract class User {
    protected String id;
    protected String username;
    protected String password;

    public User(String id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public abstract String getRole();
}
