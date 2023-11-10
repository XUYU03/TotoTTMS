package xupt.se.ttms.dao;


import xupt.se.ttms.idao.iRole_resourceDAO;

import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Role_resource;

import java.sql.SQLException;
import java.util.List;

public class Role_resourceDAO extends BasicDAO<Role_resource> implements iRole_resourceDAO {

    @Override
    public int insert(Role_resource role_resource) throws SQLException{
        String sql ="INSERT INTO `role_resource` (`role_res_id`, `role_id`, `res_id`) VALUES (?,?,?)";
        return update(sql,role_resource.getRole_res_id(),role_resource.getRole_id(),role_resource.getRole_res_id());

    }

    @Override
    public int delete(int id) throws SQLException {
        String sql="delete from role_resource  where role_res_id = ?";

        return update(sql,id);
    }

    @Override
    public int update(Role_resource role_resource) {
        String sql = "";
        return 0;
    }

    @Override
    public List<Role_resource> select(Role_resource role_resource) {
        String sql = "select * FROM role_resource";
        return queryMulti(sql , Role_resource.class);
    }
}
