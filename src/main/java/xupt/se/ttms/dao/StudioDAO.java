package xupt.se.ttms.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;
import xupt.se.util.JDBCUtilsByDruid;

public class StudioDAO extends BasicDAO<Studio> implements iStudioDAO
{

    @Override
    public int delect_seat(int studio_id) throws SQLException {
        String sql ="delete from seat WHERE studio_id =?";
        return  update(sql,studio_id);
    }

    @Override
    public int insert_seat(int studio_id, int row, int col, int seat_status) throws SQLException {
        String sql = "insert into seat(studio_id,seat_row,seat_column,seat_status) values(?,?,?,?)";
        return update(sql,studio_id,row,col,seat_status);
    }

    @Override
    public int Count() {
        int count =0 ;
        String sql = "SELECT COUNT(*) FROM studio";
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
        return count;
    }

    @Override
    public List<Studio> QueryALL() {
        String sql = "SELECT * FROM studio";
        return  queryMulti(sql, Studio.class);

    }
    @SuppressWarnings("finally")
    @Override
    public List<Studio> selectsched(String sched_id)
    {
        DBUtil db=null;
        List<Studio> stuList=null;
        stuList=new LinkedList<Studio>();
        try
        {
            String sql="select * from studio where studio_id =" + sched_id;
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
                    Studio stu=new Studio();
                    stu.setID(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRowCount(rst.getInt("studio_row_count"));
                    stu.setColCount(rst.getInt("studio_col_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    stuList.add(stu);
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
            return stuList;
        }
    }

    @Override
    public Studio QueryByid(int id) {
        String sql ="SELECT * FROM studio WHERE studio_id = "+id;
        return  querySingle(sql,Studio.class);

    }
    public List<Studio> selectbyid(int id)
    {
        DBUtil db=null;
        List<Studio> stuList=null;
        stuList=new LinkedList<Studio>();
        try
        {
            String sql="select * from studio where studio_id =" + id;
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
                    Studio stu=new Studio();
                    stu.setID(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRowCount(rst.getInt("studio_row_count"));
                    stu.setColCount(rst.getInt("studio_col_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    stuList.add(stu);
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
            return stuList;
        }
    }

    @SuppressWarnings("finally")

    @Override
    public int insert(Studio stu) throws SQLException {
//        String sql ="insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction ) values (?,?,?,?)";
//        Connection connection = null;
//        connection = JDBCUtilsByDruid.getConnection();
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        pstmt.setString(1, stu.getName());
//        pstmt.setInt(2, stu.getRowCount());
//        pstmt.setInt(3, stu.getColCount());
//        pstmt.setString(4, stu.getIntroduction());
//
//        int rowsAffected = pstmt.executeUpdate();
//
//        return stu;
              //  querySingle(sql,Studio.class,stu.getName(),stu.getRowCount(),stu.getColCount(),stu.getIntroduction());
        int result=0;
        try
        {
            String sql="insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction )"
                + " values('" + stu.getName() + "', " + stu.getRowCount() + ", " + stu.getColCount() + ", '"
                + stu.getIntroduction() + "' )";
            //插入座位

            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                stu.setID(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            result=1;
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

    @SuppressWarnings("finally")
    @Override
    public int update(Studio stu)
    {
        int result=0;
        try
        {
            String sql="update studio set " + " studio_name ='" + stu.getName() + "', " + " studio_row_count = "
                    + stu.getRowCount() + ", " + " studio_col_count = " + stu.getColCount() + ", "
                    + " studio_introduction = '" + stu.getIntroduction() + "' ";
            sql+=" where studio_id = " + stu.getID();
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
    public int delete(int ID)
    {
        int result=0;
        try
        {
            String sql="delete  from studio where studio_id = " + ID;
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

    @SuppressWarnings("finally")
    public String selectstudioid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select studio_name from studio  where studio_id= " + condt;
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
                    result=rst.getString("studio_name");
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
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public List<Studio> select(String studioName)
    {
        DBUtil db=null;
        List<Studio> stuList=null;
        stuList=new LinkedList<Studio>();
        try
        {
            studioName.trim();
            String sql="select * from studio where studio_name like '%" + studioName + "%'";
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
                    Studio stu=new Studio();
                    stu.setID(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRowCount(rst.getInt("studio_row_count"));
                    stu.setColCount(rst.getInt("studio_col_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    stuList.add(stu);
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
            return stuList;
        }
    }

}
