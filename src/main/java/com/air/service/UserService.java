package com.air.service;

import com.air.bean.UserLogin;

public interface UserService {

    Boolean login(UserLogin userlogin);

    Boolean InsertUserLogin(UserLogin userLogin);
}
