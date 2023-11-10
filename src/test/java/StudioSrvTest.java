import org.junit.Test;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.idao.iStudioDAO;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class StudioSrvTest {
    private iStudioDAO iStudioDAO1 = new StudioDAO();

    @Test
    public void query01(){
        System.out.println(iStudioDAO1.select("1号厅"));
    }
}
