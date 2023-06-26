package com.wjj.demo.service;

import com.wjj.demo.bean.Emp;
import com.wjj.demo.dao.EmpDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
    private static final EmpDao empDao;

    static {
        empDao = new EmpDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收页面传来的参数
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        //判断是否有空
        if(userName != null && userPassword != null){
            //不为空则根据用户名查找用户
            Emp emp = empDao.selectByUserName(userName);
            //判断用户名是否存在
            if(emp == null){
                //不存在返回登陆页面
                req.getRequestDispatcher("/login.html").forward(req,resp);
            }
            //判断密码是否正确
            else if(!emp.getUserPassword().equals(userPassword)){
                //如果密码不正确则返回登陆页面
                req.getRequestDispatcher("/login.html").forward(req,resp);
            }else{
                //用户名和密码都正确则将用户信息存入session
                HttpSession session = req.getSession();
                session.setAttribute("id",emp.getId());
                session.setAttribute("user_name",emp.getUserName());
                session.setAttribute("islogin","yes");
                //跳转至用户列表页面
                resp.sendRedirect("UserList");
            }
        }else{
            //如果用户名或密码为空则跳转回登录页面
            req.getRequestDispatcher(req.getContextPath() + "/login.html").forward(req,resp);
        }
    }
}
