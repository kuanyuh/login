package xyz.kuanyu.dao;

import xyz.kuanyu.domain.User;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    int updateUser(User user);
    int deleteUserById(int id);

    int existUserById(int id);
    int existUserByEmail(String email);
    User selectUserById(int id);
    User selectUserByPhone(String phoneNumber);
    User selectUserByEmail(String email);
    List<User> selectAllUser();
}
