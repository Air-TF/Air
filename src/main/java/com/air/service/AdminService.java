package com.air.service;

import com.air.bean.UserLogin;

public interface AdminService {
    UserLogin login(UserLogin userLogin);

    UserLogin getAdminByName(String value);
}
