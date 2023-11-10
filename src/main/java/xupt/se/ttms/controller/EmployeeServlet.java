package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.ResourceSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static xupt.se.util.DataUtils.parseInt;

/**
 * @author 杜浩杰
 * @version 1.0
 */

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    //EmployeeSrv employeeSrv = new EmployeeSrvImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、改、查
        if(type.equalsIgnoreCase("add")) {
            try {
                add(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(type.equalsIgnoreCase("delete")) {
            delete(req, resp);

        }
        else if(type.equalsIgnoreCase("update")){
            update(req, resp);

        }

        else if(type.equalsIgnoreCase("search")) {
            search(req, resp);
        }
    }


    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setCharacterEncoding("UTF-8");
        PrintWriter out=resp.getWriter();
        String name=req.getParameter("name");
        List<Employee> result=null;

        if(name != null && name.length() > 0) {
            result = new EmployeeSrv().Fetch(name);
        }
        else {
            result =new EmployeeSrv().FetchAll();
        }
        String jsonStr="";
        System.out.println(result);
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Employee s : result)
            {
                json=new JSONObject();
                json.put("emp_id",s.getEmp_id());
                json.put("dict_id", s.getDict_id());
                json.put("emp_no", s.getEmp_no());
                json.put("emp_name", s.getEmp_name());
                json.put("emp_gender", s.getEmp_gender());
                json.put("emp_telnum",s.getEmp_telnum());
                json.put("emp_email", s.getEmp_email());
                json.put("emp_pwd", s.getEmp_pwd());
                json.put("emp_status",s.getEmp_status());

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

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Employee emp=null;
        int id=0;
        try
        {
            id = Integer.parseInt(req.getParameter("emp_id"));
            Integer dictId = Integer.valueOf(req.getParameter("dict_id"));
            String empNo = req.getParameter("emp_no");
            String empName = req.getParameter("emp_name");
            Integer empGender =parseInt(req.getParameter("emp_gender"),0);
            String empTelnum = req.getParameter("emp_telnum");
            String empEmail = req.getParameter("emp_email");
            String empPwd = req.getParameter("emp_pwd");
            Integer empStatus =parseInt( req.getParameter("emp_status"),0);

            emp= new Employee(id,dictId,empNo,empName,empGender,empTelnum,empEmail,empPwd,empStatus);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out=resp.getWriter();

            if(new EmployeeSrv().modify(emp) == 1)
                out.write("数据修改成功");
            else
                out.write("数据修改失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("操作错误，请重试");
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            //通过 name 查询数据库删除
        //拿到emp_name;
        Integer emp_id =Integer.valueOf( req.getParameter("emp_id"));
        //设置字符集
        try {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out=resp.getWriter();
           // System.out.println(new EmployeeSrv().Fetch(""));
            out.write("" + new EmployeeSrv().delete(emp_id));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("操作错误，请重试");
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        Employee employee = null;
        int id=0;
        try{
            Integer dictId = parseInt(req.getParameter("dict_id"),0);
            String empNo = req.getParameter("emp_no");
            String empName = req.getParameter("emp_name");
            Integer empGender =parseInt(req.getParameter("emp_gender"),0);
            String empTelnum = req.getParameter("emp_telnum");
            String empEmail = req.getParameter("emp_email");
            String empPwd = req.getParameter("emp_pwd");
            Integer empStatus =parseInt( req.getParameter("emp_status"),0);
            employee = new Employee(id,dictId,empNo,empName,empGender,empTelnum,empEmail,empPwd,empStatus);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out=resp.getWriter();

            if((new EmployeeSrv().add(employee)) != 0)
                out.write("数据添加成功");
            else
                out.write("数据添加失败，请重试");

            out.close();
        }catch(Exception e)
        {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("操作错误，请重试");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
