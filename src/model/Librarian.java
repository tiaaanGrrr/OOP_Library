package model;

public class Librarian extends User {

    public Librarian(String id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}
