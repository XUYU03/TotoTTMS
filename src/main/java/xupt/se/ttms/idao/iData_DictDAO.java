package xupt.se.ttms.idao;


import xupt.se.ttms.model.Data_Dict;

import java.util.List;

public interface iData_DictDAO {
    /**
     * 根据字典 ID 查询字典
     *
     * @param dictId 字典 ID
     * @return 返回查询到的字典对象，若未查询到则返回 null
     */
    Data_Dict selectById(Integer dictId);

    /**
     * 查询所有字典
     *
     * @return 返回所有字典对象的列表
     */
    List<Data_Dict> selectAll();

    /**
     * 插入一条字典记录
     *
     * @param dataDict 待插入的字典对象
     * @return 返回受影响的行数，若插入失败则返回 0
     */
    public int insert(Data_Dict dataDict);

    /**
     * 根据字典 ID 更新对应的字典记录
     *
     * @param dataDict 待更新的字典对象
     * @return 返回受影响的行数，若更新失败则返回 0
     */
    public int update(Data_Dict dataDict);

    /**
     * 根据字典 ID 删除对应的字典记录
     *
     * @param dictId 字典 ID
     * @return 返回受影响的行数，若删除失败则返回 0
     */
     public int delete(Integer dictId);
}
