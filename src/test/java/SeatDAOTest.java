import org.junit.Test;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.idao.iSeatDAO;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class SeatDAOTest {
    private iSeatDAO seatDAO = new SeatDAO();

    @Test
    public void Query(){
        System.out.println(seatDAO.select(2));
    }

    @Test
    public void Query2(){
        System.out.println(seatDAO.selectAll());
    }
}
