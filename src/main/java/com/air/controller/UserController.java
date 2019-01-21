package com.air.controller;


import com.air.bean.UserLogin;
import com.air.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public UserLogin login(String phone){
        UserLogin userlogin = new UserLogin();
        userlogin.setPhone(phone);
        UserLogin login = userService.login(userlogin);
        return login;
    }
}
