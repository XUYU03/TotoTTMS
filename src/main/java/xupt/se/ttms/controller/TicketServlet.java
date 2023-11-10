package xupt.se.ttms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.TicketSrv;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
        else if(type.equalsIgnoreCase("sale"))
            sale(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Ticket stu=null;
        int id=0;
        try
        {

            int sched_id=Integer.valueOf(request.getParameter("sched_id"));
            Timestamp time=null;
            time=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("0000-00-00 00:00:00").getTime());
            stu=new Ticket(id, 0,sched_id , 0, 0,time);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new TicketSrv().add(stu) == 1)
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
            int id=Integer.valueOf(request.getParameter("ticket_id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new TicketSrv().delete(id));
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
        Ticket stu=null;
        int id=0;
        try
        {
            id=Integer.valueOf(request.getParameter("ticket_id"));
            int seat_id=Integer.valueOf(request.getParameter("seat_id"));
            int sched_id=Integer.valueOf(request.getParameter("sched_id"));
            double ticket_price=Double.valueOf(request.getParameter("ticket_price"));
            int ticket_status=Integer.valueOf(request.getParameter("ticket_status"));
            Timestamp ticket_locktime=Timestamp.valueOf(request.getParameter("ticket_locktime"));
            stu=new Ticket(id, seat_id, sched_id, ticket_price, ticket_status,ticket_locktime);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new TicketSrv().modify(stu) == 1)
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
        String sched=request.getParameter("sched_id");

        List<Ticket> result=null;
        if(sched != null && sched.length() > 0)
            result=new TicketSrv().Fetch(sched);
        else
            result=new TicketSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket s : result)
            {
                json=new JSONObject();
                json.put("ticket_id", s.getTicket_id());
                json.put("seat_id", s.getSeat_id());
                json.put("sched_id", s.getSched_id());
                json.put("ticket_price", s.getTicket_price());
                json.put("ticket_status", s.getTicket_status());
                json.put("ticket_locktime", s.getTicket_locktime());
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
        // System.out.print(jsonStr);
    }
    private void sale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }



}
