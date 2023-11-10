package xupt.se.ttms.idao;

import xupt.se.ttms.model.Resource;

import java.sql.SQLException;
import java.util.List;

public interface iResourceDAO {
    Resource queryById(int id);

    public int insert(Resource resource) throws SQLException;

    public int update (Resource resource) throws SQLException;

    public List<Resource> select(String Name) ;

    int delete(int id) throws SQLException;
}
