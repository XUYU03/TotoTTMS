package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/CusScheduleServlet")
public class CusScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String type = req.getParameter("type");

            if(type.equalsIgnoreCase("search1")) {
                search(req, resp);
            }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //System.out.println("111111111");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out=resp.getWriter();
        int play_id = Integer.parseInt(req.getParameter("play_id"));
        System.out.println(play_id);
        DBUtil db = null;
        List<Schedule> schList = null;
        schList = new LinkedList<Schedule>();
        String sql=null;
        try {
            if(play_id!=0){
                sql = "select * from schedule where play_id="+play_id;
            }else{
                System.out.println("all");
                sql="select * from schedule";
            }
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                return ;
            }
            ResultSet rst = db.execQuery(sql);
            if (rst != null) {
                while (rst.next()) {
                    Schedule stu = new Schedule();
                    stu.setSched_id(rst.getInt("sched_id"));
                    stu.setStudio_id(rst.getInt("studio_id"));
                    stu.setPlay_id(rst.getInt("play_id"));
                    stu.setSched_time(rst.getTimestamp("Sched_time"));
                    stu.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
                    stu.setSched_status(rst.getInt("sched_status"));
                    schList.add(stu);
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Schedule s : schList)
            {
                json=new JSONObject();
                json.put("sched_time", s.getSched_time());
                json.put("studio_id", s.getStudio_id());
                json.put("play_id",s.getPlay_id());
                json.put("sched_id",s.getSched_id());
                json.put("sched_ticket_price", s.getSched_ticket_price());
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

