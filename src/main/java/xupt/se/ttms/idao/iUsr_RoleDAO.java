package xupt.se.ttms.idao;


import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Usr_Role;

import java.sql.SQLException;
import java.util.List;

public interface iUsr_RoleDAO {


    public int insert(Usr_Role usrRole)throws SQLException;

    public int update(Usr_Role usrRole)throws SQLException;

    public int delete(int id)throws SQLException;

    List<Usr_Role> select(int id);

    List<Usr_Role> select(String emp_id);
}
