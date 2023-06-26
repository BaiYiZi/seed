package com.wjj.demo.service;

import com.wjj.demo.bean.Emp;
import com.wjj.demo.dao.EmpDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserList")
public class UserListService extends HttpServlet {
    private static final EmpDao empDao;

    static {
        empDao = new EmpDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用empDao对象selectAll方法查询所有员工信息
        List<Emp> empList = empDao.selectAll();
        //放入request共享域中
        req.setAttribute("empList",empList);
        //转发到userList.jsp
        req.getRequestDispatcher("/userList.jsp").forward(req,resp);
    }
}
