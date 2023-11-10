package xupt.se.ttms.Client.service;

import java.util.HashMap;

/**
 * @author 杜浩杰
 * @version 1.0
 * 该类管理 连接到服务器端的线程
 */


public class ManageClientConnectServerThread {

    //把多个线程放入到一个HashMap 集合 key 就是 用户id value 就是线程
    private static HashMap<String,ClientConnectServletThread> hm = new HashMap<>();

    //将某个线程加入到集合
    public static void addClientConnectServerThread(String emp_id, ClientConnectServletThread clientConnectServletThread){
        hm.put(emp_id,clientConnectServletThread);
    }
    //通过userId 可以得到对应的线程

    public static ClientConnectServletThread getClientConnectServerThread(String emp_id){
        return  hm.get(emp_id);
    }

}
