package com.wjj.demo.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //属性
    //连接池对象
    private static DataSource dataSource = null;
    //线程本地对象，存储Connection
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    private JDBCUtils(){

    }

    //静态代码块初始化连接对象
    static {
        //加载druid配置文件
        Properties properties = new Properties();
        try {
            //调用加载类的方式加载druid配置文件，得到配置中key value输入流并入给输入流对象ips
            InputStream ips = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //调用properties对象的load方法加载ips
            properties.load(ips);
            //利用工厂模式，传入配置文件对象，创建连接池！
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //方法

    //对外提供链连接的方法
    public static Connection getConnection() throws SQLException {
        //先获取线程本地变量对象中的Connection
        Connection connection = tl.get();
        //判断是否存在已有的Connection
        if(connection == null){
            //如果没有则获取新的连接池连接
            connection = dataSource.getConnection();
            //然后将获取到的连接池连接存入线程本地变量对象tl中
            tl.set(connection);
        }
        return connection;
    }
    //回收外部传入连接的方法
    public static void freeConnection() throws SQLException {
        //因为获取连接的方法为获取后即存入线程本地变量对象中
        //则这边直接获取线程本地变量中的连接
        Connection connection = tl.get();
        //判断是否为空
        if(connection != null) {
            //首先释放线程本地变量中的连接
            tl.remove();
            //回归事务自动提交状态，连接池的连接可能被开启事务了，所以先回归自动提交状态
            connection.setAutoCommit(true);
            //再关闭当前连接
            connection.close();
        }
    }
}
