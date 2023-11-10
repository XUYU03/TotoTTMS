package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class SaleSrv {

    private iSaleDAO saleDAO = DAOFactory.creatSalesDAO();

    public int add(Sale stu) throws SQLException {
        return saleDAO.insert(stu);
    }

    public int modify(Sale stu) throws SQLException {
        return saleDAO.update(stu);
    }


    public List<Sale> Fetch(int id){
        return saleDAO.select(id);
    }

    public List<Sale> FetchAll(){
        return saleDAO.select(-1);
    }
    public int add1(Sale stu){
        return saleDAO.insert1(stu);
    }
}
