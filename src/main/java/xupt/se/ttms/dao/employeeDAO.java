package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iEmployee;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class employeeDAO  extends BasicDAO<Employee> implements iEmployee {

    @Override
    public Employee queryByName(String name ) {
        //String sql ="SELECT * FROM employee WHERE emp_name=?";
        String sql = "select * from employee where emp_name = ?";
        return  querySingle(sql, Employee.class,name);
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        String sql ="INSERT INTO `employee` ( `dict_id`,`emp_no`, `emp_name`, `emp_gender`, `emp_telnum`, `emp_email`, `emp_pwd`, `emp_status`)" +
                "VALUES(?,?,?,?,?,?,?,?) ";
        return update(sql,employee.getDict_id(),employee.getEmp_no(),employee.getEmp_name(),employee.getEmp_gender(),employee.getEmp_telnum(),employee.getEmp_email()
        ,employee.getEmp_pwd(),employee.getEmp_status());
    }

    @Override
    public int update(Employee employee) throws SQLException {
        String sql = "update employee set dict_id=? , emp_no=?,emp_name=?,emp_gender=?,emp_telnum=?,emp_email=?,emp_pwd=?,emp_status=? where emp_id = "+ employee.getEmp_id();

        return update(sql,employee.getDict_id(),employee.getEmp_no(),employee.getEmp_name(),
                employee.getEmp_gender(),employee.getEmp_telnum(),employee.getEmp_email(),
                employee.getEmp_pwd(),employee.getEmp_status());
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE emp_id =? ";
        return update(sql ,id);
    }

    @Override
    public List<Employee> select(String Name) {
        String sql=null;
        if(!Objects.equals(Name, "")){
            sql = "select * from employee where emp_name  like '%" + Name + "%'";
        }else{
            sql ="SELECT * FROM employee";
        }
        System.out.println();
        return queryMulti(sql, Employee.class);
    }

    public boolean QueryByU_idANDPWD(String u_id, String pwd) {
        String sql = "select * from employee WHERE emp_name=? AND emp_pwd=?";
        if(querySingle(sql, Employee.class,u_id,pwd) != null)
            return true;
        else
            return false;
    }
}
