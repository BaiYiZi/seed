package com.zhs.service.iml;

import com.zhs.dao.UserDao;
import com.zhs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements com.zhs.service.UserService {

    //自动注入
    @Autowired
    private UserDao userDao;

    @Override
    /**
     * 新增用户
     */
    public boolean addUser(User user) {
        int rows = userDao.addUser(user);
        return rows > 0;
    }

    @Override
    /**
     * 根据id删除用户
     */
    public boolean deleteUser(Integer id) {
        int rows = userDao.deleteUser(id);
        return rows > 0;
    }

    @Override
    /**
     * 修改用户信息
     */
    public boolean updateUser(User user) {
        int rows = userDao.updateUser(user);
        return rows > 0;
    }

    @Override
    /**
     * 根据id查询用户信息
     */
    public User getUserById(Integer id) {
            return userDao.getUserById(id);
    }

    @Override
    /**
     * 查询所有用户
     */
    public List<User> findAll() {
        return userDao.findAll();
    }
}
