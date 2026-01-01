package service;

import model.User;
import java.util.List;

public class AuthenticationService {

    private List<User> users;

    public AuthenticationService(List<User> users) {
        this.users = users;
    }

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                return u;
            }
        }
        return null;
    }
}
