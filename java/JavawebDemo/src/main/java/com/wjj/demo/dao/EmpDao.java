package com.wjj.demo.dao;

import com.wjj.demo.bean.Emp;
import com.wjj.demo.utils.JDBCUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class EmpDao extends BaseDao{
    //查询所有
    public List<Emp> selectAll() {
        //编写sql语句
        String sql = "select * from emp;";
        //初始化empList列表
        List<Emp> empList = null;
        try {
            //调用BaseDao中封装好的查询方法executeQuery执行sql语句并直接返回其返回值
            empList = executeQuery(Emp.class,sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return empList;
    }

    //根据id查询emp
    public Emp selectById(Integer id) {
        //编写sql语句
        String sql = "select * from emp where id = ?";
        //初始化empList列表
        List<Emp> empList = null;
        try {
            //调用BaseDao中封装好的查询方法executeQuery执行sql语句并将返回值赋给empList
            empList = executeQuery(Emp.class,sql,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        //判断empList是否为空
        if(empList.size() > 0){
            //不为空则返回第一个
            return empList.get(0);
        }
        //为空返回null;
        return null;
    }

    //根据id查询emp
    public Emp selectByUserName(String userName) {
        //编写sql语句
        String sql = "select * from emp where user_name = ?";
        //初始化empList列表
        List<Emp> empList = null;
        try {
            //调用BaseDao中封装好的查询方法executeQuery执行sql语句并将返回值赋给empList
            empList = executeQuery(Emp.class,sql,userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断empList是否为空
        if(empList.size() > 0){
            //不为空则返回第一个
            return empList.get(0);
        }
        //为空返回null;
        return null;
    }

    //插入一个emp记录
    public int insert(Emp emp) {
        int rows = 0;
        //编写sql语句
        String sql = "insert into emp(id,user_name,real_name,user_password,gender,age,phone) values(?,?,?,?,?,?,?);";
        try {
            //调用BaseDao中封装好的增删改方法执行sql语句并将返回值赋给rows
            rows = executeUpdate(sql,emp.getId(),emp.getUserName(),emp.getRealName(),emp.getUserPassword(),emp.getGender(),emp.getAge(),emp.getPhone());
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回行数
        return rows;
    }

    //根据id更改一条emp记录
    public int updateById(Emp emp) {
        int rows = 0;
        //编写一条sql语句
        String sql = "update emp set user_name = ?, real_name = ?,user_password = ?,gender = ?, age = ?, phone = ? where id = ?;";
        try {
            //调用BaseDao中封装好的增删改方法执行sql语句并将返回值赋给rows
            rows = executeUpdate(sql,emp.getUserName(),emp.getRealName(),emp.getUserPassword(),emp.getGender(),emp.getAge(),emp.getPhone(),emp.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回行数
        return rows;
    }

    //根据id删除一条emp记录
    public int deleteById(Integer id) {
        int rows = 0;
        //编写一条sql语句
        String sql = "delete from emp where id = ?";
        try{
            //调用BaseDao中封装好的增删改方法执行sql语句并返回该方法的返回值
            rows = executeUpdate(sql,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回行数
        return rows;
    }

    public void print(List<Emp> emps) {
        //for循环
        for (Emp emp : emps) {
            System.out.println(emp.toString());
        }
    }
}
