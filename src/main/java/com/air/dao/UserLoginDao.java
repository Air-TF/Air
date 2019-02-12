package com.air.dao;

import com.air.bean.UserLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLoginDao {
    UserLogin selectUserLoginByPhone(String phone);

    UserLogin selectUserLoginByEmail(String email);

    Integer insertUserLogin(UserLogin userLogin);

    Integer updateUser(UserLogin userLogin);

    List<UserLogin> selectUserLoginList(@Param("start") Integer start, @Param("rows") Integer rows, @Param("userName") String userName);

    Integer selectUserLoginListCount(@Param("userName") String userName);
}