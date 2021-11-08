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
    public void remove(User user) {
        userdao.remove(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userdao.update(user);
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userdao.findByEmail(email);
    }

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
