package com.zhs.controller;

import com.zhs.domain.User;
import com.zhs.service.UserService;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping("users")
@EnableWebMvc
public class UserController {

    //自动注入
    @Autowired
    private UserService userService;


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 根据id查询用户信息
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    /**
     * 查询所有用户信息
     */
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
}
