package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class customerDAO extends BasicDAO<Customer> implements iCustomerDAO {
    @Override
    public List<Customer> queryALL() {
        String sql = "select * FROM customer";
        return queryMulti(sql , Customer.class);
    }

    @Override
    public Customer queryByName(String name) {
        String sql = "select `cus_name`, `cus_gender`, `cus_telnum`, `cus_email`, `cus_uid`, `cus_pwd`, `cus_status`, `cus_balance`, `cus_paypwd` FROM customer WHERE `cus_name`=?";
        return querySingle(sql,Customer.class,name);
    }

    @Override
    public int insert(Customer cus) throws SQLException {

       String sql ="INSERT INTO `customer` (`cus_name`, `cus_gender`, `cus_telnum`, `cus_email`, `cus_uid`, `cus_pwd`, `cus_status`, `cus_balance`, `cus_paypwd`) VALUES (?,?,?,?,?,?,?,?,?)";
       return update(sql,cus.getCus_name(),cus.getCus_gender(),cus.getCus_telnum(),cus.getCus_email(),cus.getCus_uid(),cus.getCus_pwd(),cus.getCus_status(),cus.getCus_balance(),cus.getCus_paypwd());

//        int result=0;
//        try
//        {
//            String sql="INSERT INTO `customer` (`cus_name`, `cus_gender`, `cus_telnum`, `cus_email`, `cus_uid`, `cus_pwd`, `cus_status`, `cus_balance`, `cus_paypwd`)"
//                    + " values(" + cus.getCus_name() + ", " + cus.getCus_gender() + ", " + cus.getCus_telnum() + ", "
//                    + cus.getCus_email() +"',"+cus.getCus_uid()+"',"+cus.getCus_pwd()+"',"+cus.getCus_status()+"',"+cus.getCus_balance()
//                    + "',"+ cus.getCus_paypwd()+ "' )";
//            DBUtil db=new DBUtil();
//            db.openConnection();
//            ResultSet rst=db.getInsertObjectIDs(sql);
//            if(rst != null && rst.first())
//            {
//                cus.setCus_id(rst.getInt(1));
//            }
//            db.close(rst);
//            db.close();
//            result=1;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            return result;
//        }
    }

    @Override
    public int update(Customer cus) {
        int result=0;
        System.out.println("customerdao");
        try
        {
            String sql="update customer set " + " cus_name ='" + cus.getCus_name() + "', " + " cus_gender = "
                    + cus.getCus_gender() + ", " + " cus_telnum = " + cus.getCus_telnum() + ", "
                    + " cus_email = '" + cus.getCus_email() + "' " +", " +" cus_uid = '"+cus.getCus_uid()+"', "  +" cus_pwd = '"+ cus.getCus_pwd() +"', "
                    + "cus_status = '" + cus.getCus_status()+"',"+"cus_balance = '"+cus.getCus_balance()+"'," +"cus_paypwd = '"+cus.getCus_paypwd()+"'";
            sql+=" where cus_id = " + cus.getCus_id();
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
    public int delete(int ID) {
//        String sql="delete from customer where cus_uid = ?";
//
//        return update(sql,UID);
        int result=0;
        try
        {
            String sql="delete from  customer where cus_id = " + ID;
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
    public List<Customer> select(String Name) {
        String sql=null;
        if(!Objects.equals(Name, "")){
            sql = "select * from customer where cus_name  like '%" + Name + "%'";
        }else{
            sql ="SELECT * FROM customer";
        }
        return queryMulti(sql, Customer.class);
    }


    public boolean QueryByU_idANDPWD(String u_id ,String pwd){
        String sql = "select * from customer WHERE cus_uid=? AND cus_pwd=?";
        if(querySingle(sql, Customer.class,u_id,pwd) != null)
            return true;
        else
            return false;
    }
}

