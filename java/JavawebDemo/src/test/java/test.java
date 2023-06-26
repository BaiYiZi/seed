import com.wjj.demo.bean.Emp;
import com.wjj.demo.dao.EmpDao;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class test {
    private static final EmpDao empDao = new EmpDao();

    @Test
    public void findAll() {
        try {
            //调用查找所有方法将返回值赋给empList
            List<Emp> empList = empDao.selectAll();
            //打印输出
            if(empList.size() > 0){
                empDao.print(empList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        int id = 1;
        try {
            //调用根据id查询记录方法将返回值赋给emp
            Emp emp = empDao.selectById(id);
            //打印输出
            if(emp != null){
                System.out.println(emp.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findByUserName() {
        String userName = "1";
        try {
            //调用根据id查询记录方法将返回值赋给emp
            Emp emp = empDao.selectByUserName(userName);
            //打印输出
            if(emp != null){
                System.out.println(emp.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        int rows = 0;
        Emp emp = new Emp();
        emp.setUserName("1");
        emp.setUserPassword("1");
        emp.setGender("男");
        emp.setAge(1);
        try {
            //调用查找所有方法将返回值赋给emps
            rows = empDao.insert(emp);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(rows);
    }

    @Test
    public void modifyById() {
        int rows = 0;
        Emp emp = new Emp();
        emp.setId(1);
        emp.setUserName("2");
        emp.setUserPassword("2");
        emp.setGender("女");
        emp.setAge(2);
        try {
            //调用查找所有方法将返回值赋给emps
            rows = empDao.updateById(emp);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(rows);
    }

    @Test
    public void deleteById() {
        int rows = 0;
        int id = 1;
        try {
            //调用查找所有方法将返回值赋给emps
            rows = empDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(rows);
    }
}
