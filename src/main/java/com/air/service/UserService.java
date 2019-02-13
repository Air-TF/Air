package com.air.service;

import com.air.bean.UserLogin;
import com.air.common.utils.Page;

public interface UserService {

    Boolean login(UserLogin userlogin);

    Boolean insertUserLogin(UserLogin userLogin);

    Boolean updateUser(UserLogin userLogin);

    Page<UserLogin> selectUserList(Integer page, Integer rows, String userName);

    UserLogin selectUserLoginById(String id);

    Boolean deleteUserLoginById(String id);
}
