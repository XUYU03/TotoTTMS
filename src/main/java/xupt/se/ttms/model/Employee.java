package xupt.se.ttms.model;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class Employee {

    private Integer emp_id ;
    private Integer dict_id ;
    private String emp_no;
    private String emp_name;
    private Integer emp_gender;
    private String emp_telnum;
    private String emp_email;
    private  String emp_pwd ;
    private  Integer emp_status;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getDict_id() {
        return dict_id;
    }

    public void setDict_id(Integer dict_id) {
        this.dict_id = dict_id;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(Integer emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_telnum() {
        return emp_telnum;
    }

    public void setEmp_telnum(String emp_telnum) {
        this.emp_telnum = emp_telnum;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_pwd() {
        return emp_pwd;
    }

    public void setEmp_pwd(String emp_pwd) {
        this.emp_pwd = emp_pwd;
    }

    public Integer getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(Integer emp_status) {
        this.emp_status = emp_status;
    }

    public Employee(Integer emp_id, Integer dict_id, String emp_no, String emp_name, Integer emp_gender, String emp_telnum, String emp_email, String emp_pwd, Integer emp_status) {
        this.emp_id = emp_id;
        this.dict_id = dict_id;
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_gender = emp_gender;
        this.emp_telnum = emp_telnum;
        this.emp_email = emp_email;
        this.emp_pwd = emp_pwd;
        this.emp_status = emp_status;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", dict_id=" + dict_id +
                ", emp_no='" + emp_no + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", emp_gender=" + emp_gender +
                ", emp_telnum='" + emp_telnum + '\'' +
                ", emp_email='" + emp_email + '\'' +
                ", emp_pwd='" + emp_pwd + '\'' +
                ", emp_status=" + emp_status +
                '}';
    }
}