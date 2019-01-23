package com.air.controller;

import com.air.bean.*;
import com.air.common.utils.Page;
import com.air.service.AdminService;
import com.air.service.CategoryService;
import com.air.service.ContentService;
import com.air.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminService adminService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ContentService contentService;
    @Autowired
    ItemService itemService;


    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping()
    public void show(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(new String("userName"))) {
                    request.getSession().setAttribute("user", adminService.getAdminByName(cookie.getValue()));
                }
            }
        }
    }

    /**
     * 登录功能
     *
     * @param response
     * @param request
     * @param userLogin
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(HttpServletResponse response, HttpServletRequest request, UserLogin userLogin) {
        String rememberMe = request.getParameter("remember");

        UserLogin user = adminService.login(userLogin);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            Cookie nameCookie = new Cookie("userName", user.getName());
            Cookie pwdCookie = new Cookie("password", user.getPassword());
            if (rememberMe != null) {
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                pwdCookie.setMaxAge(60 * 60 * 24 * 7);
            }
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
            return new ResultData().success();
        } else {
            return new ResultData().failure("登陆失败");
        }
    }

    /**
     * 管理模块选择
     *
     * @return
     */
    @RequestMapping(value = "choose", method = RequestMethod.GET)
    @ResponseBody
    public ResultData choose(Integer index, HttpServletRequest request, HttpSession httpSession) {
        if (httpSession.getAttribute("menu_index") == null) {
            httpSession.setAttribute("menu_index", index);
        }
        List dataList = null;
        switch ((Integer) httpSession.getAttribute("menu_index")) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                Integer categoryId = -1;
                String s = request.getParameter("categoryId");
                if (s != null && s != "") {
                    categoryId = Integer.valueOf(s);
                }
                if (categoryId < 0) {
                    dataList = categoryService.selectCategoryList();
                } else {
                    dataList = contentService.selectContentByCategoryId(categoryId);
                }
                break;
            case 3:
                break;
        }
        return new ResultData().success(dataList);
    }

    @RequestMapping(value = "itemList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                               HttpServletRequest request, HttpSession httpSession, Model model) {
        Map<String, Object> dataList = new HashMap<>();
        int menu_index;
        try {
            menu_index = (Integer) httpSession.getAttribute("menu_index");
        } catch (Exception e) {
            menu_index = 2;
        }
        switch (menu_index) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                String itemName = request.getParameter("productName");
                Integer category = Integer.valueOf(request.getParameter("category"));
                Integer content = Integer.valueOf(request.getParameter("content"));
                Page<Item> itemPage = itemService.selectItemList(page, rows, itemName, content);
                dataList.put("menu_index", menu_index);
                dataList.put("productName", itemName);
                dataList.put("category", category);
                dataList.put("content", content);
                dataList.put("itemPage", itemPage);
                break;
            case 3:
                break;
        }
        return new ResultData().success(dataList);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @ResponseBody
    public ResultData edit(HttpServletRequest request) {
        Long itemId = Long.valueOf(request.getParameter("itemId"));
        Map<String, Object> data = new HashMap<>();
        Item item = itemService.selectItemById(itemId);
        Category category = categoryService.selectCategoryByContentId(item.getContentId());
        data.put("category", category);
        data.put("item", item);
        return new ResultData().success(data);
    }
}
