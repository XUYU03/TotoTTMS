package xupt.se.ttms.dao;

import xupt.se.ttms.model.Refund;

import javax.management.Query;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class RefundDAO extends BasicDAO<Refund> {

    //显示退票信息
    /**
     * 使用多表联合查询 牵扯到 雇员表 顾客表 和 sale 表 其中 sale表中字段 sale_type=-1 , sale_status=1 再拿到sale 表单中的sale_sort 显示退票方式
     *
     * 显示顾客姓名 --->employee表单获取
     * 退票时间 ---->sale 表单获取
     * 退款方式 ---->sale 表单获取
     */

    //重新封装 pojo对象-> Refund   javabean类

      public List<Refund> Query(){
          String sql= "select cus_name , sale_time,sale_sort FROM customer ,sale WHERE sale.cus_id = customer.cus_id AND sale_type=-1 AND sale_status=1" ;
          return queryMulti(sql,Refund.class);
      }

      public List<Refund> QueryLikecus_Name(String cus_name){
          String sql ="SELECT select cus_name , sale_time,sale_sort FROM customer ,sale WHERE sale.cus_id = customer.cus_id AND sale_type=-1 AND sale_status=1 AND cus_name=?";
        return queryMulti(sql,Refund.class,cus_name);
      }


}
