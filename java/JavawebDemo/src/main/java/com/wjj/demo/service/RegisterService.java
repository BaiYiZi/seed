package com.wjj.demo.service;

import com.wjj.demo.bean.Emp;
import com.wjj.demo.dao.EmpDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterService")
public class RegisterService extends HttpServlet {
    private static final EmpDao empDao = new EmpDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        //判断接收的参数是否有空
        if(req.getParameter("user_name") != null &&
            req.getParameter("real_name") != null &&
            req.getParameter("user_password") != null &&
            req.getParameter("gender") != null &&
            req.getParameter("age") != null &&
            req.getParameter("phone") != null
        ){
            //不为空则接收传过来的参数
            String userName = req.getParameter("user_name");
            String realName = req.getParameter("real_name");
            String userPassword = req.getParameter("user_password");
            String gender = req.getParameter("gender");
            String age = req.getParameter("age");
            String phone = req.getParameter("phone");
            System.out.println();
            //判断username是否已存在
            if(empDao.selectByUserName(userName) == null ){
                //不存在则将参数封装成emp对象
                Emp emp = new Emp();
                emp.setUserName(userName);
                emp.setRealName(realName);
                emp.setUserPassword(userPassword);
                emp.setGender(gender);
                emp.setAge(Integer.parseInt(age));
                emp.setPhone(phone);
                System.out.println(emp.toString());
                //调用empDao的insert方法将当前emp对象添加到数据库中
                empDao.insert(emp);
                //重定向到登录页面
                resp.sendRedirect(req.getContextPath() + "/login.html");
            }else{
                //如果用户名重复则返回注册页面
                resp.sendRedirect(req.getContextPath() + "/regist.html");
            }
        }else{
            //如果有参数为空则返回注册页面
            resp.sendRedirect(req.getContextPath() + "/regist.html");
        }

    }
}
