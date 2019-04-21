package com.air.controller;


import com.air.bean.*;
import com.air.common.utils.CommonsUtils;
import com.air.common.utils.EmailUtils;
import com.air.service.HistoryService;
import com.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HistoryService historyService;

    /**
     * 登录验证
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "signIn", method = RequestMethod.GET)
    public ResultData signIn(String account, String password) {
        UserLogin userlogin = new UserLogin();
        if (account.contains("@")) {
            userlogin.setEmail(account);
        } else {
            userlogin.setPhone(account);
        }
        userlogin.setPassword(password);
        String userId = userService.login(userlogin);
        if (userId != null) {
            if (userId.equals("")) {
                return new ResultData().failure("密码错误");
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("userId", userId);
                return new ResultData().success(map);
            }
        } else {
            return new ResultData().failure("用户名不存在或状态异常");
        }
    }

    /**
     * 用户注册
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ResultData signUp(String account, String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(CommonsUtils.getUUID());
        int verificationCode = (int) ((Math.random() * 9 + 1) * 100000);
        if (account.contains("@")) {
            userLogin.setEmail(account);
        } else {
            userLogin.setPhone(account);
        }
        userLogin.setPassword(password);
        userLogin.setStatus(verificationCode);
        try {
            if (userService.insertUserLogin(userLogin)) {
                HashMap<String, String> map = new HashMap<>();
                map.put("userId", userLogin.getId());
                if (userLogin.getEmail() != null) {
                    String context = EmailUtils.EmailContext(userLogin.getEmail(), userLogin.getId(), String.valueOf(verificationCode));
                    EmailUtils.sendEmail(userLogin.getEmail(), "账号激活", context, null);
                }
                return new ResultData().success(map);
            } else {
                return new ResultData().failure("注册失败");
            }
        } catch (Exception e) {
            return new ResultData().failure("用户已存在");
        }
    }

    @RequestMapping(value = "check/{userId}/{code}", produces = "text/html;charset=UTF-8")
    public String check(@PathVariable("userId") String userId, @PathVariable("code") int code) {
        UserLogin user = userService.selectUserLoginById(userId);
        if (user.getStatus() == (code) && (new Date()).getTime() - user.getCreated().getTime() < 24 * 60 * 60 * 1000) {
            user.setStatus(1);
            userService.updateUser(user);
            return "激活成功，请返回应用登陆";
        } else {
            return "链接失效，请重新注册";
        }
    }

    /**
     * 更新用户信息
     *
     * @param userLogin
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultData update(UserLogin userLogin) {
        userService.updateUser(userLogin);
        return new ResultData().success();
    }

    /**
     * 获取用户收藏列表
     *
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "star", method = RequestMethod.GET)
    public ResultData listStar(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String userId) {
        Page<Item> itemPage = userService.listStar(page, size, userId);
        return new ResultData().success(itemPage);
    }

    /**
     * 设置用户收藏或者喜欢
     *
     * @param history
     * @return
     */
    @RequestMapping(value = "favorite", method = RequestMethod.GET)
    public ResultData setFavorite(History history) {
        if (historyService.updateHistory(history))
            return new ResultData().success();
        else
            return new ResultData().failure();
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResultData getUser(@PathVariable("id") String id) {
        return new ResultData().success(userService.getUser(id));
    }
}
