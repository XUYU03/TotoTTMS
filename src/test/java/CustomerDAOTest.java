import org.junit.Test;
import xupt.se.ttms.dao.customerDAO;
import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class CustomerDAOTest {
    private iCustomerDAO customerDAO1 = new customerDAO();

    @Test
    public void queryById01(){

        System.out.println(customerDAO1.queryByName("张三"));
        System.out.println(customerDAO1.queryByName("李四"));
    }

    @Test
    public void queryALL(){
        System.out.println(customerDAO1.queryALL());

        for(Customer customer : customerDAO1.queryALL()){
            System.out.println(customer);
        }
    }
}
