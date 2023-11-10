package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iRolesDAO;
import xupt.se.ttms.model.Role_resource;
import xupt.se.ttms.model.Roles;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RolesDAO extends BasicDAO<Roles> implements iRolesDAO {
    @Override
    public int insert(Roles role) throws SQLException {
        String sql ="INSERT INTO `roles` (`role_name`) VALUES (?)";
        return update(sql,role.getRole_name());
    }

    @Override
    public int delete(int ID)
    {
        int result=0;
        try
        {
            String sql="delete from roles where role_id = " + ID;
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int update(Roles rol)
    {
        int result=0;
        try
        {
            String sql="update Roles set " + "role_name ='" + rol.getRole_name()+ "' ";
            sql+=" where role_id = " + rol.getRole_id();
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
        }
    }
    @Override
    public List<Roles> select(String rolName)
    {
        DBUtil db=null;
        List<Roles> rolList=null;
        rolList=new LinkedList<Roles>();
        try
        {
            rolName.trim();
            String sql="select * from Roles where role_name like '%" + rolName + "%'";
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Roles rol=new Roles();
                    rol.setRole_id(rst.getInt("role_id"));
                    rol.setRole_name(rst.getString("role_name"));
                    rolList.add(rol);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return rolList;
        }
    }

}
