package xyz.kuanyu.service;

import xyz.kuanyu.dao.UserDao;
import xyz.kuanyu.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public int addUser(User user) {
        int nums = userDao.insertUser(user);
        return nums;
    }

    @Override
    public int modifyUser(User user) {
        int nums = userDao.updateUser(user);
        return nums;
    }

    @Override
    public int removeUser(int id) {
        int nums = userDao.deleteUserById(id);
        return nums;
    }

    @Override
    public User findUserById(int id) {
        User user = userDao.selectUserById(id);
        return user;
    }

    @Override
    public User findUserByPhone(String phoneNumber) {
        User user = userDao.selectUserByPhone(phoneNumber);
        return user;
    }

    @Override
    public User findUserByEmail(String Email) {
        User user = userDao.selectUserByEmail(Email);
        return user;
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = userDao.selectAllUser();
        return users;
    }

}
