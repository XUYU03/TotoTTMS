package xupt.se.ttms.service;

import java.sql.SQLException;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;

public class ScheduleSrv{
    private final iScheduleDAO scheduleDAO=DAOFactory.creatScheduleDAO();

    public int insert_ticket(int seat_id,int sched_id ,double ticket_price) throws SQLException {
        return  scheduleDAO.insert_ticket(seat_id,sched_id,ticket_price);
    }

    public int add(Schedule schedule) throws SQLException {
        return scheduleDAO.insert(schedule);
    }

    public int modify(Schedule schedule){
        return scheduleDAO.update(schedule);
    }

    public int delete(int ID){
        return scheduleDAO.delete(ID);
    }

    public List<Schedule> Fetch(Integer id){
        return scheduleDAO.selectbyid(id);
    }

    public List<Schedule> FetchAll(){
        return scheduleDAO.select();
    }
}
