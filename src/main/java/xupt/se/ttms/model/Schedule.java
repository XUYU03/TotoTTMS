package xupt.se.ttms.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

//package xupt.se.ttms.model;


public class Schedule {
    private int sched_id=0     ;
    private int studio_id=0 ;
    private int play_id=0;
    private Timestamp sched_time;
    private Double sched_ticket_price;
    private  int sched_status=0;

    public Schedule(int sched_id, int studio_id, int play_id, Timestamp sched_time, double sched_ticket_price, int sched_status) {
        this.sched_id = sched_id;
        this.studio_id = studio_id;
        this.play_id = play_id;
        this.sched_time = sched_time;
        this.sched_ticket_price = sched_ticket_price;
        this.sched_status = sched_status;
    }
    public Schedule(){

    }



    public int getSched_id() {
        return sched_id;
    }

    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }

    public int getStudio_id() {
        return studio_id;
    }

    public void setStudio_id(int studio_id) {
        this.studio_id = studio_id;
    }

    public int getPlay_id() {
        return play_id;
    }

    public void setPlay_id(int play_id) {
        this.play_id = play_id;
    }

    public Date getSched_time() {
        return sched_time;
    }

    public void setSched_time(Timestamp sched_time) {
        this.sched_time = sched_time;
    }

    public Double getSched_ticket_price() {
        return sched_ticket_price;
    }

    public void setSched_ticket_price(Double sched_ticket_price) {
        this.sched_ticket_price = sched_ticket_price;
    }

    public int getSched_status() {
        return sched_status;
    }

    public void setSched_status(int sched_status) {
        this.sched_status = sched_status;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sched_id=" + sched_id +
                ", studio_id=" + studio_id +
                ", play_id=" + play_id +
                ", sched_time=" + sched_time +
                ", sched_ticket_price=" + sched_ticket_price +
                ", sched_status=" + sched_status +
                '}';
    }
}

