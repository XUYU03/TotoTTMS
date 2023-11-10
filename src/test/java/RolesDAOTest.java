import org.junit.Test;
import xupt.se.ttms.dao.RolesDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Roles;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

public class RolesDAOTest {

    private xupt.se.ttms.idao.iRolesDAO iRolesDA01 = new RolesDAO();

    /**
     * (int ID, String name, int rowCount, int colCount, String intro)
     */

    @Test
    public void inster01() throws SQLException {
        System.out.println(iRolesDA01.insert(new Roles(7,"炎龙侠")));
    }

    @Test
    public void queryById(){
        System.out.println(iRolesDA01.select("炎龙侠"));

        //  System.out.println(iStudioDAO.select("1号厅"));
        //  xupt.se.ttms.model.Studio@52525845
    }

    @Test
    public void QUERYAll(){
        List<Roles> roles = iRolesDA01.select("");
        for (Roles role : roles) {
            // 执行操作
            System.out.println(role);
        }

        System.out.println(iRolesDA01.select(""));
    }

//
//    public void Count(){
//        System.out.println(iStudioDAO.Count());
//    }
}
