package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class PlaySrv {
    private iPlayDAO playDAO= DAOFactory.createPlayDAO();
    public int add(Play play) throws SQLException {
        return playDAO.insert(play);
    }

    public int modify(Play play) throws SQLException {
        return playDAO.update(play);
    }

    public int delete(int ID) throws SQLException {
        return playDAO.delete(ID);
    }

    public List<Play> Fetch(String condt){
        return playDAO.select(condt);
    }

    public List<Play> FetchAll(){
        return playDAO.select("");
    }
}
