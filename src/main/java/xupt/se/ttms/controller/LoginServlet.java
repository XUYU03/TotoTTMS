package xupt.se.ttms.controller;

import xupt.se.ttms.Client.service.UserClientService;
import xupt.se.ttms.dao.customerDAO;
import xupt.se.ttms.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserClientService userClientService = new UserClientService() ; //对象用于登录服务器 和注册
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u_id = req.getParameter("u_id");
        String pwd = req.getParameter("pwd");
        PrintWriter out = resp.getWriter();
        if(new customerDAO().QueryByU_idANDPWD(u_id,pwd)){
//            req.setCharacterEncoding("UTF-8");  // 设置请求编码
//            resp.setContentType("text/html; charset=UTF-8");  // 设置响应编码
//
//            out.write("登录成功");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("admin/studio/studio.html");
//            dispatcher.forward(req,resp);
            String sql ="select * from customer WHERE cus_uid=? AND cus_pwd=?";
            Customer customer = new customerDAO().querySingle(sql, Customer.class, u_id, pwd);
            System.out.println(customer.getCus_id());
            resp.sendRedirect("customer/index.html?cus_id="+customer.getCus_id());

        } else{
            out.write("登录失败~请重新输入");
        }

    }


}
