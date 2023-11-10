package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.service.ResourceSrv;
import xupt.se.ttms.service.StudioSrv;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;


@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if (type.equalsIgnoreCase("add"))
            add(request, response);
        else if (type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if (type.equalsIgnoreCase("update"))
            update(request, response);
        else if (type.equalsIgnoreCase("search"))
            search(request, response);


    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Resource res=null;
        int id=0;
        try
        {
            String parent=request.getParameter("res_parent");
            String name=(request.getParameter("res_name"));
            String URL=(request.getParameter("res_URL"));
            res =new Resource(id, parent, name, URL);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if((new ResourceSrv().add(res)) != 0)
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
            int res_id=Integer.valueOf(request.getParameter("res_id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new ResourceSrv().delete(res_id));
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
        Resource res=null;
        int id=0;
        System.out.println(request);
        try
        {
            id = Integer.parseInt(request.getParameter("res_id"));
            String parent=request.getParameter("res_parent");
            String name=request.getParameter("res_name");
            String URL=request.getParameter("res_URL");
            res =new Resource(id, parent, name, URL);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ResourceSrv().modify(res) == 1)
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
        String name=request.getParameter("name");
        List<Resource> result=null;
        if(name != null && name.length() > 0){
            result=new ResourceSrv().Fetch(name);
        }
        else {
            result = new ResourceSrv().FetchAll();
        }


        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Resource r: result)
            {
                json=new JSONObject();
                json.put("res_id", r.getRes_id());
                json.put("res_parent", r.getRes_parent());
                json.put("res_name", r.getRes_name());
                json.put("res_URL", r.getRes_URL());
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
