package xupt.se.ttms.idao;

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

public interface iCustomerDAO {

    public Customer queryByName(String name);

    public List<Customer> queryALL();

    public int insert(Customer cus) throws SQLException;

    public int update(Customer cus);

    public int delete(int ID);

    public List<Customer> select(String cusName);
}
