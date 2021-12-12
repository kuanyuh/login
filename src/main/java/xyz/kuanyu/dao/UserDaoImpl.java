package xyz.kuanyu.dao;

import xyz.kuanyu.domain.User;
import xyz.kuanyu.util.JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl implements UserDao {

    static String url;
    static String userName;
    static String password;

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("D:\\Program Files\\IntelliJ IDEA 2021.2\\JavaCode\\Web\\login\\target\\classes\\jdbc.properties"));
            url = prop.getProperty("jdbc.url");
            userName = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public int insertUser(User user) {
        String sql = "insert into user(id, email, password) value(?,?,?)";
        int num = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,user.getId());
            pst.setString(2,user.getEmail());
            pst.setString(3,user.getPassword());
            num = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return num;
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set name = ?, email = ?, age = ?, phoneNumber = ?, password = ? where id = ?";
        int num = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setString(1,user.getName());
            pst.setString(2,user.getEmail());
            pst.setInt(3,user.getAge());
            pst.setString(4,user.getPhoneNumber());
            pst.setString(5,user.getPassword());
            pst.setInt(6,user.getId());
            num = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return num;
    }

    @Override
    public int deleteUserById(int id) {
        String sql = "delete from user where id = ?";
        int num = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            num = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return num;
    }

    @Override
    public int existUserById(int id) {
        String sql = "select 1 from user where id=? limit 1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return 0;
    }

    @Override
    public int existUserByEmail(String email) {String sql = "select 1 from user where email=? limit 1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return 0;
    }

    @Override
    public User selectUserById(int id) {
        String sql = "select id, name, email, age, phoneNumber, password from user where id = ?";
        int num = 0;
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAge(rs.getInt(4));
                user.setPhoneNumber(rs.getString(5));
                user.setPassword(rs.getString(6));
            }else {
                System.out.println("用户不存在");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return user;
    }

    @Override
    public User selectUserByPhone(String phoneNumber) {
        String sql = "select id, name, email, age, phoneNumber, password from user where phoneNumber = ?";
        int num = 0;
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setString(1,phoneNumber);
            rs = pst.executeQuery();
            rs.next();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setAge(rs.getInt(4));
            user.setPhoneNumber(rs.getString(5));
            user.setPassword(rs.getString(6));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return user;
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql = "select id, name, email, age, phoneNumber, password from user where email = ?";
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            rs = pst.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setAge(rs.getInt(4));
                user.setPhoneNumber(rs.getString(5));
                user.setPassword(rs.getString(6));
            }else {
                System.out.println("用户不存在");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        String sql = "select id, name, email, age, phoneNumber, password from user";

        List users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.serviceRecycle(conn,pst,rs);
        }

        return users;
    }
}
