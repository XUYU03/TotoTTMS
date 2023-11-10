package xupt.se.ttms.idao;

import xupt.se.ttms.model.Play;

import java.sql.SQLException;
import java.util.List;

public interface iPlayDAO {
    Play queryById(int id);

    public int insert (Play play) throws SQLException;



    public int delete(int id) throws SQLException;

    public int update (Play play) throws SQLException;

    public List<Play> select(String PlayName);



}
