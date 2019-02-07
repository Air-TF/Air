package com.air.service.Impl;

import com.air.bean.UserLogin;
import com.air.dao.UserLoginDao;
import com.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserLoginDao userLoginDao;

    @Override
    public Boolean login(UserLogin userlogin) {
        UserLogin user;
        if (userlogin.getPhone() != null) {
            user = userLoginDao.selectUserLoginByPhone(userlogin.getPhone());
        } else if (userlogin.getEmail() != null) {
            user = userLoginDao.selectUserLoginByEmail(userlogin.getEmail());
        } else {
            return false;
        }

        if (userlogin.getPassword().equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean InsertUserLogin(UserLogin userLogin) {
        if (userLoginDao.InsertUserLogin(userLogin) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
