package xupt.se.ttms.model;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class Customer  implements Serializable {

    private Integer cus_id ;
    private String cus_name ;
    private Integer cus_gender;
    private String cus_telnum;
    private String cus_email;
    private String cus_uid ;
    private String cus_pwd ;
    private Integer cus_status;
    private double cus_balance ;
    private String cus_paypwd ;

    public Customer() {
    }

    public Customer(Integer cus_id, String cus_name, Integer cus_gender, String cus_telnum, String cus_email, String cus_uid, String cus_pwd, Integer cus_status, double cus_balance, String cus_paypwd) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_gender = cus_gender;
        this.cus_telnum = cus_telnum;
        this.cus_email = cus_email;
        this.cus_uid = cus_uid;
        this.cus_pwd = cus_pwd;
        this.cus_status = cus_status;
        this.cus_balance = cus_balance;
        this.cus_paypwd = cus_paypwd;
    }

    public Integer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Integer cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public Integer getCus_gender() {
        return cus_gender;
    }

    public void setCus_gender(Integer cus_gender) {
        this.cus_gender = cus_gender;
    }

    public String getCus_telnum() {
        return cus_telnum;
    }

    public void setCus_telnum(String cus_telnum) {
        this.cus_telnum = cus_telnum;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public String getCus_uid() {
        return cus_uid;
    }

    public void setCus_uid(String cus_uid) {
        this.cus_uid = cus_uid;
    }

    public String getCus_pwd() {
        return cus_pwd;
    }

    public void setCus_pwd(String cus_pwd) {
        this.cus_pwd = cus_pwd;
    }

    public Integer getCus_status() {
        return cus_status;
    }

    public void setCus_status(Integer cus_status) {
        this.cus_status = cus_status;
    }

    public double getCus_balance() {
        return cus_balance;
    }

    public void setCus_balance(double cus_balance) {
        this.cus_balance = cus_balance;
    }

    public String getCus_paypwd() {
        return cus_paypwd;
    }

    public void setCus_paypwd(String cus_paypwd) {
        this.cus_paypwd = cus_paypwd;
    }

    @Override
    public String toString() {
        return "customerDAO{" +
                "cus_id=" + cus_id +
                ", cus_name='" + cus_name + '\'' +
                ", cus_gender=" + cus_gender +
                ", cus_telnum='" + cus_telnum + '\'' +
                ", cus_email='" + cus_email + '\'' +
                ", cus_uid='" + cus_uid + '\'' +
                ", cus_pwd='" + cus_pwd + '\'' +
                ", cus_status=" + cus_status +
                ", cus_balance=" + cus_balance +
                ", cus_paypwd='" + cus_paypwd + '\'' +
                '}';
    }
}
