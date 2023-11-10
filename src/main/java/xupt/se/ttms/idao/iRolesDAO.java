package xupt.se.ttms.idao;

import org.w3c.dom.ls.LSException;
import xupt.se.ttms.model.Roles;

import java.sql.SQLException;
import java.util.List;

public interface iRolesDAO {
    public int insert(Roles roles)throws SQLException;

    public int delete(int id)throws SQLException;

    public int update(Roles roles);

//    public List<Roles> select(Roles roles);
    public List<Roles>  select(String Name);

}
