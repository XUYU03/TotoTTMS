package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO extends BasicDAO<Ticket> implements iTicketDAO
{
    @Override
    public int Update_ticket(int seat_id,int sched_id,  double sched_ticket_price) throws SQLException {
        String sql ="UPDATE ticket SET seat_id=?, sched_id=?,ticket_price=? WHERE studio_id=?";
        return update(sql,seat_id,sched_id,sched_ticket_price);
    }

    @Override
    public int Check_ticket_status(int sched_id) {
        String sql ="SELECT * FROM ticket WHERE sched_id=? AND ticket_status=9 ";
        List<Ticket> list = queryMulti(sql,Ticket.class,sched_id);
        if(list.size() > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int insert_ticket(int studio_id, int seat_id, int sched_id, int ticket_price, Date now) throws SQLException {
        return 0;
    }

    @SuppressWarnings("finally")
    @Override
    public int insert(Ticket stu)
    {
        int result=0;
        int seat[] = new int[200];
        double price = 0;
        int sched_id=stu.getSched_id();
        int i=0;
        int j=1;
        try
        {
            int studio_id=0;
            studio_id=selectstudioid(sched_id);
            DBUtil db=new DBUtil();

            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }


            String sql2="select * from seat where studio_id="+studio_id+" and seat_status="+j;
            ResultSet rst2=db.execQuery(sql2);

            while(rst2.next()) {
                seat[i]=rst2.getInt("seat_id");
                String sql1="select * from schedule where sched_id="+sched_id;
                ResultSet rst1=db.execQuery(sql1);
                if (rst1!=null) {
                    while(rst1.next()) {
                        price=rst1.getDouble("sched_ticket_price");
                    }
                }

                String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Timestamp time = Timestamp.valueOf(current);

                String sql="insert into ticket(seat_id, sched_id, ticket_price, ticket_status ,ticket_locktime)"
                        + " values(" + seat[i] + ", " + stu.getSched_id() + ", " + price + ", "
                        + stu.getTicket_status() + ",'"+time+"' )";

                ResultSet rst=db.getInsertObjectIDs(sql);
                if(rst != null && rst.first())
                {
                    stu.setTicket_id(rst.getInt(1));
                }
                i++;
            }

            db.close(rst2);
            db.close();
            result=1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public int update(Ticket stu)
    {
        int result=0;
        try
        {
            String sql="update ticket set " + " seat_id ='" + stu.getSeat_id() + "', " + " sched_id = "
                    + stu.getSched_id() + ", " + " ticket_price = " + stu.getTicket_price() + ", "
                    + " ticket_status = '" + stu.getTicket_status() + ", "+"ticket_locktime="+stu.getTicket_locktime();
            sql+=" where ticket_id = " + stu.getTicket_id();
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @Override
    public int delete(int ID)
    {
        int result=0;
        try
        {
            String sql="delete from  ticket where ticket_id = " + ID;
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    @SuppressWarnings("finally")
    public int selectstudioid(int condt)
    {
        DBUtil db=null;
        int result=0;
        try
        {
            String sql="select studio_id from schedule  where sched_id= " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    result=rst.getInt("studio_id");
                    System.out.println(result);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    public String selectticketid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select sched_id from ticket  where ticket_id= " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    result=rst.getString("sched_id");
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }


    public List<Ticket> selectbyid(int id){
        DBUtil db=null;
        String sql;
        List<Ticket> stuList=null;
        stuList=new LinkedList<Ticket>();
        String time;
        try
        {
            sql="select * from ticket where sched_id =" + id ;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Ticket stu=new Ticket();
                    stu.setTicket_id(rst.getInt("ticket_id"));
                    stu.setSeat_id(rst.getInt("seat_id"));
                    stu.setSched_id(rst.getInt("sched_id"));
                    stu.setTicket_price(rst.getDouble("ticket_price"));
                    time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rst.getTimestamp("ticket_locktime"));
                    if (li(time) && rst.getInt("ticket_status")==1) {
                        stu.setTicket_status(0);
                    }else {
                        stu.setTicket_status(rst.getInt("ticket_status"));
                    }
                    stu.setTicket_locktime(rst.getTimestamp("ticket_locktime"));
                    stuList.add(stu);
                }
            }
            db.close(rst);

            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return stuList;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public List<Ticket> select(String sched)
    {
        DBUtil db=null;
        String sql;
        List<Ticket> stuList=null;
        stuList=new LinkedList<Ticket>();
        String time;

        try
        {
            if (sched.equals("")) {
                sql="select * from ticket";
            }else {
                int sched_id=Integer.valueOf(sched);
                sql="select * from ticket where sched_id =" + sched_id ;
            }
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Ticket stu=new Ticket();
                    stu.setTicket_id(rst.getInt("ticket_id"));
                    //sql1="update ticket set ticket_status =0 where ticket_id="+rst.getInt("ticket_id");
                    stu.setSeat_id(rst.getInt("seat_id"));
                    stu.setSched_id(rst.getInt("sched_id"));
                    stu.setTicket_price(rst.getDouble("ticket_price"));
                    time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rst.getTimestamp("ticket_locktime"));
                    //System.out.println(time);
                    if (li(time) && rst.getInt("ticket_status")==1) {
                        stu.setTicket_status(0);
                        //rst1=db.execQuery(sql1);
                    }else {
                        stu.setTicket_status(rst.getInt("ticket_status"));
                    }
                    //stu.setTicket_status(rst.getInt("ticket_status"));

                    stu.setTicket_locktime(rst.getTimestamp("ticket_locktime"));
                    //System.out.println(stu.getTicket_status());
                    stuList.add(stu);
                }
            }
            db.close(rst);

            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return stuList;
        }
    }

    private boolean li(String time) {
        int year=Integer.valueOf(time.substring(0,4));
        int month=Integer.valueOf(time.substring(5, 7));
        int day=Integer.valueOf(time.substring(8, 10));
        int hour=Integer.valueOf(time.substring(11, 13));
        int min=Integer.valueOf(time.substring(14, 16));
        int  mmin=Integer.valueOf(time.substring(17, 19));
        String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int year1=Integer.valueOf(current.substring(0,4));
        int month1=Integer.valueOf(current.substring(5, 7));
        int day1=Integer.valueOf(current.substring(8, 10));
        int hour1=Integer.valueOf(current.substring(11, 13));
        int min1=Integer.valueOf(current.substring(14, 16));
        int mmin1=Integer.valueOf(time.substring(17, 19));
        if (year<year1) {
            return true;
        }else if (year==year1) {
            if(month<month1) {
                return true;
            }else if(month==month1) {
                if(day<day1) {
                    return true;
                }else if (month==month1) {
                    if(hour<hour1) {
                        return true;
                    }else if(hour==hour1){
                        if (min<min1) {
                            return true;
                        }else if(min==min1) {
                            if(mmin<mmin1) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
