package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import static xupt.se.util.DataUtils.parseInt;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet {
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
        Play play=null;
        int id=0;
        try
        {
            int dict_type_id= Integer.valueOf(request.getParameter("dict_type_id"));
            int dict_lang_id=Integer.valueOf(request.getParameter("dict_lang_id"));
            int play_length=Integer.valueOf(request.getParameter("play_length"));
            String play_name=request.getParameter("play_name");
            String play_introduction = request.getParameter("play_introduction");
            String play_image = request.getParameter("play_image");
            String play_video = request.getParameter("play_video");
            Double play_ticket_price =Double.valueOf(request.getParameter("play_ticket_price"));
            int play_status =parseInt(request.getParameter("play_status"),0);

            play=new Play(id,dict_type_id,dict_lang_id,play_name,play_introduction,
                    play_image,play_video,play_length,new BigDecimal(play_ticket_price),play_status);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            System.out.println(play);
            if((new PlaySrv().add(play)) != 0)
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

            int id=Integer.valueOf(request.getParameter("play_id"));

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            System.out.println("112233");
            out.write("" + new PlaySrv().delete(id));
            System.out.println("445566");
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
        Play play=null;
        int id=0;
        try
        {
            id=Integer.parseInt(request.getParameter("play_id"));
            int dict_type_id= Integer.parseInt(request.getParameter("dict_type_id"));
            int dict_lang_id=Integer.parseInt(request.getParameter("dict_lang_id"));
            int play_length=Integer.parseInt(request.getParameter("play_length"));
            String play_name=request.getParameter("play_name");
            String play_introduction = request.getParameter("play_introduction");
            String play_image = request.getParameter("play_image");
            String play_video = request.getParameter("play_video");
            BigDecimal play_ticket_price =BigDecimal.valueOf(Long.parseLong(request.getParameter("play_ticket_price")));
            int play_status =parseInt(request.getParameter("play_status"),0);

            play =new Play(id,dict_type_id,dict_lang_id,play_name,play_introduction,
                    play_image,play_video,play_length,play_ticket_price,play_status);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new PlaySrv().modify(play) == 1)
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
       // System.out.println("3333333333333333");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("play_name");
        System.out.println(name);
        List<Play> result=null;

        if(name != null && name.length() > 0)
            result=new PlaySrv().Fetch(name);
        else {
            System.out.println("123");
            result = new PlaySrv().FetchAll();
//            for(Studio studio : result){
//                System.out.println(studio.getName());
//            }
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Play s : result)
            {

                json=new JSONObject();
                json.put("play_id", s.getPlay_id());
                json.put("dict_type_id", s.getDict_type_id());
                json.put("dict_lang_id", s.getDict_lang_id());
                json.put("play_name", s.getPlay_name());
                json.put("play_introduction", s.getPlay_introduction());
                json.put("play_image", s.getPlay_image());
                json.put("play_video", s.getPlay_video());
                json.put("play_length", s.getPlay_length());
                json.put("play_ticket_price", s.getPlay_ticket_price());
                json.put("play_status", s.getPlay_status());

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
//    protected static void massage(HttpServletRequest request ,HttpServletResponse response){
//        int dict_type_id= Integer.valueOf(request.getParameter("dict_type_id"));
//        int dict_lang_id=Integer.valueOf(request.getParameter("dict_lang_id"));
//        int play_length=Integer.valueOf(request.getParameter("play_length"));
//        String play_name=request.getParameter("play_name");
//        String play_introduction = request.getParameter("play_introduction");
//        String play_image = request.getParameter("play_image");
//        String play_video = request.getParameter("play_video");
//        BigDecimal play_ticket_price =BigDecimal.valueOf(Long.parseLong(request.getParameter("play_ticket_price")));
//        String play_status = request.getParameter("play_status");
//
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
