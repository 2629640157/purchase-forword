package com.ydgk.forword.services;

import com.ydgk.forword.entity.User;

/**
 * @author 游斌
 * @create 2020-02-29  10:44
 */
public interface UserServices {
    boolean checkUser(User user);
    boolean registerUser(User user);
    boolean checkUserName(User user);

}
