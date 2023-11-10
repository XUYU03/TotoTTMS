package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

public class CustomerSrv {
    private iCustomerDAO CusDAO = DAOFactory.creatCustomerDAO();

    public int add(Customer cus) throws SQLException {
        return CusDAO.insert(cus);
    }

    public int modify(Customer cus){
        return CusDAO.update(cus);
    }

    public int delete(int ID){
        return CusDAO.delete(ID);
    }

    public List<Customer> Fetch(String condt){
        return CusDAO.select(condt);
    }

    public List<Customer> FetchAll(){
        return CusDAO.select("");
}
}



