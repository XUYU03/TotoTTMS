package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iResourceDAO;
import xupt.se.ttms.model.Resource;

import java.sql.SQLException;
import java.util.List;

public class ResourceSrv {
    private iResourceDAO resourceDAO= DAOFactory.creatResourceDAO();

    public int add(Resource resource) throws SQLException {
        return resourceDAO.insert(resource);
    }

    public int modify(Resource resource) throws SQLException {
        return resourceDAO.update(resource);
    }

    public int delete(int ID) throws SQLException {
        return resourceDAO.delete(ID);
    }

    public List<Resource> Fetch(String Name){
        return resourceDAO.select(Name);
    }

    public List<Resource> FetchAll(){
        return resourceDAO.select("");
    }
}
