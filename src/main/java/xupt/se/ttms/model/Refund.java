package xupt.se.ttms.model;

import java.util.Date;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class Refund {
    private String cus_name ;
    private Date refund_time;
    private String refund_sort;//退款方式

    @Override
    public String toString() {
        return "Refund{" +
                "cus_name='" + cus_name + '\'' +
                ", refund_time=" + refund_time +
                ", refund_sort='" + refund_sort + '\'' +
                '}';
    }

    public Refund() {
    }

    public Refund(String cus_name, Date refund_time, String refund_sort) {
        this.cus_name = cus_name;
        this.refund_time = refund_time;
        this.refund_sort = refund_sort;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public Date getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(Date refund_time) {
        this.refund_time = refund_time;
    }

    public String getRefund_sort() {
        return refund_sort;
    }

    public void setRefund_sort(String refund_sort) {
        this.refund_sort = refund_sort;
    }
}
