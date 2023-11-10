package xupt.se.ttms.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet
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
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Schedule schedule=null;
        try
        {
            int studio_id=Integer.parseInt(request.getParameter("studio_id"));
            int play_id=Integer.parseInt(request.getParameter("play_id"));
            Timestamp sched_time= Timestamp.valueOf(request.getParameter("sched_time"));
            double sched_ticket_price = Integer.parseInt(request.getParameter("sched_ticket_price"));
            int sched_status = Integer.parseInt(request.getParameter("sched_status"));
            schedule = new Schedule(0,studio_id, play_id, sched_time, sched_ticket_price, sched_status);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            int sched_id = 0;
            int first_seat_id=0;
            if((new ScheduleSrv().add(schedule)) != 0) {
                sched_id=new ScheduleDAO().selectshed_id();
                first_seat_id=new ScheduleDAO().first_seat_id(studio_id);
                System.out.println(sched_id);
                out.write("数据添加成功");
                //拿到演出厅id -> 查询到演出厅的行和列 ->根据行和列生成演出计划票 标记为未售出 等待顾客买票
                List<Studio> stu = new StudioSrv().QuerybyId(studio_id);
                //根据演出计划生成票
                int colCount=0;
                int rowCount=0;
                for(Studio studio:stu){
                    rowCount=studio.getRowCount();
                    colCount=studio.getColCount();
                }
                System.out.println(rowCount);
                System.out.println(colCount);
                System.out.println(first_seat_id);
                for(int i = 1; i <=colCount ; i++) {
                    for(int j = 1; j <= rowCount; j++){
                        new ScheduleSrv().insert_ticket(first_seat_id+i*j-1,sched_id,sched_ticket_price);
                    }
                }
            }
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
            int id=Integer.parseInt(request.getParameter("sched_id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            //删除演出计划->查看是否能删除 （是否有已售出的票，如有已售出的票必须先完成退票 保证票状态 ticket_status = 0;
            if(new TicketSrv().Check_ticket_status(id) == 1) {
                //不存在已售出票 ，可以删除
                out.write( new ScheduleSrv().delete(id));
            }else {
                //存在已售出票 不能删除 可以先完成退票
                //给出提示
                response.getWriter().write("请先完成该演出计划所有已售出票的退票");
            }
            //如果该演出票状态全  ticket_status = 0 删除所有演出计划所产生的票
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
        Schedule schedule=null;
        int sched_id=0;
        try
        {
            sched_id=Integer.valueOf(request.getParameter("sched_id"));
            int studio_id=Integer.valueOf(request.getParameter("studio_id"));
            int play_id=Integer.valueOf(request.getParameter("play_id"));
            Timestamp sched_time=Timestamp.valueOf(request.getParameter("sched_time"));
            double sched_ticket_price = Integer.valueOf(request.getParameter("sched_ticket_price"));
            int sched_status=Integer.valueOf(request.getParameter("sched_status"));
            schedule=new Schedule(sched_id,studio_id,play_id,sched_time,sched_ticket_price,sched_status);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ScheduleSrv().modify(schedule) == 1) {
                out.write("数据修改成功");
                //根据演出计划修改票 前提没有已售出的票
                if(new TicketSrv().Check_ticket_status(sched_id) ==1) {//没有以售出的票 可以修改演出计划信息
//                    int colCount = new StudioSrv().QuerybyId(studio_id).getColCount();
//                    int rowCount = new StudioSrv().QuerybyId(studio_id).getRowCount();
                    List<Studio> stu = new StudioSrv().QuerybyId(4);
                    int colCount=0;
                    int rowCount=0;
                    for(Studio studio:stu){
                        rowCount=studio.getRowCount();
                        colCount=studio.getColCount();
                    }
                    for (int i = 0; i < rowCount; i++) {
                        for (int j = 0; j < colCount; j++) {
                            //new TicketSrv().Update_ticket(i * j, sched_id, studio_id, sched_ticket_price);
                        }
                    }
                }else {
                    response.getWriter().write("数据修改失败存在已出售的该票");
                }

            }
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
        int id= Integer.parseInt(request.getParameter("sched_id"));
        List<Schedule> result=null;

        if(id!=-1)
            result=new ScheduleSrv().Fetch(id);
        else {
            result = new ScheduleSrv().FetchAll();
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Schedule s : result)
            {

                json=new JSONObject();
                json.put("sched_id", s.getSched_id());
                json.put("studio_id", s.getStudio_id());
                json.put("play_id", s.getPlay_id());
                json.put("sched_time", s.getSched_time());
                json.put("sched_ticket_price", s.getSched_ticket_price());
                json.put("sched_status", s.getSched_status());
                array.put(json);
            }

            jsonStr=array.toString();
            //System.out.println(jsonStr);
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
