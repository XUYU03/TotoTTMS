package xupt.se.ttms.idao;

import xupt.se.ttms.model.Sale;

import java.sql.SQLException;
import java.util.List;

public interface iSaleDAO {
    public int insert (Sale sale)throws SQLException;

//    public int delete (int id)throws SQLException;

    public int update (Sale sale) throws SQLException;

    public List<Sale> select(int id);

    public int insert1(Sale sale);

}
