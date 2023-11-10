package xupt.se.ttms.dao;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import xupt.se.ttms.idao.iData_DictDAO;
import xupt.se.ttms.model.Data_Dict;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;


public class Data_DictDAO extends BasicDAO<Data_Dict> implements iData_DictDAO
{


    @Override
    public Data_Dict selectById(Integer dictId) {
        String sql ="SELECT * FROM data_dict WHERE dict_id = ?";
        return  querySingle(sql ,Data_Dict.class,dictId);
    }

    @Override
    public List<Data_Dict> selectAll() {
        String sql = "SELECT * FROM data_dict";
        return  queryMulti(sql, Data_Dict.class);
    }

    @Override
    public int insert(Data_Dict dataDict) {
        int result=0;
        try
        {
            String sql="insert into data_dict(dict_id ='" + dataDict.getDict_id() + "', " + " super_dict_id = "
                    + dataDict.getSuper_dict_id() + ", " + " dict_index = " + dataDict.getDict_index() + ", "
                    + " dict_name = '" + dataDict.getDict_name()+ "' )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
               dataDict.setDict_id(rst.getInt(1));
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


    @Override
    public int update(Data_Dict dataDict) {
        int result=0;
        try
        {
            String sql="update data_dict set " + " dict_id ='" + dataDict.getDict_id() + "', " + " super_dict_id = "
                    + dataDict.getSuper_dict_id() + ", " + " dict_index = " + dataDict.getDict_index() + ", "
                    + " dict_name = '" + dataDict.getDict_name() + "' ";
            sql+=" where dict_id = " + dataDict.getDict_id();
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
    public int delete(Integer dictId) {
        int result=0;
        try
        {
            String sql="delete from  date_dict where studio_id = " + dictId;
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
}
