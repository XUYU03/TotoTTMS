package xupt.se.ttms.Client.service;

import xupt.se.ttms.Server.common.Massage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class ClientConnectServletThread extends  Thread{
    //该线程需要持有Socket
    private Socket socket;

    public ClientConnectServletThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        //因为Thread需要在后台和服务器通讯 ,因此
        while(true){

            System.out.println("客户端线程 等待从服务器读取的发送请求");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                //如果服务器没有发送 Message 对象 线程会阻塞
                Massage massage =(Massage) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
