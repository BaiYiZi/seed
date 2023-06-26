package com.wjj.demo.dao;

import com.wjj.demo.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {
    //执行增删改语句方法：返回值为影响行数及int
    //参数为preparedSql：sql语句字符串，params：可变参数对象
    protected int executeUpdate(String sql, Object...params) throws SQLException {
        //调用jdbcUtils对象的getConnection方法创建连接
        Connection conn = JDBCUtils.getConnection();
        //创建请求对象statement，预编制语句
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //循环填入占位符值
        for (int i = 1; i <= params.length; i++) {
            pstmt.setObject(i, params[i - 1]);
        }
        //调用pstmt对象中的executeUpdate运行增删改语句并把返回的影响行数量
        int rows = pstmt.executeUpdate();
        //关闭pstmt
        pstmt.close();
        //判断是否在事务进行中
        if (conn.getAutoCommit()) {
            //如果为true则说明没有在事务进行中，即回收连接
            JDBCUtils.freeConnection();
        }
        //最后返回行数
        return rows;
    }

    /**执行增删改语句方法：返回值为影响行数及int
    TODO:执行查询语句并将查询返回的结果集封装成指定类型数组再返回
    参数：
    <T>：泛型，不限数据类型
    clazz：传入的具体类型
    sql：需要执行的sql语句
     params：需要填入的参数
     */
    protected <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //调用jdbcUtils对象的getConnection方法创建连接
        Connection conn = JDBCUtils.getConnection();
        //创建请求对象,预编制语句
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //如果传入的可变参数不为空且长度大于0
        if (params != null && params.length > 0) {
            //循环在sql语句中填入参数
            for (int i = 1; i <= params.length; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
        }

        //创建一个泛型数组列表list，用于返回该方法查询后的结果
        List<T> list = new ArrayList<>();
        //执行查询，并把查询的结果集保存在rs中
        ResultSet rs = pstmt.executeQuery();

        //创建一个数据列对象metaData，获得结果集rs的列对象
        ResultSetMetaData metaData = rs.getMetaData();
        //调用metaData中的getColumns方法得到列数
        int columnCount = metaData.getColumnCount();

        //while循环将rs中的结果提至数组list中
        //rs.next()控制行下移
        while (rs.next()) {
            //实例化泛型T类对象t
            T t = clazz.getDeclaredConstructor().newInstance();
            //控制每列后移
            for (int i = 1; i <= columnCount; i++) {
                //======获得rs中对应位置的值即其列名=========
                //得到结果集rs中当前行的地i列的值
                Object value = rs.getObject(i);
                //获得当前i列的字段名
                String propertyName = metaData.getColumnLabel(i);

                //======利用反射给对象属性赋值=========
                Field field = clazz.getDeclaredField(propertyName);
                //解除private的限制
                field.setAccessible(true);
                //给t类赋值value
                field.set(t, value);
            }
            //将每一行都添加到到list中
            list.add(t);
        }
        //关闭pstmt
        pstmt.close();
        //判断是否在事务进行中
        if (conn.getAutoCommit()) {
            //如果为true则说明没有在事务进行中，即回收连接
            JDBCUtils.freeConnection();
        }
        //返回list
        return list;
    }

    //查询记录数
    protected int executeQueryCount(String sql) throws SQLException {
        //调用jdbcUtils对象的getConnection方法创建连接
        Connection conn = JDBCUtils.getConnection();
        //创建请求对象statement
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //调用pstmt对象中的executeQuery查询
        ResultSet rs = pstmt.executeQuery();
        //调用next方法下移指针指向数据
        rs.next();
        //以整型方式获取结果集rs中的number列中的值赋给count
        int count = rs.getInt("number");
        //关闭pstmt
        pstmt.close();
        //判断是否在事务进行中
        if (conn.getAutoCommit()) {
            //如果为true则说明没有在事务进行中，即回收连接
            JDBCUtils.freeConnection();
        }
        //最后返回记录数
        return count;
    }
}
