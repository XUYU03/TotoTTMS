package xupt.se.ttms.idao;

import xupt.se.ttms.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface iEmployee {

    public Employee queryByName(String name);

    public int insert(Employee employee) throws SQLException;

    public int update(Employee employee) throws SQLException;

    public int delete(int ID) throws SQLException;

    public List<Employee> select(String Name);
}
