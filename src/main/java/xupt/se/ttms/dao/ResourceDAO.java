package xupt.se.ttms.dao;

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.idao.iResourceDAO;

import xupt.se.ttms.dao.BasicDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ResourceDAO extends BasicDAO<Resource> implements iResourceDAO {
    @Override
    public Resource queryById(int id) {
        String sql = "select `res_id`, `res_parent`, `res_name`, `res_URL` FROM resource WHERE `res_id`=?";
        return querySingle(sql, Resource.class,id);
    }

    @Override
    public int insert(Resource resource) throws SQLException {
        String sql ="INSERT INTO `resource` (`res_id`, `res_parent`, `res_name`, `res_URL`) VALUES (?,?,?,?)";
        return update(sql,resource.getRes_id(),resource.getRes_parent(),resource.getRes_name(),resource.getRes_URL());

    }



    @Override
    public int update(Resource resource) throws SQLException {
        int result=0;
        try
        {
            String sql="update resource set " + " res_parent ='" + resource.getRes_parent() + "', " + " res_name = '"
                    + resource.getRes_name() + "', " + " res_URL = '" + resource.getRes_URL()+"'";
            sql+=" where res_id = " + resource.getRes_id();
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }}

    @Override
    public List<Resource> select(String Name) {
        String sql=null;
        if(!Objects.equals(Name, "")){
            sql = "select * from resource where res_name  like '%" + Name + "%'";
        }else{
            sql = "select * FROM resource";
        }
        return queryMulti(sql , Resource.class);
    }

    @Override
    public int delete(int id) throws SQLException{
        String sql="delete from resource  where res_id = ?";
        return update(sql,id);
    }
}

