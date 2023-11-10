import org.junit.Test;
import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;

import java.math.BigDecimal;
import java.sql.SQLException;

import static java.sql.JDBCType.NULL;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class PlayDAOTest {
    private iPlayDAO playDAO = new PlayDAO();

    @Test
    public void delete_test() throws SQLException {
        System.out.println(playDAO.delete(1));
    }

    @Test
    public void insert01() throws SQLException {
        System.out.println(playDAO.insert(new Play( 0,7, 14, "我和我的家乡",
                "电影《我和我的家乡》定档2020年国庆，延续《我和我的祖国》集体创作的方式，由张艺谋担当总监制，宁浩担任总导演，张一白担任总策划，宁浩、徐峥、陈思诚、闫非&彭大魔、邓超&俞白眉分别执导五个故事。",
                "images/property/1.jpg", "1", 120, new BigDecimal(35.00), 0)));
    }

    @Test
    public void delete() throws SQLException {
        System.out.println(playDAO.delete(11));
    }

    @Test
    public void update() throws SQLException {
        System.out.println(playDAO.update(new Play( 1,7, 14, "我和我的家乡",
                "电影《我和我的家乡》定档2020年国庆，延续《我和我的祖国》集体创作的方式，由张艺谋担当总监制，宁浩担任总导演，张一白担任总策划，宁浩、徐峥、陈思诚、闫非&彭大魔、邓超&俞白眉分别执导五个故事。",
                "images/property/1.jpg", "1", 120, new BigDecimal(35.00), 0)));
    }
}
