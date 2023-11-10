package xupt.se.ttms.controller;

import xupt.se.ttms.Client.service.UserClientService;
import xupt.se.ttms.dao.employeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginadminServlet")
public class LoginadminServlet extends HttpServlet {

    private UserClientService userClientService = new UserClientService() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u_id = req.getParameter("u_id");
        String pwd = req.getParameter("pwd");
        PrintWriter out = resp.getWriter();
        if (new employeeDAO().QueryByU_idANDPWD(u_id, pwd)) {

            resp.sendRedirect("admin/studio/studio.html");
        } else {
            out.write("登录失败~请重新输入");
        }

    }
}
