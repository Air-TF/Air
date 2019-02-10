package com.air.controller;

import com.air.bean.*;
import com.air.common.utils.Page;
import com.air.service.AdminService;
import com.air.service.CategoryService;
import com.air.service.ItemService;
import com.air.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    SubcategoryService subcategoryService;
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
    public ResultData choose(HttpServletRequest request, HttpSession httpSession) {
        if (httpSession.getAttribute("menu") == null) {
            Menu menu = Menu.getMenuByName(request.getParameter("menuName").trim());
            if (menu != null) {
                httpSession.setAttribute("menu", menu);
            }
        }

        List dataList = null;
        switch ((Menu) httpSession.getAttribute("menu")) {
            case CATE:
                break;
            case SUBCATE:
                break;
            case ITEM:
                Integer categoryId = -1;
                String s = request.getParameter("categoryId");
                if (s != null && s != "") {
                    categoryId = Integer.valueOf(s);
                }
                if (categoryId < 0) {
                    dataList = categoryService.selectCategoryList();
                } else {
                    dataList = subcategoryService.selectSubcategoryListByCategoryId(categoryId);
                }
                break;
            case PARAM:
                break;
        }
        return new ResultData().success(dataList);
    }

    /**
     * 分页数据列表
     *
     * @param page
     * @param rows
     * @param request
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "itemList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                               HttpServletRequest request, HttpSession httpSession) {
        Map<String, Object> dataList = new HashMap<>();
        Menu menu = (Menu) httpSession.getAttribute("menu");
        switch (menu) {
            case CATE:
                break;
            case SUBCATE:
                break;
            case ITEM:
                String itemName = request.getParameter("itemName");
                Integer category = Integer.valueOf(request.getParameter("category"));
                Integer subcategory = Integer.valueOf(request.getParameter("subcategory"));
                Page<Item> itemPage = itemService.selectItemList(page, rows, itemName, subcategory);
                dataList.put("menuType", menu.getTypeName());
                dataList.put("itemName", itemName);
                dataList.put("category", category);
                dataList.put("subcategory", subcategory);
                dataList.put("itemPage", itemPage);
                break;
            case PARAM:
                break;
        }
        return new ResultData().success(dataList);
    }

    /**
     * 编辑对象回显
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @ResponseBody
    public ResultData edit(HttpServletRequest request) {
        Long itemId = Long.valueOf(request.getParameter("itemId"));
        Map<String, Object> data = new HashMap<>();
        Item item = itemService.selectDetailedItemById(itemId);
        Category category = categoryService.selectCategoryByItemId(item.getId());
        Subcategory subcategory = subcategoryService.selectSubcategoryByItemId(item.getId());
        data.put("category", category);
        data.put("subcategory", subcategory);
        data.put("item", item);
        return new ResultData().success(data);
    }

    /**
     * 更新数据
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public ResultData update(@RequestBody Item item) {
        if (itemService.updateItemById(item)) {
            return new ResultData().success();
        } else {
            return new ResultData().failure();
        }
    }

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultData delete(Long id) {
        if (itemService.deleteItemById(id)) {
            return new ResultData().success();
        } else {
            return new ResultData().failure();
        }
    }

    @RequestMapping(value = "getItem", method = RequestMethod.GET)
    @ResponseBody
    public ResultData getItem(Long id) {
        return new ResultData().success(itemService.selectDetailedItemById(id));
    }

}
