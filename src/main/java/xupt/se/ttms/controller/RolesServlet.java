package xupt.se.ttms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Roles;
import xupt.se.ttms.service.RolesSrv;

@WebServlet("/RolesServlet")
public class RolesServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String type=request.getParameter("type");
        System.out.println(type);
        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("add"))
            add(request, response);
        else if(type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Roles role=null;
        try
        {
            String role_name=request.getParameter("role_name");
            role=new Roles(0,role_name);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new RolesSrv().add(role) == 1)
                out.write("数据添加成功");
            else
                out.write("数据添加失败，请重试");

            out.close();
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
        try
        {
            int role_id=Integer.valueOf(request.getParameter("role_id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new RolesSrv().delete(role_id));
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
        Roles rol=null;
        int role_id=0;
        try
        {
            role_id=Integer.parseInt(request.getParameter("role_id"));
            System.out.println(role_id);
            String role_name=request.getParameter("role_name");
            rol=new Roles(role_id, role_name);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new RolesSrv().modify(rol) == 1)
                out.write("数据修改哦成功");
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
        String role_name=request.getParameter("name");
        System.out.println(role_name);
        List<Roles> result=null;
        if(role_name != null &&role_name.length() > 0){
            result=new RolesSrv().Fetch(role_name);}
        else
            result=new RolesSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Roles s : result)
            {
                json=new JSONObject();
                json.put("role_id", s.getRole_id());
                json.put("role_name", s.getRole_name());
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