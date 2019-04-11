package com.air.service.Impl;

import com.air.bean.Item;
import com.air.bean.UserLogin;
import com.air.common.utils.CommonsUtils;
import com.air.bean.Page;
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
    public String login(UserLogin userlogin) {
        UserLogin user;
        if (userlogin.getPhone() != null) {
            user = userLoginDao.selectUserLoginByPhone(userlogin.getPhone());
        } else if (userlogin.getEmail() != null) {
            user = userLoginDao.selectUserLoginByEmail(userlogin.getEmail());
        } else {
            return null;
        }

        if (user != null) {
            if (user.getStatus() != 1 && (new Date()).getTime() - user.getCreated().getTime() > 60 * 1000){
                userLoginDao.deleteUserById(user.getId());
                return null;
            }
            return userlogin.getPassword().equals(user.getPassword()) ? user.getId() : "";
        } else {
            return null;
        }
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
        return userLoginDao.updateUserStatus(id, 9);
    }

    @Override
    public Page<Item> listStar(Integer page, Integer size, String userId) {
        Integer start = (page - 1) * size;
        List<Item> itemList = userLoginDao.selectUserStar(start, size, userId);
        int itemCount = userLoginDao.selectUserStarCount(userId);
        Page<Item> itemPage = new Page<>();
        itemPage.setPage(page);
        itemPage.setRows(itemList);
        itemPage.setSize(size);
        itemPage.setTotal(itemCount);
        return itemPage;
    }
}
