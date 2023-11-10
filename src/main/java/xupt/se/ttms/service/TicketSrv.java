package xupt.se.ttms.service;

import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class TicketSrv {
    private iTicketDAO ticketDAO = new TicketDAO();

    public int add(Ticket tic) throws SQLException {
        return ticketDAO.insert(tic);
    }

    public int modify(Ticket tic){
        return ticketDAO.update(tic);
    }

    public int delete(int ID){
        return ticketDAO.delete(ID);
    }

    public List<Ticket> Fetch(String  sched_id){
        return ticketDAO.select(sched_id);
    }
    public List<Ticket>  selectbyid(int id){
        return ticketDAO.selectbyid(id);
    }

    public List<Ticket> FetchAll(){
        return ticketDAO.select("");
    }

    public int insert_seat(int studio_id, int row , int col, int seat_status, Date now) throws SQLException {
        return  ticketDAO.insert_ticket(studio_id,row,col,seat_status,now);
    }

    //查看票状态 ticket_status
    public int Check_ticket_status(int sched_id ){
        return ticketDAO.Check_ticket_status(sched_id);
    }

    // 更新演出票
    public int Update_ticket(int seat_id, int sched_id , double sched_ticket_price) throws SQLException {
        return ticketDAO.Update_ticket(seat_id,sched_id,sched_ticket_price);
    }
}
