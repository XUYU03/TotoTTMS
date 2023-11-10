package xupt.se.ttms.Server.server;

import xupt.se.ttms.Server.common.Massage;
import xupt.se.ttms.Server.common.MassageType;
import xupt.se.ttms.model.Employee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 杜浩杰
 * @version 1.0
 *
 * 服务端 监听 9999 ,等待连接
 */
public class UserServer {
    private ServerSocket ss =null;

    public  UserServer(){
        System.out.println("服务器在9999端口监听");
        try {
            ss = new ServerSocket(9999);

            while (true){ //当和某个客户端连接后 继续监听
                Socket socket = ss.accept();
                //得到socket 关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


                //得到socket 对象 关联的对象 输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Employee emp =(Employee) ois.readObject();
                //在 DB 中验证 用户名和密码是否正确
                /**
                 * 创建一个Massage 对象 准备回复客户端
                 * 验证
                 */
                Massage massage = new Massage();
                if("成功" != null){
                    massage.setMesType(MassageType.MESSAGE_LOGIN_SUCCEED);
                    //将message 对象 回复给客户端
                    oos.writeObject(massage);
                    //创建一个线程 和客户端保持通讯
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
