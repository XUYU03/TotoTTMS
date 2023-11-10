package xupt.se.ttms.idao;

import xupt.se.ttms.model.Saleitem;

import java.util.List;

public interface iSaleitemDAO {
    public int insert(Saleitem saleitem);

    public int delete(Saleitem saleitem);

    public int update(Saleitem saleitem);

    public List<Saleitem> select(Saleitem saleitem);
}
