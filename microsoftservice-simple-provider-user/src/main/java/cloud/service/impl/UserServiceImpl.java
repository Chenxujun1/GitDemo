package cloud.service.impl;

import cloud.dao.UserDao;
import cloud.entity.User;
import cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }
}
