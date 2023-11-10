package xupt.se.ttms.controller;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;


@WebServlet("/RegisteradminServlet")
public class RegisteradminServlet extends HttpServlet {
    //   private CustomerSrvImpl customerSrvImpl = new CustomerSrvImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到登录注册界面用户输入
        int id =0;
        Employee ee =null;
        Integer dict_id =Integer.valueOf( req.getParameter("dict_id"));
        String no = req.getParameter("emp_no");
        String name = req.getParameter("emp_name");
        Integer gender =Integer.valueOf( req.getParameter("emp_gender"));
        String telnum = req.getParameter("emp_telnum");
        String email = req.getParameter("emp_email");
        String pwd = req.getParameter("emp_pwd");
        String pwd_confirm = req.getParameter("password_confirm");

        resp.setContentType("text/html;utf-8");
        PrintWriter out = resp.getWriter();

        if (pwd == null) {
           out.write("密码为空！");
            // 处理密码为 null 的情况
        } else if (pwd.equals(pwd_confirm)) {
            ee = new Employee(id,dict_id, no, name, gender, telnum, email, pwd,1);
            try {
                if((new EmployeeSrv().add(ee)) !=0){
                    out.write("注册成功");
                    resp.sendRedirect("admin/loginadmin.html");
                }else{
                    out.write("注册失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // 密码正确，执行注册操作
        } else {
            out.write("error");
            // 密码不一致，给出错误提示
        }

//        if(pwd.equals(pwd_confirm)){
//            ee = new Employee(id,dict_id, no, name, gender, telnum, email, pwd,1);
//            try {
//                if((new EmployeeSrv().add(ee)) !=0){
//                    out.write("注册成功");
//                    resp.sendRedirect("loginadmin.html");
//                }else{
//                    out.write("注册失败");
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        else{
//            out.write("两次输入的密码不一致");
//
//        }
        out.close();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
