package xupt.se.ttms.model;

public class Data_Dict {
    /**
     * CREATE TABLE `data_dict`  (
     *   `dict_id` int(11) NOT NULL AUTO_INCREMENT,
     *   `super_dict_id` int(11) DEFAULT NULL COMMENT '父id',
     *   `dict_index` int(11) DEFAULT NULL COMMENT '同级顺序',
     *   `dict_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
     *   `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
     *   PRIMARY KEY (`dict_id`) USING BTREE,
     *   INDEX `FK_super_child_dict`(`super_dict_id`) USING BTREE,
     *   CONSTRAINT `FK_super_child_dict` FOREIGN KEY (`super_dict_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
     * ) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;
     */

    private Integer dict_id;
    private Integer super_dict_id;
    private Integer dict_index;
    private String dict_name;
    private String dict_value;


    public Data_Dict() {
    }

    public Data_Dict(Integer dict_id, Integer super_dict_id, Integer dict_index, String dict_name, String dict_value) {
        this.dict_id = dict_id;
        this.super_dict_id = super_dict_id;
        this.dict_index = dict_index;
        this.dict_name = dict_name;
        this.dict_value = dict_value;
    }

    /**
     * 获取
     * @return dict_id
     */
    public Integer getDict_id() {
        return dict_id;
    }

    /**
     * 设置
     * @param dict_id
     */
    public void setDict_id(Integer dict_id) {
        this.dict_id = dict_id;
    }

    /**
     * 获取
     * @return super_dict_id
     */
    public Integer getSuper_dict_id() {
        return super_dict_id;
    }

    /**
     * 设置
     * @param super_dict_id
     */
    public void setSuper_dict_id(Integer super_dict_id) {
        this.super_dict_id = super_dict_id;
    }

    /**
     * 获取
     * @return dict_index
     */
    public Integer getDict_index() {
        return dict_index;
    }

    /**
     * 设置
     * @param dict_index
     */
    public void setDict_index(Integer dict_index) {
        this.dict_index = dict_index;
    }

    /**
     * 获取
     * @return dict_name
     */
    public String getDict_name() {
        return dict_name;
    }

    /**
     * 设置
     * @param dict_name
     */
    public void setDict_name(String dict_name) {
        this.dict_name = dict_name;
    }

    /**
     * 获取
     * @return dict_value
     */
    public String getDict_value() {
        return dict_value;
    }

    /**
     * 设置
     * @param dict_value
     */
    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    public String toString() {
        return "data_dict{dict_id = " + dict_id + ", super_dict_id = " + super_dict_id + ", dict_index = " + dict_index + ", dict_name = " + dict_name + ", dict_value = " + dict_value + "}";
    }
}
