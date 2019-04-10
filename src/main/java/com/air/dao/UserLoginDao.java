package com.air.dao;

import com.air.bean.Item;
import com.air.bean.UserLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLoginDao {
    UserLogin selectUserLoginByPhone(String phone);

    UserLogin selectUserLoginByEmail(String email);

    UserLogin selectUserLoginById(String id);

    Integer insertUserLogin(UserLogin userLogin);

    Integer updateUser(UserLogin userLogin);

    List<UserLogin> selectUserLoginList(@Param("start") Integer start, @Param("rows") Integer rows, @Param("userName") String userName);

    Integer selectUserLoginListCount(@Param("userName") String userName);

    Boolean updateUserStatus(@Param("id") String id, @Param("status") Integer status);

    List<Item> selectUserStar(@Param("start") Integer start, @Param("size") Integer size, @Param("userId") String userId);

    int selectUserStarCount(@Param("userId") String userId);
}