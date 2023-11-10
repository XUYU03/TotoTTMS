package xupt.se.ttms.Client.service;


import xupt.se.ttms.Server.common.Massage;
import xupt.se.ttms.Server.common.MassageType;
import xupt.se.ttms.dao.employeeDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author 杜浩杰
 * @version 1.0
 */
public class UserClientService {

    private Employee emp = new Employee();
    private employeeDAO employeeDAO0 = new employeeDAO();
    private  EmployeeSrv employeeSrv = new EmployeeSrv();

    //Socket 可能在其他地方使用 因此作出属性
    private  Socket socket;

    //验证用户合法性


    //
    public boolean checkUser(String emp_name ,String pwd){
        boolean b =false;
        // 在数据库中验证用户信息

        //连接到服务器 ，发送 u 对象

        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);

            //得到ObjectOutputStream 对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送user对象
            oos.writeObject(emp);

            //读取服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Massage ms = (Massage) ois.readObject();

            if (ms.getMesType().equals(MassageType.MESSAGE_LOGIN_SUCCEED)){
                //登录成功
                b=true;
                //创建一个和服务器段通讯的线程 -> ClientConnectServletThread
                ClientConnectServletThread ccst = new ClientConnectServletThread(socket);
                //启动客户端线程
                ccst.start();
                //将线程放入到集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(emp_name,ccst);



            }else{
                //如果登录失败 ，没有创建线程 ； 不能启动和服务器启动的线程
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
