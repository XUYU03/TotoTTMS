package xupt.se.ttms.idao;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

import java.sql.SQLException;
import java.util.List;

public interface iSeatDAO {

        public int insert1(Seat stu);
        public int insert(Seat seat) throws SQLException;

        public int update(Seat seat) throws SQLException;

        public int delete(int id);

    List<Seat> select(int seat_id);
    List<Seat> selectAll();

    int selSeat_id(int studioId, int rowcount, int colcount);
}
