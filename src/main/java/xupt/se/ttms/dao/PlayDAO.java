package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PlayDAO extends BasicDAO<Play> implements iPlayDAO {

    @Override
    public Play queryById(int id) {
        String sql ="SELECT * FROM play WHERE play_id = ?";
        return  querySingle(sql ,Play.class,id);
    }

    @Override
    public int insert(Play play) throws SQLException {
        String sql = "INSERT INTO `play` VALUES (?,?,?,?,?,?,?,?,?,?)";

        return update(sql,0,play.getDict_type_id(),play.getDict_lang_id(),play.getPlay_name()
        ,play.getPlay_introduction(),play.getPlay_image(),play.getPlay_video(),play.getPlay_length(),play.getPlay_ticket_price()
        ,play.getPlay_status());
//        int result=0;
//        try
//        {
//            String sql="insert into play(" + " dict_type_id = "
//                    + play.getDict_type_id() + ", " + " dict_lang_id = " + play.getDict_lang_id() + ", "
//                    + " play_name = '" + play.getPlay_name()+ "' play_introduction = '" + play.getPlay_introduction() + "', "
//                    + " play_image = '" + play.getPlay_image() + "', "+ " play_video = '" + play.getPlay_video() + "', "
//                    + " play_length = " + play.getPlay_length() + ", "+ " play_ticket_price = " + play.getPlay_ticket_price() + ", "
//                    + " play_status = " + play.getPlay_status() + " )";
//            DBUtil db=new DBUtil();
//            db.openConnection();
//            ResultSet rst=db.getInsertObjectIDs(sql);
//            if(rst != null && rst.first())
//            {
//                play.setPlay_id(rst.getInt(1));
//            }
//            db.close(rst);
//            db.close();
//            result=1;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            return result;
//        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql="delete from  play where play_id = ? " ;
        return update(sql,id);
        }


    @Override
    public int update(Play play) throws SQLException {
        String sql = "UPDATE  play SET dict_type_id = ? , dict_lang_id = ? , play_name= ? , play_introduction = ? , play_image = ?, play_video = ? , play_length = ? ,play_ticket_price = ? , play_status = ?  where play_id = "+ play.getPlay_id();
        return update(sql,play.getDict_type_id(),play.getDict_lang_id(),play.getPlay_name()
                ,play.getPlay_introduction(),play.getPlay_image(),play.getPlay_video(),play.getPlay_length(),play.getPlay_ticket_price()
                ,play.getPlay_status());
    }

    @Override
    public List<Play> select(String PlayName) {
        DBUtil db=null;
        List<Play> playList=null;
        playList=new LinkedList<Play>();
        try
        {
            PlayName.trim();
            String sql="select * from play where  play_name like '%" + PlayName + "%'";
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Play play=new Play();
                    play.setPlay_id(rst.getInt("play_id"));
                    play.setDict_type_id(rst.getInt("dict_type_id"));
                    play.setDict_lang_id(rst.getInt("dict_lang_id"));
                    play.setPlay_name(rst.getString("play_name"));
                    play.setPlay_introduction(rst.getString("play_introduction"));
                    play.setPlay_image(rst.getString("play_image"));
                    play.setPlay_video(rst.getString("play_video"));
                    play.setPlay_length(rst.getInt("play_length"));
                    play.setPlay_ticket_price(rst.getBigDecimal("play_ticket_price"));
                    play.setPlay_status(rst.getInt("play_status"));

                    playList.add(play);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return playList;
        }
    }
    }


