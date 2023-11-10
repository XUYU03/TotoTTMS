package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.dao.customerDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import static xupt.se.ttms.idao.DAOFactory.customerDAO;
import static xupt.se.util.DataUtils.parseInt;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet  extends HttpServlet {
    private static final long serialVersionUID=1L;

  //   CustomerSrv customerSrv = new CustomerSrvImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       // System.out.println("111");
        String type=request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("add"))
            add(request, response);
        else if(type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("zhifu")) {
            try {
                zhifu(request,response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         *   var cus_id = UrlParm.parm("id");
         *         var cus_name = UrlParm.parm("name");
         *         var cus_gender = UrlParm.parm("gender");
         *         var cus_telnum = UrlParm.parm("telnum");
         *         var cus_email = UrlParm.parm("email");
         *         var cus_uid = UrlParm.parm("uid");
         *         var cus_pwd = UrlParm.parm("pwd");
         *         var cus_balance = UrlParm.parm("balance");
         *         var cus_paypwd = UrlParm.parm("paypwd");
         *         document.getElementById("cus_id").value = cus_id;
         *         document.getElementById("cus_name").value = cus_name;
         *         document.getElementById("cus_gender").value = cus_gender;
         *         document.getElementById("cus_telnum").value = cus_telnum;
         *         document.getElementById("cus_email").value = cus_email;
         *         document.getElementById("cus_uid").value = cus_uid;
         *         document.getElementById("cus_pwd").value = cus_pwd;
         *         document.getElementById("cus_balance").value = cus_balance;
         *         document.getElementById("cus_paypwd").value = cus_paypwd;
         */
    }

    private void zhifu(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

//        Customer customer = new customerDAO().querySingle("SELECT * FROM customer WHERE cus_id=?", Customer.class, cus_id);
//        double balance = customer.getCus_balance()+aDouble;
//        if(balance > 0) {
//            new customerDAO().update(("UPDATE customer SET cus_balance=? WHERE cus_id=?"), balance, cus_id);
//            out.write("支付成功");
//        }else{
//            out.write("余额不足");
//        }
        int  id=Integer.valueOf(request.getParameter("cus_id"));
        double price=Double.valueOf(request.getParameter("price"));
        double yue=0;
        DBUtil db=null;

        try
        {

            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return ;
            }
            String sql="select * from customer where cus_id="+id ;

            ResultSet rst=null;
            rst=db.execQuery(sql);
            /*
             * if (rst!=null && rst.getFetchSize()!=0) { System.out.println(111111);
             * rst.next(); if (rst.getString("cus_name") != null) {
             * System.out.println("已存在"); } }
             */


            PrintWriter out=response.getWriter();
            if (rst!=null ) {
                while(rst.next()) {
                    yue=rst.getDouble("cus_balance");
                    //System.out.println(yue);
                }

            }
            if (price>yue) {
                out.write(""+0);
            }else {
                double yue1=yue-price;

                String sql1="update customer set cus_balance="+yue1+"where cus_id="+id;
                int result=db.execCommand(sql1);
                out.write(""+1);
            }


            db.close(rst);
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }

    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer customer=null;
        int id=0;
        try
        {
            String name=request.getParameter("cus_name");
            Integer gender =parseInt( request.getParameter("cus_gender"),0);
            String telnum = request.getParameter("cus_telnum");
            String email = request.getParameter("cus_email");
            String uid = request.getParameter("cus_uid");
            String pwd = request.getParameter("cus_pwd");
            String balance = request.getParameter("cus_balance");
            String paypwd = request.getParameter("cus_paypwd");
            String cus_balance = request.getParameter("cus_balance");

            customer = new Customer(id,name,gender,telnum,email,uid,pwd,0,Double.valueOf(cus_balance),paypwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if ((new CustomerSrv().add(customer)) != 0){
                out.write("数据添加成功");
            }else {
                out.write("数据添加失败，请重试");
                out.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("delete");
        try
        {
            Integer id = Integer.valueOf(request.getParameter("cus_id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new CustomerSrv().delete(id));
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer customer=null;
        int id=0;
        try
        {
            id=Integer.parseInt(request.getParameter("cus_id"));
            String name=request.getParameter("cus_name");
            Integer gender = Integer.parseInt(request.getParameter("cus_gender"));
            String telnum = request.getParameter("cus_telnum");
            String email = request.getParameter("cus_email");
            String uid = request.getParameter("cus_uid");
            String pwd = request.getParameter("cus_pwd");
            String balance = request.getParameter("cus_balance");
            Integer status = Integer.valueOf(request.getParameter("cus_status"));
            String paypwd = request.getParameter("cus_paypwd");
            customer = new Customer(id,name,gender,telnum,email,uid,pwd,status,Double.valueOf(balance),paypwd);
            System.out.println(customer);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new CustomerSrv().modify(customer) == 1)
                out.write("数据修改成功");
            else
                out.write("数据修改失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("cus_name");
        System.out.println(name);
        List<Customer> result=null;
        if(name != null && name.length() > 0) {
            result=new CustomerSrv().Fetch(name);
        }
        else {
            result=new CustomerSrv().FetchAll();
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Customer s : result)
            {
                /**
                 * String name=request.getParameter("name");
                 *             Integer gender =parseInt( request.getParameter("gender"),0);
                 *             String telnum = request.getParameter("telnum");
                 *             String email = request.getParameter("email");
                 *             String uid = request.getParameter("uid");
                 *             String pwd = request.getParameter("pwd");
                 *             String balance = request.getParameter("balance");
                 *             String paypwd = request.getParameter("paypwd");
                 */
                json=new JSONObject();
                json.put("cus_id", s.getCus_id());
                json.put("cus_name", s.getCus_name());
                json.put("cus_gender", s.getCus_gender());
                json.put("cus_email", s.getCus_email());
                json.put("cus_telnum",s.getCus_telnum());
                json.put("cus_uid", s.getCus_uid());
                json.put("cus_pwd", s.getCus_pwd());
                json.put("cus_status",s.getCus_status());
                json.put("cus_balance", s.getCus_balance());
                json.put("cus_paypwd", s.getCus_paypwd());
                array.put(json);
            }
            jsonStr=array.toString();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
    }

}
