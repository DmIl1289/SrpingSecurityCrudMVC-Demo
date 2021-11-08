package web.service;


import web.models.Role;
import web.models.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void add(User user);
    User show(long id);
    void remove(User user);
    void update(User user);
    User findByEmail(String email);
    List<Role> getRoles();
}
