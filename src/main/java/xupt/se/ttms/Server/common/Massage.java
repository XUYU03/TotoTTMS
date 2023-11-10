package xupt.se.ttms.Server.common;

import java.io.Serializable;
import java.util.TimerTask;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class Massage implements Serializable {

    private static  final long serialVersionUID = 1L ;

    private String sender ;//发送者
    private  String getter; //接收者

    private  String content ; //消息内容

    private TimerTask sendTime; //发送时间

    private  String mesType ; //消息类型

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public Massage() {
    }

    public Massage(String sender, String getter, String content, TimerTask sendTime ,String mesType) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
        this.mesType = mesType;
    }

    @Override
    public String toString() {
        return "Massage{" +
                "sender='" + sender + '\'' +
                ", getter='" + getter + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TimerTask getSendTime() {
        return sendTime;
    }

    public void setSendTime(TimerTask sendTime) {
        this.sendTime = sendTime;
    }
}
