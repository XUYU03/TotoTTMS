package xupt.se.ttms.idao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import xupt.se.ttms.model.Schedule;

public interface iScheduleDAO
{


    public int insert(Schedule sch) throws SQLException;

    public int update(Schedule sch);

    public int delete(int ID);

    public List<Schedule> select();

    public int insert_ticket(int seat_id,int sched_id ,double ticket_price) throws SQLException;

    List<Schedule> selectbyid(Integer id);

    public int selectshed_id();

    public int first_seat_id(int studio_id);
}
