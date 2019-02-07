package com.air.controller;


import com.air.bean.ResultData;
import com.air.bean.UserLogin;
import com.air.common.utils.CommonsUtils;
import com.air.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger log = Logger.getLogger(UserController.class);

    /**
     * 登录验证
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "signIn", method = RequestMethod.GET)
    public ResultData signIn(String phone, String password) {
        UserLogin userlogin = new UserLogin();
        userlogin.setPhone(phone);
        userlogin.setPassword(password);
        Boolean login = userService.login(userlogin);
        if (login != null) {
            if (login) {
                return new ResultData().success();
            } else {
                return new ResultData().failure("密码错误");
            }
        } else {
            return new ResultData().failure("用户名不存在");
        }
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ResultData signUp(String phone, String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(CommonsUtils.getUUID());
        userLogin.setPhone(phone);
        userLogin.setPassword(password);
        try {
            if (userService.InsertUserLogin(userLogin)) {
                return new ResultData().success();
            }else{
                return new ResultData().failure("注册失败");
            }
        }catch (Exception e){
            return new ResultData().failure("用户已存在");
        }
    }
}
