package xupt.se.ttms.model;

public class seatbean {
    private int studio_id;
    private int rowcount;
    private int colcount;
    private int seat_row;
    private int seat_column;
    private int seat_status;
    private int ticket_id;
    private int ticket_status;

    public seatbean() {
    }

    public seatbean(int studio_id, int rowcount, int colcount, int seat_row, int seat_column, int seat_status, int ticket_id, int ticket_status) {
        this.studio_id = studio_id;
        this.rowcount = rowcount;
        this.colcount = colcount;
        this.seat_row = seat_row;
        this.seat_column = seat_column;
        this.seat_status = seat_status;
        this.ticket_id = ticket_id;
        this.ticket_status = ticket_status;
    }

    public int getStudio_id() {
        return studio_id;
    }

    public void setStudio_id(int studio_id) {
        this.studio_id = studio_id;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getColcount() {
        return colcount;
    }

    public void setColcount(int colcount) {
        this.colcount = colcount;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int seat_row) {
        this.seat_row = seat_row;
    }

    public int getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(int seat_column) {
        this.seat_column = seat_column;
    }

    public int getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(int seat_status) {
        this.seat_status = seat_status;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(int ticket_status) {
        this.ticket_status = ticket_status;
    }

    @Override
    public String toString() {
        return "seatbean{" +
                "studio_id=" + studio_id +
                ", rowcount=" + rowcount +
                ", colcount=" + colcount +
                ", seat_row=" + seat_row +
                ", seat_column=" + seat_column +
                ", seat_status=" + seat_status +
                ", ticket_id=" + ticket_id +
                ", ticket_status=" + ticket_status +
                '}';
    }
}
