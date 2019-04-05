package cloud.service.impl;

import cloud.dao.UserDao;
import cloud.entity.User;
import cloud.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> findUsersByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage-1, pageSize);
        List<User> users = userDao.findUsersByPage();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo.getList();
    }
}
