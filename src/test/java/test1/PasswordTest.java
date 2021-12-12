package test1;

import org.junit.Test;
import xyz.kuanyu.util.MD5;

public class PasswordTest {
    @Test
    public void test01(){
        String pw = MD5.getMD5String("1234");
        System.out.println(pw);
    }
}
