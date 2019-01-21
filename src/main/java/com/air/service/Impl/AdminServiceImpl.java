package com.air.service.Impl;

import com.air.bean.UserLogin;
import com.air.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {


    @Override
    public UserLogin login(UserLogin userLogin) {
        if (userLogin.getPhone().equals("123") && userLogin.getPassword().equals("123")) {
            userLogin.setName("Admin");
            return userLogin;
        } else {
            return null;
        }
    }

    @Override
    public UserLogin getAdminByName(String value) {
        UserLogin user = new UserLogin();
        user.setName(value);
        user.setPassword("123");
        return user;
    }
}
