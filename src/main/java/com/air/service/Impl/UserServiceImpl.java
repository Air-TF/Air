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
    public UserLogin login(UserLogin userlogin) {
        if (userlogin.getPhone()!=null){
            return userLoginDao.selectUserLoginByPhone(userlogin.getPhone());
        }else if (userlogin.getEmail()!=null){
            return userLoginDao.selectUserLoginByEmail(userlogin.getEmail());
        }else{
            return null;
        }
    }
}
