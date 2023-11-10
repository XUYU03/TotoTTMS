import org.junit.Test;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class StudioDAOTest {

    private iStudioDAO iStudioDAO = new StudioDAO();

    /**
     * (int ID, String name, int rowCount, int colCount, String intro)
     */

    @Test
    public void inster01() throws SQLException {
        System.out.println(iStudioDAO.insert(new Studio(7,"火神殿",11,11,"炉子热")));
    }

    @Test
    public void queryById(){
        System.out.println(iStudioDAO.QueryByid(3));

      //  System.out.println(iStudioDAO.select("1号厅"));
      //  xupt.se.ttms.model.Studio@52525845
    }

    @Test
    public void QUERYAll(){
        List<Studio> studios = iStudioDAO.QueryALL();
        for (Studio studio : studios) {
            // 执行操作
            System.out.println(studio);
        }

        System.out.println(iStudioDAO.QueryALL());
    }

    @Test
    public void Count(){
        System.out.println(iStudioDAO.Count());
    }
}
