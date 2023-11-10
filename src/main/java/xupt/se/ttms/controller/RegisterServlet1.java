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
@WebServlet("/RegisterServlet1")
public class RegisterServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到登录注册界面用户输入
        Customer cc =null;
        String uId = req.getParameter("u_id");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");
        resp.setContentType("text/html;utf-8");
        PrintWriter out = resp.getWriter();
        if(!password.equals(passwordConfirm)){
            out.write("两次输入的密码不一致");
        } else if (uId.equals("")) {
            out.write("请勿输入空值！");
        } else{
            cc = new Customer(0,"",2,"","",uId,password,1,0,"");
            try {
                if((new CustomerSrv().add(cc)) !=0){
                    out.write("注册成功");

                    resp.sendRedirect("customer/login/index.html");
                }else{
                    out.write("注册失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        out.close();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
