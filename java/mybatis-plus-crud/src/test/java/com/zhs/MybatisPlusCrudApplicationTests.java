package com.zhs;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhs.dao.UserDao;
import com.zhs.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusCrudApplicationTests {

    //自动注入
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
    }

    @Test
    /**
     * 增加用户
     */
    void addUsre() {
        User user = new User();
        user.setXingMing("迪迦");
        user.setAiHao("唱跳");
        userDao.insert(user);
    }


    @Test
    /**
     * 根据id删除用户
     */
    void deleteById() {
        userDao.deleteById(14);
    }

    @Test
    /**
     * 修改用户信息
     */
    void updateUser() {
        User user = new User();
        user.setId(6);
        user.setXingMing("你是认真的吗");
        user.setDiZhi("雪啊");
        userDao.updateById(user);
    }

    @Test
    /**
     * 根据id查询用户信息
     */
    void getUserById() {
        User user = userDao.selectById(6);
        System.out.println(user);
    }

    @Test
    /**
     * 查询所有用户
     */
    void findAll() {
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }


    @Test
    /**
     * 分页
     */
    void getUserPag() {
        IPage page = new Page(1,5);
        userDao.selectPage(page,null);
        System.out.println("当前页码值" + page.getCurrent());
        System.out.println("每页显示数" + page.getSize());
        System.out.println("一共多少页" + page.getPages());
        System.out.println("一共多少条数据" + page.getTotal());
        System.out.println("数据" + page.getRecords());
    }

    @Test
    //条件查询
    void conditionQuery(){
      /*  //1、按条件查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.lt("id",9);
        wrapper.gt("id",6);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);*/

        /*//2、Lambda表达式按条件查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().lt(User::getId,9).gt(User::getId,6);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);*/

        //3、
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(User::getId,9).or().gt(User::getId,6);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);
    }
}
