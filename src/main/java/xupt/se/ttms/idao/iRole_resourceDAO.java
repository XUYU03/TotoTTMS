package xupt.se.ttms.idao;

import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Role_resource;

import java.sql.SQLException;
import java.util.List;

public interface iRole_resourceDAO {
    public int insert(Role_resource role_resource) throws SQLException;

    public int delete(int id) throws SQLException;

    public int update(Role_resource role_resource);

    public List<Role_resource> select(Role_resource role_resource);
}
