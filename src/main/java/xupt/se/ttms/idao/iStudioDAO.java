/**
 * 
 */
package xupt.se.ttms.idao;

import java.sql.SQLException;
import java.util.List;

import xupt.se.ttms.model.Studio;

public interface iStudioDAO
{
    int Count();
    public List<Studio> selectsched(String name);

    List<Studio> QueryALL();

    Studio QueryByid(int id);

    List<Studio>  selectbyid(int id);

    public int insert(Studio stu) throws SQLException;

    public int update(Studio stu);

    public int delete(int ID);

    public List<Studio> select(String studioName);

    public int insert_seat(int studio_id,int row,int col ,int seat_status) throws SQLException;

    public int delect_seat(int studio_id) throws SQLException;
}
