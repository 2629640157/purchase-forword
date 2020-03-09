package com.ydgk.forword.services.impl;

import com.ydgk.forword.dao.UserDao;
import com.ydgk.forword.entity.User;
import com.ydgk.forword.services.UserServices;
import com.ydgk.forword.util.Tools;

/**
 * @author 游斌
 * @create 2020-02-29  10:46
 */
public class UserServicesImpl implements UserServices {
    UserDao userDao = new UserDao();

    @Override
    public boolean checkUser(User user) {
        user.setPassword(Tools.md5(user.getPassword()));
        return userDao.queryUser(user);
    }

    @Override
    public boolean registerUser(User user) {
      user.setPassword(Tools.md5(user.getPassword()));
        return userDao.registerUser(user);
    }

    @Override
    public boolean checkUserName(User user) {
        return userDao.checkUserName(user);
    }
}
