package xupt.se.ttms.Server.server;

import xupt.se.ttms.Server.common.Massage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author 杜浩杰
 * @version 1.0
 * 该类对应的对象 和某个 客户端 保持 通讯
 */
public class ServerConnectClientTread  extends  Thread{
    private Socket socket ;

    //连接到服务端的用户id
    private String userId;

    @Override
    public void run() {//这里线程处于run 的状态 可以发送/接收消息
        while(true){
            System.out.println("通讯状态开启");
            try {
                ObjectInputStream ois
                        = new ObjectInputStream(socket.getInputStream());

                Massage massage =(Massage) ois.readObject();

        //        new ServerConnectClientTread(socket,em).var

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ServerConnectClientTread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }
}
