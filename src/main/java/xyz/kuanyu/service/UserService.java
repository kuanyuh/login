package xyz.kuanyu.service;

import xyz.kuanyu.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    int modifyUser(User user);
    int removeUser(int id);
    User findUserById(int id);
    User findUserByPhone(String phoneNumber);
    User findUserByEmail(String Email);
    List<User> findAllUser();

}
