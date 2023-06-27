package com.zhs.service;

import com.zhs.domain.User;

import java.util.List;

public interface UserService {
    //新增用户
    boolean addUser(User user);

    //根据id删除用户
    boolean deleteUser(Integer id);

    //修改用户信息
    boolean updateUser(User user);

    //根据id查询用户信息
    User getUserById(Integer id);

    //查询所有用户
    List<User> findAll();
}
