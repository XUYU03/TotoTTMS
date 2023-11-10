package xupt.se.ttms.model;

import java.sql.Timestamp;

public class Ticket {
    private int ticket_id;
    private int seat_id;
    private int sched_id;
    private double ticket_price;
    private int ticket_status;
    private Timestamp ticket_locktime;
    public Ticket() {
        ticket_id=0;
    }
    public Ticket(int ticket_id, int seat_id, int sched_id, double ticket_price, int ticket_status,
                  Timestamp ticket_locktime) {
        super();
        this.ticket_id = ticket_id;
        this.seat_id = seat_id;
        this.sched_id = sched_id;
        this.ticket_price = ticket_price;
        this.ticket_status = ticket_status;
        this.ticket_locktime = ticket_locktime;
    }
    public int getTicket_id() {
        return ticket_id;
    }
    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }
    public int getSeat_id() {
        return seat_id;
    }
    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }
    public int getSched_id() {
        return sched_id;
    }
    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }
    public double getTicket_price() {
        return ticket_price;
    }
    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }
    public int getTicket_status() {
        return ticket_status;
    }
    public void setTicket_status(int ticket_status) {
        this.ticket_status = ticket_status;
    }
    public Timestamp getTicket_locktime() {
        return ticket_locktime;
    }
    public void setTicket_locktime(Timestamp ticket_locktime) {
        this.ticket_locktime = ticket_locktime;
    }

}
