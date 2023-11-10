package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployee;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

public class EmployeeSrv {

    private iEmployee empDAO= DAOFactory.creatEmployeeDAO();

    public int add(Employee emp) throws SQLException {
        return empDAO.insert(emp);
    }

    public int modify(Employee emp) throws SQLException {
        return empDAO.update(emp);
    }

    public int delete(int ID) throws SQLException {
        return empDAO.delete(ID);
    }

    public List<Employee> Fetch(String condt){
        return empDAO.select(condt);
    }

    public List<Employee> FetchAll(){
        return empDAO.select("");
    }
}
