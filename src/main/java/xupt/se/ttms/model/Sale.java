package xupt.se.ttms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Sale {
    private Integer sale_ID;

    private Integer emp_id;

    private Integer cus_id;

    private Date sale_time;

    private Double sale_payment;

    private Double sale_change;

    private Integer sale_type;

    private Integer sale_status;

    private Integer sale_sort;

    public Sale(Integer sale_ID, Integer emp_id, Integer cus_id, Date sale_time, Double sale_payment, Double sale_change, Integer sale_type, Integer sale_status, Integer sale_sort) {
        this.sale_ID = sale_ID;
        this.emp_id = emp_id;
        this.cus_id = cus_id;
        this.sale_time = sale_time;
        this.sale_payment = sale_payment;
        this.sale_change = sale_change;
        this.sale_type = sale_type;
        this.sale_status = sale_status;
        this.sale_sort = sale_sort;
    }

    public Integer getSale_ID() {
        return sale_ID;
    }

    public void setSale_ID(Integer sale_ID) {
        this.sale_ID = sale_ID;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Integer cus_id) {
        this.cus_id = cus_id;
    }

    public Date getSale_time() {
        return sale_time;
    }

    public void setSale_time(Date sale_time) {
        this.sale_time = sale_time;
    }

    public Double getSale_payment() {
        return sale_payment;
    }

    public void setSale_payment(Double sale_payment) {
        this.sale_payment = sale_payment;
    }

    public Double getSale_change() {
        return sale_change;
    }

    public void setSale_change(Double sale_change) {
        this.sale_change = sale_change;
    }

    public Integer getSale_type() {
        return sale_type;
    }

    public void setSale_type(Integer sale_type) {
        this.sale_type = sale_type;
    }

    public Integer getSale_status() {
        return sale_status;
    }

    public void setSale_status(Integer sale_status) {
        this.sale_status = sale_status;
    }

    public Integer getSale_sort() {
        return sale_sort;
    }

    public void setSale_sort(Integer sale_sort) {
        this.sale_sort = sale_sort;
    }

    public Sale() {
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sale_ID=" + sale_ID +
                ", emp_id=" + emp_id +
                ", cus_id=" + cus_id +
                ", sale_time=" + sale_time +
                ", sale_payment=" + sale_payment +
                ", sale_change=" + sale_change +
                ", sale_type=" + sale_type +
                ", sale_status=" + sale_status +
                ", sale_sort=" + sale_sort +
                '}';
    }
}
