import org.junit.Test;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.idao.iScheduleDAO;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class ScheduleDAOtest {

    private iScheduleDAO scheduleDAO = new ScheduleDAO();

//    @Test
//    public void  Query(){
//        System.out.println(scheduleDAO.QueryALL());
//    }
    private iScheduleDAO s3cheduleDAO =new ScheduleDAO();

//    @Test
//    public void Query(){
//        System.out.println(scheduleDAO.select(Double.MAX_VALUE));
//    }

    @Test
    public void Query01(){
        System.out.println(scheduleDAO.select());
    }

    @Test
    public void insert_ticket() throws SQLException {
            System.out.println(scheduleDAO.insert_ticket(1,1,1));
    }

    @Test
    public void reselectedid(){
        System.out.println(scheduleDAO.selectshed_id());
    }

    @Test
    public void getfirst(){
        System.out.println(scheduleDAO.first_seat_id(38));
    }
}
