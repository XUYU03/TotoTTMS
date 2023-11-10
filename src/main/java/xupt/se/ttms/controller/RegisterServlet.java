package xupt.se.ttms.controller;

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.CustomerSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
 //   private CustomerSrvImpl customerSrvImpl = new CustomerSrvImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到登录注册界面用户输入
        int id =0;
        Customer cc =null;
        String username = req.getParameter("username");
        Integer gender =Integer.valueOf( req.getParameter("gender"));
        String telem = req.getParameter("telem");
        String email = req.getParameter("email");
        String uId = req.getParameter("u_id");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");
        String payPwd = req.getParameter("pay_pwd");
        String cusBalance = req.getParameter("cus_balance");

        resp.setContentType("text/html;utf-8");
        PrintWriter out = resp.getWriter();
        if(password.equals(passwordConfirm)){
            cc = new Customer(id,username,gender,telem,email,uId,password,1,Double.valueOf(cusBalance),payPwd);
            try {
                if((new CustomerSrv().add(cc)) !=0){
                    out.write("注册成功");
                    resp.sendRedirect("customer/login.html");
                }else{
                    out.write("注册失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            out.write("两次输入的密码不一致");

        }
        out.close();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
