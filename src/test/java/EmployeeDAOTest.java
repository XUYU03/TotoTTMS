import org.junit.Test;
import org.w3c.dom.ls.LSOutput;
import xupt.se.ttms.dao.employeeDAO;
import xupt.se.ttms.idao.iEmployee;
import xupt.se.ttms.model.Employee;

import java.sql.SQLException;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class EmployeeDAOTest {

    private iEmployee iEmployee = new employeeDAO();
    @Test
    public void select(){
        System.out.println(iEmployee.select(""));
    }
    @Test
    public void inSert() throws SQLException {
        System.out.println(iEmployee.insert(new Employee(null,2, "EMP0002", "Jane Smith", 0, "+0987654321", "janesmith@example.com", "123456", 1)));
    }
    @Test
    public void Query1(){
        System.out.println(iEmployee.queryByName("J"));
    }
    @Test
    public void Insert01() throws SQLException {
//        System.out.println(iEmployee.insert(new Employee(1, "E1001",
//                "duhaojie", 1, "1234567890",
//                "duhaojie@example.com", "password123",1)));
    }

    @Test
    public void DELECT() throws SQLException {
       // System.out.println(iEmployee.delete("duhaojie"));
    }

    @Test
    public void QUERYALL(){
        System.out.println(iEmployee.select(""));
    }
}
