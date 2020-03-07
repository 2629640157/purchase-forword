package com.ydgk.eight.services.impl;

import com.ydgk.eight.entity.User;
import com.ydgk.eight.services.UserServices;

/**
 * @author 游斌
 * @create 2020-02-29  10:46
 */
public class UserServicesImpl implements UserServices {
    UserDao userDao = new UserDao();

    @Override
    public boolean checkUser(User user) {
        return userDao.queryUser(user);
    }
}
