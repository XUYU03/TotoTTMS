package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Ticket;

import java.sql.SQLException;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class Ticket_generateDAO  extends  BasicDAO<Ticket>{

    private iStudioDAO studioDAO = new StudioDAO();

    public int insert_studiorow_studiocol_seat(int studio_id) throws SQLException {
       int count = 0;

        int rowCount= studioDAO.QueryByid(studio_id).getRowCount();

       int ColCount= studioDAO.QueryByid(studio_id).getColCount();
        for (int i = 1; i <rowCount ; i++) {
            for (int j = 1; j <ColCount ; j++) {
                String sql = "insert into seat(studio_id,seat_row,seat_column,seat_status)values(?,?,?,?)" ;
                 update(sql,studio_id,i,j,1);
                count++;
            }
        }
        return count;
    }

}
