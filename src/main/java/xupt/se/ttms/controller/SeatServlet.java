package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */

@WebServlet("/SeatServlet")
public class SeatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("add")) {
            try {
                add(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(type.equalsIgnoreCase("delete"))
            delete(req, resp);
        else if(type.equalsIgnoreCase("update")) {
            try {
                update(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(type.equalsIgnoreCase("search"))
            search(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Seat ses = null;
        int studio_id = Integer.parseInt(req.getParameter("studio_id"));
        int seat_row =Integer.parseInt( req.getParameter("seat_row"));
        int seat_column = Integer.parseInt(req.getParameter("seat_column"));
        int seat_status = Integer.parseInt(req.getParameter("seat_status"));
        ses = new Seat(0,studio_id,seat_row,seat_column,seat_status);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if ((new SeatDAO().insert(ses)) !=0 )
            out.write("座位设置成功");
        else
            out.write("座位设置失败");
        out.close();
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Integer seat_id = Integer.valueOf(req.getParameter("seat_id"));
            resp.setContentType("test/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write(new SeatDAO().delete(seat_id));
        } catch(Exception e)
        {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("操作错误，请重试");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Seat ses = null;
        int seat_id = Integer.parseInt(req.getParameter("seat_id"));
        int cus_id = Integer.parseInt(req.getParameter("studio_id"));
        int seat_row =Integer.parseInt( req.getParameter("seat_row"));
        int seat_column = Integer.parseInt(req.getParameter("seat_column"));
        int seat_status = Integer.parseInt(req.getParameter("seat_status"));

        ses = new Seat(seat_id,cus_id,seat_row,seat_column,seat_status);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        if(new SeatDAO().update(ses) ==1)
            out.write("座位修改成功");
        else
            out.write("座位修改失败");

        out.close();
    }



    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out=resp.getWriter();
        int seat_id=Integer.parseInt(req.getParameter("seat_id"));
        List<Seat> result =null;
        if(seat_id!=-1){
            result = new SeatDAO().select(seat_id);
        }else {
            result = new SeatDAO().selectAll();
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Seat s : result)
            {
                json=new JSONObject();
                json.put("seat_id", s.getSeatId());
                json.put("studio_id", s.getStudioId());
                json.put("seat_row", s.getSeatRow());
                json.put("seat_column", s.getSeatColumn());
                json.put("seat_status", s.getSeatStatus());
                //System.out.println(json);
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
