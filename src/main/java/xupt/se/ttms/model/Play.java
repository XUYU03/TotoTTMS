package xupt.se.ttms.model;

import java.math.BigDecimal;

public class Play {
    /**
     CREATE TABLE `play`  (
     `play_id` int(11) NOT NULL AUTO_INCREMENT,
     `dict_type_id` int(11) DEFAULT NULL,
     `dict_lang_id` int(11) DEFAULT NULL,
     `play_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
     `play_introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
     `play_image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
     `play_video` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
     `play_length` int(11) DEFAULT NULL,
     `play_ticket_price` decimal(10, 2) DEFAULT NULL,
     `play_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n            0：待安排演出\r\n            1：已安排演出\r\n            -1：下线',
     PRIMARY KEY (`play_id`) USING BTREE,
     INDEX `FK_dict_lan_play`(`dict_lang_id`) USING BTREE,
     INDEX `FK_dict_type_play`(`dict_type_id`) USING BTREE,
     CONSTRAINT `FK_dict_lan_play` FOREIGN KEY (`dict_lang_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
     CONSTRAINT `FK_dict_type_play` FOREIGN KEY (`dict_type_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
     ) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;
     */

    private Integer play_id;
    private Integer dict_type_id;
    private Integer dict_lang_id;
    private String play_name;
    private String play_introduction;
    private String play_image;
    private String play_video;
    private Integer play_length;
    private BigDecimal play_ticket_price;
    private Integer play_status;


    public Play() {
    }

    public Play(Integer play_id, Integer dict_type_id, Integer dict_lang_id, String play_name, String play_introduction, String play_image, String play_video, Integer play_length, BigDecimal play_ticket_price, Integer play_status) {
        this.play_id = play_id;
        this.dict_type_id = dict_type_id;
        this.dict_lang_id = dict_lang_id;
        this.play_name = play_name;
        this.play_introduction = play_introduction;
        this.play_image = play_image;
        this.play_video = play_video;
        this.play_length = play_length;
        this.play_ticket_price = play_ticket_price;
        this.play_status = play_status;
    }

    /**
     * 获取
     * @return play_id
     */
    public Integer getPlay_id() {
        return play_id;
    }

    /**
     * 设置
     * @param play_id
     */
    public void setPlay_id(Integer play_id) {
        this.play_id = play_id;
    }

    /**
     * 获取
     * @return dict_type_id
     */
    public Integer getDict_type_id() {
        return dict_type_id;
    }

    /**
     * 设置
     * @param dict_type_id
     */
    public void setDict_type_id(Integer dict_type_id) {
        this.dict_type_id = dict_type_id;
    }

    /**
     * 获取
     * @return dict_lang_id
     */
    public Integer getDict_lang_id() {
        return dict_lang_id;
    }

    /**
     * 设置
     * @param dict_lang_id
     */
    public void setDict_lang_id(Integer dict_lang_id) {
        this.dict_lang_id = dict_lang_id;
    }

    /**
     * 获取
     * @return play_name
     */
    public String getPlay_name() {
        return play_name;
    }

    /**
     * 设置
     * @param play_name
     */
    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    /**
     * 获取
     * @return play_introduction
     */
    public String getPlay_introduction() {
        return play_introduction;
    }

    /**
     * 设置
     * @param play_introduction
     */
    public void setPlay_introduction(String play_introduction) {
        this.play_introduction = play_introduction;
    }

    /**
     * 获取
     * @return play_image
     */
    public String getPlay_image() {
        return play_image;
    }

    /**
     * 设置
     * @param play_image
     */
    public void setPlay_image(String play_image) {
        this.play_image = play_image;
    }

    /**
     * 获取
     * @return play_video
     */
    public String getPlay_video() {
        return play_video;
    }

    /**
     * 设置
     * @param play_video
     */
    public void setPlay_video(String play_video) {
        this.play_video = play_video;
    }

    /**
     * 获取
     * @return play_length
     */
    public Integer getPlay_length() {
        return play_length;
    }

    /**
     * 设置
     * @param play_length
     */
    public void setPlay_length(Integer play_length) {
        this.play_length = play_length;
    }

    /**
     * 获取
     * @return play_ticket_price
     */
    public BigDecimal getPlay_ticket_price() {
        return play_ticket_price;
    }

    /**
     * 设置
     * @param play_ticket_price
     */
    public void setPlay_ticket_price(BigDecimal play_ticket_price) {
        this.play_ticket_price = play_ticket_price;
    }

    /**
     * 获取
     * @return play_status
     */
    public Integer getPlay_status() {
        return play_status;
    }

    /**
     * 设置
     * @param play_status
     */
    public void setPlay_status(Integer play_status) {
        this.play_status = play_status;
    }

    public String toString() {
        return "play{play_id = " + play_id + ", dict_type_id = " + dict_type_id + ", dict_lang_id = " + dict_lang_id + ", play_name = " + play_name + ", play_introduction = " + play_introduction + ", play_image = " + play_image + ", play_video = " + play_video + ", play_length = " + play_length + ", play_ticket_price = " + play_ticket_price + ", play_status = " + play_status + "}";
    }
}
