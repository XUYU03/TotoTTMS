package xupt.se.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 发邮件工具类
 */
public final class MailUtil {
    //创建线程对象
 //   ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    private static final String USER = "2802188750@qq.com"; // 发件人称号，同邮箱地址※
    private static final String PASSWORD = "tamarcsnwlupdgdb"; // 授权码，开启SMTP时显示※

    /**
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(HttpServletRequest request, String to, String text, String title) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        final HttpSession session = request.getSession();
        //拿到验证码 ：
        String verCode = MailUtil.CreateCode().toString();
        //发送时间
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(time);

        Map<String , String> map = new HashMap<>();
        map.put("code",verCode);
        map.put("email",to);
        //将验证码和邮箱一起放入到session中
        session.setAttribute("verCode",map);
        final Map<String,String> codeMap = (Map<String, String>) session.getAttribute("verCode");

        //创建计时线程池 到时间自动移除验证码
//        scheduledExecutorService.schedule(new Thread(()->{
//            if (to.equals(codeMap.get("email"))) {
//                session.removeAttribute("verCode");
//            }
//        }),5*60, TimeUnit.SECONDS);
//        try {
//            scheduledExecutorService.schedule(new Thread(()->{
//                if(to.equals(codeMap.get("email"))){
//                    session.removeAttribute("verCode");
//                }
//            }), 5*60, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {


            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
//            注意发送邮件的方法中，发送给谁的，发送给对应的app，※
//            要改成对应的app。扣扣的改成qq的，网易的要改成网易的。※
//            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            //发送复杂邮件
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**生成随机的六位验证码*/
    /**
     * 邮箱验证码 采用真随机数 SecureRandom
     * @return
     */
    public static StringBuilder CreateCode() {
        String dates = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();
        Random r = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int index = r.nextInt(dates.length());
            char c = dates.charAt(index);
            code.append(c);
        }
        return code;
    }


    public static void main(String[] args) throws Exception { // 做测试用
        StringBuilder  code =MailUtil.CreateCode();
        //MailUtil.sendMail( "2118456900@qq.com", "你好，这是一封测试邮件，无需回复  验证码: " + code, "测试邮件");//填写接收邮箱※
        System.out.println("发送成功");
    }
}
