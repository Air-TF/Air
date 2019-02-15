package com.air.service.Impl;

import com.air.bean.UserLogin;
import com.air.common.utils.CommonsUtils;
import com.air.common.utils.Page;
import com.air.dao.UserLoginDao;
import com.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

        return userlogin.getPassword().equals(user.getPassword()) ? true : false;
    }

    @Override
    public Boolean insertUserLogin(UserLogin userLogin) {
        userLogin.setId(CommonsUtils.getUUID());
        userLogin.setCreated(new Date());
        return userLoginDao.insertUserLogin(userLogin) == 1 ? true : false;
    }

    @Override
    public Boolean updateUser(UserLogin userLogin) {
        return userLoginDao.updateUser(userLogin) == 1 ? true : false;
    }

    @Override
    public Page<UserLogin> selectUserList(Integer page, Integer rows, String userName) {
        Integer start = (page - 1) * rows;
        List<UserLogin> userLoginList = userLoginDao.selectUserLoginList(start, rows, userName);
        Integer userLoginListCount = userLoginDao.selectUserLoginListCount(userName);
        Page<UserLogin> userLoginPage = new Page<>();
        userLoginPage.setRows(userLoginList);
        userLoginPage.setSize(rows);
        userLoginPage.setPage(page);
        userLoginPage.setTotal(userLoginListCount);
        return userLoginPage;
    }

    @Override
    public UserLogin selectUserLoginById(String id) {
        return userLoginDao.selectUserLoginById(id);
    }

    @Override
    public Boolean deleteUserLoginById(String id) {
        return userLoginDao.updateUserStatus(id,9);
    }
}
