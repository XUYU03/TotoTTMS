package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Roles;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SaleDAO extends BasicDAO<Sale> implements iSaleDAO {



    @SuppressWarnings("finally")
    @Override
    public int insert(Sale sale)throws SQLException{
        String sql ="INSERT INTO `sale` ( `emp_id`,'cus_id','sale_time','sale_payment','sale_change','sale_type','sale_status','sale_sort') VALUES (?,?,?,?,?,?,?,?)";
        return update(sql,sale.getEmp_id(),sale.getCus_id(),sale.getSale_time(),sale.getSale_payment(),sale.getSale_change(),sale.getSale_type(),sale.getSale_status(),sale.getSale_sort());

    }

//    @Override
//    public int delete(int id) throws SQLException {
//        String sql="delete from sale  where sale_ID = ?";
//
//        return update(sql,id);
//    }


    @SuppressWarnings("finally")
    @Override
    public int update(Sale sale) throws SQLException {
        String sql = "UPDATE sale set emp_id=?,cus_id=?,sale_time=?,sale_payment=?,sale_change=?,sale_type=?,sale_status=?,sale_sort=? where sale_ID = " +sale.getSale_ID();
        return update(sql,sale.getEmp_id(),sale.getCus_id(),sale.getSale_time(),sale.getSale_payment(),sale.getSale_change(),sale.getSale_type(),sale.getSale_status(),sale.getSale_sort());
    }

    @SuppressWarnings("finally")
    @Override
    public List<Sale> select(int id) {
//        String sql = null;
//        if (id==-1) {
//             sql = "select * FROM sale";
//        }else{
//             sql = "select * FROM sale WHERE sale_ID =" +id;
//        }
//        return queryMulti(sql,Sale.class);
        DBUtil db=null;
        List<Sale> saleList=null;
        saleList=new LinkedList<Sale>();
        String sql = null;
        try
        {
            if(id==-1){
                sql="select * from sale";
            }
            else {
                sql="select * from sale where sale_ID = " +id ;
            }
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
                    Sale sale=new Sale();
                    sale.setSale_ID(rst.getInt("sale_ID"));
                    sale.setEmp_id(rst.getInt("emp_id"));
                    sale.setCus_id(rst.getInt("cus_id"));
                    sale.setSale_time(rst.getTime("sale_time"));
                    sale.setSale_payment(rst.getDouble("sale_payment"));
                    sale.setSale_change(rst.getDouble("sale_change"));
                    sale.setSale_type(rst.getInt("sale_type"));
                    sale.setSale_status(rst.getInt("sale_status"));
                    sale.setSale_sort(rst.getInt("sale_sort"));
                    saleList.add(sale);
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
            return saleList;
        }
    }


    @Override
    public int insert1(Sale stu)
    {
        int result=0;
        try
        {
            String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String sql="insert into sale( emp_id, sale_time, sale_payment,sale_change,sale_type,sale_status,sale_sort )"
                    + " values("  + stu.getEmp_id() + ", '" + current + "', "+stu.getSale_payment()+","
                    + stu.getSale_change() + ", "+stu.getSale_type()+","+stu.getSale_status()+","+stu.getSale_sort()+")";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                stu.setSale_ID(rst.getInt(1));
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
}
