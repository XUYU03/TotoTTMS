package xupt.se.ttms.dao;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Usr_Role;
import xupt.se.ttms.idao.iUsr_RoleDAO;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Usr_RoleDAO extends BasicDAO<Usr_Role> implements iUsr_RoleDAO {
    @Override
    public int insert(Usr_Role usrRole) throws SQLException {
        String sql = "INSERT INTO `usr_role` (`usr_role_id`, `emp_id`, `role_id`) VALUES (?,?,?)";
        return update(sql, usrRole.getUsrRoleId(), usrRole.getEmpId(), usrRole.getRoleId());

//            int result = 0;
//            try {
//                String sql = "insert into Usr_Role(emp_id, role_id)"
//                        + " values('" + usrRole.getEmpId() + "', " + usrRole.getRoleId()  + "' )";
//                DBUtil db = new DBUtil();
//                db.openConnection();
//                ResultSet rst = db.getInsertObjectIDs(sql);
//                if (rst != null && rst.first()) {
//                    usrRole.setUsrRoleId(rst.getInt(1));
//                }
//                db.close(rst);
//                db.close();
//                result = 1;
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                return result;
//            }
    }


    @Override
    public int update(Usr_Role usrRole) throws SQLException {
        String sql = "UPADTE FROM usr_role set `emp_id`=?, `role_id`=?";
        return update(sql, Usr_Role.class, usrRole.getEmpId(), usrRole.getRoleId());
//            int result=0;
//            try
//            {
//                String sql="update usr_role set " + " usr_role_id ='" + usrRole.getUsrRoleId() + "', " + " emp_id = "
//                        + usrRole.getEmpId() + ", " + " role.id = " + usrRole.getRoleId() +"' ";
//                sql+=" where usr_role_id = " + usrRole.getUsrRoleId();
//                DBUtil db=new DBUtil();
//                db.openConnection();
//                result=db.execCommand(sql);
//                db.close();
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//                return result;
//            }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete from usr_role  where usr_role_id = ?";

        return update(sql, id);
//            int result=0;
//            try
//            {
//                String sql="delete from  studio where usr_role_id = " + id;
//                DBUtil db=new DBUtil();
//                db.openConnection();
//                result=db.execCommand(sql);
//                db.close();
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//            return result;
    }


    @Override
    public List<Usr_Role> select(int id) {
        String sql = "select `usr_role_id`, `emp_id`, `role_id` FROM usr_role WHERE `usr_role_id`=?";
        return queryMulti(sql, Usr_Role.class);
    }

    @Override
    public List<Usr_Role> select(String emp_id) {
        String sql = "select * FROM usr_role";
        return queryMulti(sql , Usr_Role.class);
    }
}
//        DBUtil db=null;
//        List<Usr_Role> usrRolesList=null;
//        usrRolesList=new LinkedList<>();
//        try
//        {
//            String sql="select * from studio where emp_id like '%" + emp_id + "%'";
//            db=new DBUtil();
//            if(!db.openConnection())
//            {
//                System.out.print("fail to connect database");
//                return null;
//            }
//            ResultSet rst=db.execQuery(sql);
//            if(rst != null)
//            {
//                while(rst.next())
//                {
//                    Usr_Role usr=new Usr_Role();
//                    usr.setUsrRoleId(rst.getInt("usr_role_id"));
//                    usr.setEmpId(rst.getInt("emp_id"));
//                    usr.setRoleId(rst.getInt("role_id"));
//                    usrRolesList.add(usr);
//                }
//            }
//            db.close(rst);
//            db.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            return usrRolesList;
//        }



