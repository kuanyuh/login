package xyz.kuanyu.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {
    //发送邮件代码
    public static void sendRegisterAuthCodeEmail(String email, int authCode) {
        try {
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName("smtp.qq.com");//发送邮件的服务器
            mail.setAuthentication("kuanyuhuang@qq.com","olywjmsvulgseccd");//刚刚记录的授权码，是开启SMTP的密码
            mail.setFrom("kuanyuhuang@qq.com");  //发送邮件的邮箱和发件人
            mail.setSSLOnConnect(true); //使用安全链接
            mail.addTo(email);//接收的邮箱
            //System.out.println("email"+email);
            mail.setSubject("注册验证码");//设置邮件的主题
            mail.setMsg("尊敬的用户:你好!\n 注册验证码为:" + authCode+"\n"+"     (有效期为一分钟)");//设置邮件的内容
            mail.send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
            //"8208190608@csu.edu.cn"
    public static void sendLoginAuthCodeEmail(String email, int authCode) {
        try {
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName("smtp.qq.com");//发送邮件的服务器
            mail.setAuthentication("kuanyuhuang@qq.com","olywjmsvulgseccd");//刚刚记录的授权码，是开启SMTP的密码
            mail.setFrom("kuanyuhuang@qq.com");  //发送邮件的邮箱和发件人
            mail.setSSLOnConnect(true); //使用安全链接
            mail.addTo(email);//接收的邮箱
            //System.out.println("email"+email);
            mail.setSubject("登录验证码");//设置邮件的主题
            mail.setMsg("尊敬的用户:你好!\n 登录验证码为:" + authCode+"\n"+"     (有效期为一分钟)");//设置邮件的内容
            mail.send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
