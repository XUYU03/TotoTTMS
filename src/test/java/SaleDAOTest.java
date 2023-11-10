import org.junit.Test;
import xupt.se.ttms.dao.SaleDAO;
import xupt.se.ttms.idao.iSaleDAO;

import java.sql.SQLException;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class SaleDAOTest {

    private iSaleDAO saleDAO = new SaleDAO();

    @Test
    public void query01(){
        System.out.println(saleDAO.select(-1));
    }

    @Test
    public void query02(){
     //   System.out.println(saleDAO.select(0));
    }

    @Test
    public void delect() throws SQLException {
      //  System.out.println(saleDAO.delete(2));
    }

    @Test
    public void query03(){
        System.out.println(saleDAO);
    }


}
