package xyz.kuanyu.util;

import xyz.kuanyu.dao.UserDao;
import xyz.kuanyu.dao.UserDaoImpl;
import xyz.kuanyu.service.UserService;
import xyz.kuanyu.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

    public static UserService serviceInstance(){
        UserDaoImpl userDao  = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        return userService;
    }

    public static void serviceRecycle(Connection conn, PreparedStatement pst, ResultSet rs){
        try {
            if (rs!=null){
                rs.close();
            }
            if (pst!=null){
                pst.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
