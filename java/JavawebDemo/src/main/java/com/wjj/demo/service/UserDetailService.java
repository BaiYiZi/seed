package com.wjj.demo.service;

import com.wjj.demo.bean.Emp;
import com.wjj.demo.dao.EmpDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserDetail")
public class UserDetailService extends HttpServlet {
    private static final EmpDao empDao;

    static {
        empDao = new EmpDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断传来的id是否为空
        if(req.getParameter("id") == null){
            //等于空则跳回 userList页面
            resp.sendRedirect(req.getContextPath() + "/UserList");
        }else{
            //接收参数id
            int id = Integer.parseInt(req.getParameter("id"));
            //调用empDao对象查询id返回一个emp对象
            Emp emp = empDao.selectById(id);
            //将emp对象共享到request域对象
            req.setAttribute("emp",emp);
            System.out.println(emp.toString());
            //跳转至userDetail页面
            req.getRequestDispatcher("/userDetail.jsp").forward(req,resp);
        }
    }
}
