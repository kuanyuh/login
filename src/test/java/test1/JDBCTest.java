package test1;

import org.junit.Test;
import xyz.kuanyu.controller.LoginServlet;
import xyz.kuanyu.domain.User;
import xyz.kuanyu.util.JDBCUtil;

import java.io.PrintWriter;

public class JDBCTest {

    @Test
    public void test01(){
        System.out.println(JDBCUtil.serviceInstance().findUserById(1001));
    }

    @Test
    public void test02(){
        System.out.println(JDBCUtil.serviceInstance().findUserByEmail("kuanyuhuang@qq.com"));
    }

    @Test
    public void test03(){
        System.out.println(JDBCUtil.serviceInstance().existUser(1001));
    }

    @Test
    public void test04(){
        System.out.println(JDBCUtil.serviceInstance().addUser(new User()));
        System.out.println(JDBCUtil.serviceInstance());
    }

    @Test
    public void test05(){
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.checkPasswd(1001,"1001",null);
    }
}
