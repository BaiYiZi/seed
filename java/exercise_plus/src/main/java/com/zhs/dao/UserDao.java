package com.zhs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    //新增用户
    @Insert("INSERT INTO yonghuxinxi ( xingMing, miMa, diZhi, aiHao ) " +
            "VALUES ( #{xingMing}, #{miMa}, #{diZhi}, #{aiHao} );")
    public int addUser(User user);

   //根据id删除用户
    @Delete("DELETE FROM yonghuxinxi WHERE id = #{id}")
    public int deleteUser(@Param("id") Integer id);

    //修改用户信息
    @Update("UPDATE yonghuxinxi SET xingMing = #{xingMing}, " +
            "miMa = #{miMa}, dizhi = #{diZhi}, aiHao = #{aiHao} WHERE id = #{id}")
    public int updateUser(User user);

    //根据id查询用户信息
    @Select("SELECT * FROM yonghuxinxi WHERE id = #{id}")
    public User getUserById(Integer id);

    //查询所有用户
    @Select("SELECT * FROM yonghuxinxi")
    public List<User> findAll();

}
