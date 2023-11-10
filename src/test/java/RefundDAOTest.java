import org.junit.Test;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class RefundDAOTest {
    private xupt.se.ttms.dao.RefundDAO refund = new xupt.se.ttms.dao.RefundDAO();

    @Test
    public void query(){
        System.out.println(refund.Query());
    }
}
