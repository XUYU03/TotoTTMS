package xupt.se.ttms.idao;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Ticket;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface iTicketDAO {

    public int Update_ticket(int seat_id,int sched_id ,double sched_ticket_price) throws SQLException;
    public int insert(Ticket ticket);

     public int delete(int ID);

    public int update(Ticket ticket);


    List<Ticket> select(String sched);

    public int insert_ticket(int studio_id, int seat_id , int sched_id , int ticket_price , Date now) throws SQLException;

    public int Check_ticket_status(int sched_id );

    List<Ticket> selectbyid(int id);
}