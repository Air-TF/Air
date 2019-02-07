package com.air.dao;

import com.air.bean.UserLogin;

public interface UserLoginDao {
    UserLogin selectUserLoginByPhone(String phone);

    UserLogin selectUserLoginByEmail(String email);

    Integer InsertUserLogin(UserLogin userLogin);
}