package test1;

import org.junit.Test;
import xyz.kuanyu.util.JDBCUtil;

public class JDBCTest {

    @Test
    public void test01(){
        System.out.println(JDBCUtil.serviceInstance().findUserById(1001));
    }

    @Test
    public void test02(){
        System.out.println(JDBCUtil.serviceInstance().findUserByEmail("kuanyuhuang@qq.com"));
    }
}
