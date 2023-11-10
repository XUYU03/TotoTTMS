package xupt.se.ttms.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("BasicServlet 方法");

        //获取到action 的值
        String action = req.getParameter("action");


        //使用反射 ， 获取当前对象
        /**
         * this 是 请求的Servlet
         * 2.deckaredMethod就是 对应的 当前请求的servlet action 方法
         * 模板模式 + 反射 + 动态机制
         */
        try {
            Method deckaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            deckaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
