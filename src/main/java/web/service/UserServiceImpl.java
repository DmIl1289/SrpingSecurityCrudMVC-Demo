package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public List<User> listUsers() {
        return userdao.listUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userdao.add(user);
    }

    @Transactional
    @Override
    public User show(long id){
        return userdao.show(id);
    }

    @Transactional
    @Override
    public void remove(long id) {
        userdao.remove(id);
    }

    @Transactional
    @Override
    public void update(long id, User user) {
        userdao.update(id, user);
    }

    @Transactional
    @Override
    public User findByName(String username) {
        return userdao.findByName(username);
    }

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
