package xupt.se.ttms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.util.DBUtil;

public class ScheduleDAO extends BasicDAO<Schedule> implements iScheduleDAO
{
    /*
    * return生成的主键
    * */
    @Override
    public int insert(Schedule sch) throws SQLException {
        String sql ="INSERT INTO `schedule` ( `studio_id`, `play_id`, `sched_time`,`sched_ticket_price`,`sched_status`) VALUES (?,?,?,?,?)";
        update(sql,sch.getStudio_id(),sch.getPlay_id(),sch.getSched_time(),sch.getSched_ticket_price(),sch.getSched_status());
        return 1;
    }

    @Override
    public int update(Schedule sch)
    {
        int result=0;
        try
        {
            String sql;
            if(sch.getPlay_id()==-1 && sch.getStudio_id()==-1) {
                sql="update Schedule set "  + " sched_time = '" + sch.getSched_time() + "', "
                        + " sched_ticket_price = " + sch.getSched_ticket_price() + ", "
                        + " sched_status = " + sch.getSched_status() + "";

            }else if(sch.getStudio_id()==-1) {
                sql="update Schedule set "  + " play_id ="
                        +sch.getPlay_id() + ", " + " sched_time = '" + sch.getSched_time() + "', "
                        + " sched_ticket_price = " + sch.getSched_ticket_price() + ", "
                        + " sched_status = " + sch.getSched_status() + "";
            }else if(sch.getPlay_id()==-1) {
                sql="update Schedule set " + "  studio_id =" + sch.getStudio_id()  + ", " + " sched_time = '" + sch.getSched_time() + "', "
                        + " sched_ticket_price = " + sch.getSched_ticket_price() + ", "
                        + " sched_status = " + sch.getSched_status() + "";
            }else {
                sql="update Schedule set " + "  studio_id =" + sch.getStudio_id() + ", " + " play_id ="
                        +sch.getPlay_id() + ", " + " sched_time = '" + sch.getSched_time() + "', "
                        + " sched_ticket_price = " + sch.getSched_ticket_price() + ", "
                        + " sched_status = " + sch.getSched_status() + "";
            }
            sql+=" where sched_id = " + sch.getSched_id();
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
            String sql="delete from  Schedule where sched_id = " + ID;
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


    @Override
    public List<Schedule> select() {
        DBUtil db = null;
        List<Schedule> schList = null;
        schList = new LinkedList<Schedule>();
        try {
            String sql = "select * from schedule";
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                return null;
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
        } finally {
            return schList;
        }
    }
    public int selectshed_id(){
        DBUtil db = null;
        int result = 0;
        try {
            String sql = "select * from schedule";
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                result=0;
            }
            ResultSet rst = db.execQuery(sql);
            if(rst != null){
                while (rst.next()){
                    result=rst.getInt("sched_id");
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public int first_seat_id(int studio_id){
        DBUtil db = null;
        int result = 0;
        try {
            //根据座位所在演厅找到座位对应字段->通过座位状态查找座位现在是否有演出计划，1表示没有
            String sql = "select * from seat where studio_id= '"+studio_id+"' and seat_status=0" ;
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                result=0;
            }

            ResultSet rst = db.execQuery(sql);
            if(rst != null){
                rst.next();
                result=rst.getInt("seat_id");
            }
            result=rst.getInt("seat_id");
            if (result!=0) {
                    sql = "UPDATE seat SET seat_status = 1 where studio_id = "+studio_id;
                    update(sql);
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public List<Schedule> selectbyid(Integer id)
    {
        DBUtil db = null;
        List<Schedule> schList = null;
        schList = new LinkedList<Schedule>();
        try {
            String sql = "select * from schedule where sched_id = "+ id;
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                return null;
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
        } finally {
            return schList;
        }
    }

    @Override
    public int insert_ticket(int seat_id,int sched_id, double ticket_price) throws SQLException {

        String sql = "INSERT INTO `ticket`(`seat_id`,`sched_id`,`ticket_price`,`ticket_status`) VALUES(?,?,?,0)";
        update(sql,seat_id,sched_id,ticket_price);
        return 1;
    }
}

