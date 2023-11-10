package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class SeatSrv {
    private iSeatDAO stuDAO= DAOFactory.creatSeatDAO();

    public int add(Seat stu) throws SQLException {
        return stuDAO.insert(stu);
    }
    public int add1(Seat stu){
        return stuDAO.insert1(stu);
    }
    public int modify(Seat stu) throws SQLException {
        return stuDAO.update(stu);
    }

    public int delete(int ID){
        return stuDAO.delete(ID);
    }

    public List<Seat> Fetch(int condt){
        return stuDAO.select(condt);
    }

    public List<Seat> FetchAll(){
        return stuDAO.select(0);
    }

    public int selectseat_id(int studioId, int rowcount, int colcount) {
        return stuDAO.selSeat_id(studioId,rowcount,colcount);
    }
}
