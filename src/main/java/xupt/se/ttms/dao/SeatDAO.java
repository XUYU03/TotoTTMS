package xupt.se.ttms.dao;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeatDAO extends BasicDAO<Seat> implements iSeatDAO {
    @SuppressWarnings("finally")
    @Override
    public int insert1(Seat stu)
    {
        int result=0;
        int studio_id=stu.getStudioId();
        //System.out.println(studio_id);
        int i=1;
        int j=1;
        int row=0,col=0;
        try
        {
            DBUtil db=new DBUtil();

            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }

            /*
             * String sql2="select * from seat where studio_id="+studio_id; ResultSet
             * rst2=db.execQuery(sql2);
             */



            String sql1="select * from studio where studio_id="+studio_id;
            ResultSet rst1=db.execQuery(sql1);
            if (rst1!=null) {
                while(rst1.next()) {
                    row=rst1.getInt("studio_row_count");
                    col=rst1.getInt("studio_col_count");

                }
            }
            while(i<=row && j<=col+1) {
                if (j>col) {
                    i++;
                    j=1;
                }
                if (i>row) {
                    break;
                }
                String sql="insert into seat(studio_id, seat_row, seat_column ,seat_status)"
                        + " values(" + studio_id + ", " + i + ", " +j+ ", "+1+" )";

                ResultSet rst=db.getInsertObjectIDs(sql);
                if(rst != null && rst.first())
                {
                    stu.setSeatId(rst.getInt(1));
                }
                j++;


                //db.close(rst2);

            }
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
    @Override

    public List<Seat> selectAll() {
        DBUtil db = null;
        List<Seat> seatList = null;
        seatList = new LinkedList<Seat>();
        try {
            String sql = "select * from seat";
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst = db.execQuery(sql);
            if (rst != null) {
                while (rst.next()) {
                    Seat seat = new Seat();
                    seat.setSeatId(rst.getInt("seat_id"));
                    seat.setStudioId(rst.getInt("studio_id"));
                    seat.setSeatRow(rst.getInt("seat_row"));
                    seat.setSeatColumn(rst.getInt("seat_column"));
                    seat.setSeatStatus(rst.getInt("seat_status"));
                    seatList.add(seat);
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return seatList;
        }
    }

    @Override
    public int insert(Seat seat) throws SQLException {
        System.out.println("22222222222222");
        String sql="INSERT INTO seat(studio_id,seat_row,seat_column,seat_status) values(?,?,?,?)";
        return  update(sql,seat.getStudioId(),seat.getSeatRow(),seat.getSeatColumn(),seat.getSeatStatus());
    }

    @Override
    public int update(Seat seat) throws SQLException {
        String sql = "UPDATE seat SET  studio_id = ? , seat_row = ? , seat_column= ? , seat_status = ? where seat_id = "+ seat.getSeatId();
        return update(sql,seat.getStudioId(),seat.getSeatRow(),seat.getSeatColumn(),seat.getSeatStatus());
    }

    @Override
    public int delete(int ID) {
        int result = 0;
        try {
            String sql = "delete from  seat where seat_id = " + ID;
            DBUtil db = new DBUtil();
            db.openConnection();
            db.execCommand(sql);
            db.close();
            result=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Seat> select(int seat_id)
    {
        DBUtil db = null;
        List<Seat> seatList = null;
        seatList = new LinkedList<Seat>();
        try {
            String sql = "select * from seat where seat_id = "+ seat_id;
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst = db.execQuery(sql);
            if (rst != null) {
                while (rst.next()) {
                    Seat seat = new Seat();
                    seat.setSeatId(rst.getInt("seat_id"));
                    seat.setStudioId(rst.getInt("studio_id"));
                    seat.setSeatRow(rst.getInt("seat_row"));
                    seat.setSeatColumn(rst.getInt("seat_column"));
                    seat.setSeatStatus(rst.getInt("seat_status"));
                    seatList.add(seat);
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return seatList;
        }
    }

    public int selSeat_id(int studioId, int rowcount, int colcount)
    {
        DBUtil db = null;
        int result =0;
        List<Seat> seatList = null;
        seatList = new LinkedList<Seat>();
        try {
            String sql = "select * from seat where studio_id = '"+ studioId+"' and seat_row ='" +rowcount+"' and seat_column = '"+colcount+"'" ;
            db = new DBUtil();
            if (!db.openConnection()) {
                System.out.print("fail to connect database");
            }
            ResultSet rst = db.execQuery(sql);
            if (rst != null) {
                while (rst.next()) {
                    result = rst.getInt("seat_id");
                }
            }
            db.close(rst);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


}
