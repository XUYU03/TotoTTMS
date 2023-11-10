import org.junit.Test;
import xupt.se.ttms.dao.customerDAO;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class LoginADNRegisterDAOTest {



    customerDAO customerDAO1=  new customerDAO();
    //new customerDAO().QueryByU_idANDPWD(u_id,pwd)

    @Test
    public void mane1(){
      if(  customerDAO1.QueryByU_idANDPWD("zhangsan","123456")){
          System.out.println("1");
      }else {
          System.out.println("2");
      }
    }
}
