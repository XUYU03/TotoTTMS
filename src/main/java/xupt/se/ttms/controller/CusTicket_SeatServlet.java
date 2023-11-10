package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.model.*;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
@WebServlet("/CusTicket_SeatServlet")
public class CusTicket_SeatServlet  extends HttpServlet {


    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //System.out.println(1111);
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type=request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("lock"))
            lock(request, response);

        else if(type.equalsIgnoreCase("open"))
            open(request, response);

        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("lock1"))
            lock1(request, response);
        else if(type.equalsIgnoreCase("search2"))
            search2(request, response);

    }
    private void search2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        String [] array=new String[5];

        array[0]=request.getParameter("a");
        array[1]=request.getParameter("b");
        array[2]=request.getParameter("c");
        array[3]=request.getParameter("d");
        array[4]=request.getParameter("e");


        int [] arr=new int[5];
        for (int i=0;i<5;i++) {
            arr[i]=Integer.valueOf(array[i]);
        }

        int T=0;
        int play_id=0,studio_id=0;
        Date sched_time=null;
        PrintWriter out=response.getWriter();
        List<Ticket> result=null;
        List<Schedule> pl=null;
        for (int i=0;i<5;i++) {
            if (arr[i]!=0) {
                T=arr[i];
            }
        }
        //System.out.println(T);
        String t=String.valueOf(T);
        int sched_id=0;
        int seat[]=new int[5];
        DBUtil db1=null;
        try {
            db1=new DBUtil();
            if(!db1.openConnection())
            {
                System.out.print("fail to connect database");
                return ;
            }
            String sql2="select * from ticket where ticket_id="+t;


            ResultSet rst1=db1.execQuery(sql2);
            if (rst1!=null) {
                while(rst1.next()) {
                    sched_id=rst1.getInt("sched_id");

                }
            }
            String sql="select * from schedule where sched_id="+sched_id;
            ResultSet rst=db1.execQuery(sql);
            if (rst!=null) {
                while(rst.next()) {
                    studio_id=rst.getInt("studio_id");
                    play_id=rst.getInt("play_id");
                    sched_time=rst.getTimestamp("sched_time");
                }
            }
            //System.out.println(sched_id);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        /*
         * pl=new ScheduleSrv().Fetch(String.valueOf(sched_id)); for (Schedule s:pl) {
         * play_id=s.getPlay_id(); studio_id=s.getStudio_id();
         * sched_time=s.getSched_time(); }
         */
        //System.out.println(play_id+" "+studio_id+" "+sched_time);
        String pname="",sname="";
        DBUtil db=null;
        try {
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return ;
            }
            String sql="select * from studio where studio_id="+studio_id;
            String sql1="select *from play where play_id="+play_id;

            ResultSet rst=db.execQuery(sql);
            ResultSet rst1=db.execQuery(sql1);
            if (rst!=null) {
                while(rst.next()) {
                    sname=rst.getString("studio_name");
                }
            }
            if (rst1!=null) {
                while(rst1.next()) {
                    pname=rst1.getString("play_name");
                }
            }
            String sql2;
            for (int i=0;i<5;i++) {
                if (arr[i]!=0) {
                    sql2="select * from ticket where ticket_id="+arr[i];
                    ResultSet rst2=db.execQuery(sql2);
                    if (rst2!=null) {
                        while(rst2.next()) {
                            seat[i]=rst2.getInt("seat_id");
                        }
                    }
                }
            }
            String jsonStr="";
            try
            {
                JSONArray array1=new JSONArray();
                JSONObject json;

                for (int i=0;i<5;i++) {
                    if (seat[i]!=0) {
                        String sql3="select *from seat where seat_id="+seat[i];
                        ResultSet rst3=db.execQuery(sql3);
                        if (rst3!=null) {
                            while(rst3.next()) {


                                json=new JSONObject();
                                json.put("play_name", pname);
                                json.put("sched_time", sched_time);
                                json.put("studio_name", sname);
                                json.put("seat_row", rst3.getInt("seat_row"));
                                json.put("seat_col", rst3.getInt("seat_column"));
                                array1.put(json);

                            }
                        }
                    }
                }
                jsonStr=array1.toString();

            }catch(JSONException e)
            {
                e.printStackTrace();
            }
            finally
            {
                out.println(jsonStr);
                out.flush();
                out.close();
            }


            //System.out.println(sname+pname);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        result=new TicketSrv().Fetch(t);
        List<Seat> se=null;



    }
    private void open(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        int id=Integer.valueOf(request.getParameter("ticket_id"));
        DBUtil db=null;
        db=new DBUtil();
        int m=0;
        try {
            String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            //String locktime=lock(current);
            //System.out.println(current);
            String sql="update ticket set ticket_locktime='"+current+"',ticket_status=0 where ticket_id="+id;

            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return ;
            }
            m=db.execCommand(sql);

            db.close();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void lock1(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        int id=Integer.valueOf(request.getParameter("ticket_id"));
        DBUtil db=null;
        db=new DBUtil();
        int m=0;
        try {
            String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String locktime=lock(current);
            //System.out.println(current +"--"+locktime);
            String sql="update ticket set "  + "ticket_locktime='"+locktime+"',"+"ticket_status=1 where ticket_id="+id;

            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return ;
            }
            m=db.execCommand(sql);

            db.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void update(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

    }
    private void lock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        int i;
        int m=0;
        int [] array=new int[5];
        String tt1=request.getParameter("a");
        String tt2=request.getParameter("b");
        String tt3=request.getParameter("c");
        String tt4=request.getParameter("d");
        String tt5=request.getParameter("e");
        array[0]=Integer.valueOf(tt1);
        array[1]=Integer.valueOf(tt2);
        array[2]=Integer.valueOf(tt3);
        array[3]=Integer.valueOf(tt4);
        array[4]=Integer.valueOf(tt5);
        //System.out.println(t1+t2+t3+t4+t5);
        DBUtil db=null;
        db=new DBUtil();
        try {
            for (i=0;i<5;i++) {
                if (array[i]==0) {
                    continue;
                }
                String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String locktime=lock(current);
                //System.out.println(current +"--"+locktime);
                String sql="update ticket set "  + "ticket_locktime='"+locktime+"',"+"ticket_status=1 where ticket_id="+array[i];
                //ResultSet rst=null;
                if(!db.openConnection())
                {
                    System.out.print("fail to connect database");
                    return ;
                }
                m=db.execCommand(sql);
                //db.close(rst);
                db.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String lock(String current) {
        int year=Integer.valueOf(current.substring(0,4));
        int month=Integer.valueOf(current.substring(5, 7));
        int day=Integer.valueOf(current.substring(8, 10));
        int hour=Integer.valueOf(current.substring(11, 13));
        int min=Integer.valueOf(current.substring(14, 16));
        String mmin=current.substring(17, 19);
        String hour1,min1;
        min+=8;
        if (min>=60) {
            min-=60;
            hour++;
            if(hour==24) {
                hour-=24;
                day++;
            }
        }
        if (hour<10) {
            hour1="0"+String.valueOf(hour);
        }else {
            hour1=String.valueOf(hour);
        }
        if (min<10) {
            min1="0"+String.valueOf(min);
        }else {
            min1=String.valueOf(min);
        }
        String newtime=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day)+
                " "+hour1+":"+min1+":"+mmin;
        // TODO Auto-generated method stub
        return newtime;
    }


    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int schedule_ID = -1;
        int seat_id = -1;
        int ticket_id=-1;
        int seat_row = -1;
        int seat_column = -1;
        int ticket_status = -1;
        int studio_id = -1;
        int rowcount = -1;
        int colcount = -1;
        int seat_status=-1;


        int sched_id= Integer.parseInt(request.getParameter("sched_id"));
        PrintWriter out = response.getWriter();

        //得到总行列，生成座位图
        List<Studio> list3= new StudioSrv().QuerybyId(studio_id);
        for (Studio s:list3){
            rowcount=s.getRowCount();
            colcount=s.getColCount();
        }

        String jsonStr="";
        //通过seat_row和seat_column对应一个确定的座位及其信息
        for(int i=1;i<=rowcount;i++)
        {
            for(int j=1;j<=colcount;j++)
            {
                seat_id = new SeatSrv().selectseat_id(studio_id,i,j);
                List<Seat> list4=new SeatSrv().Fetch(seat_id);
                for(Seat seat:list4)
                {
                    seat_row=seat.getSeatRow();
                    seat_column=seat.getSeatColumn();
                    seat_status=seat.getSeatStatus();
                    //通过传入的seat_id得到对应的ticket数据
                    List<Ticket> list1 = new TicketSrv().selectbyid(seat_id);
//                    for(Ticket s :list1){
//                        ticket_id = s.getTicket_id();
//                        ticket_status=s.getTicket_status();
//                        try
//                        {
//                            JSONArray array=new JSONArray();
//                            JSONObject json;
//                            for(seatbean seatbean : result)
//                            {
//
//                                json=new JSONObject();
//                                json.put("play_id", s.getPlay_id());
//                                json.put("dict_type_id", s.getDict_type_id());
//                                json.put("dict_lang_id", s.getDict_lang_id());
//                                json.put("play_name", s.getPlay_name());
//                                json.put("play_introduction", s.getPlay_introduction());
//                                json.put("play_image", s.getPlay_image());
//                                json.put("play_video", s.getPlay_video());
//                                json.put("play_length", s.getPlay_length());
//                                json.put("play_ticket_price", s.getPlay_ticket_price());
//                                json.put("play_status", s.getPlay_status());
//
//                                array.put(json);
//                            }
//
//                            jsonStr=array.toString();
//                            //System.out.println(jsonStr);
//                        }
//                        catch(JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//                        finally
//                        {
//                            out.println(jsonStr);
//                            out.flush();
//                            out.close();
//                        }
//
//                    }
                }
            }
        }

    }
}
