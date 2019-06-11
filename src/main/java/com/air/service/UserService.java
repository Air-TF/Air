package com.air.service;

import com.air.bean.Item;
import com.air.bean.User;
import com.air.bean.UserLogin;
import com.air.bean.Page;

public interface UserService {

    String login(UserLogin userlogin);

    Boolean insertUserLogin(UserLogin userLogin);

    Boolean updateUser(UserLogin userLogin);

    Page<UserLogin> selectUserList(Integer page, Integer rows, String userName);

    UserLogin selectUserLoginById(String id);

    Boolean deleteUserLoginById(String id);

    Page<Item> listStar(Integer page, Integer size, String userId);

    UserLogin getUserLogin(String id);

    User getUser(String id);
}
