package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iResourceDAO;
import xupt.se.ttms.idao.iRolesDAO;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Roles;

import java.sql.SQLException;
import java.util.List;

public class RolesSrv {
    private iRolesDAO rolesDAO= (iRolesDAO) DAOFactory.creatRolesDAO();

    public int add(Roles roles) throws SQLException {
        return rolesDAO.insert(roles);
    }

    public int modify(Roles roles) throws SQLException {
        return rolesDAO.update(roles);
    }

    public int delete(int ID) throws SQLException {
        return rolesDAO.delete(ID);
    }

    public List<Roles> Fetch(String Name){
        return rolesDAO.select(Name);
    }

    public List<Roles> FetchAll(){
        return rolesDAO.select("");
    }
}
