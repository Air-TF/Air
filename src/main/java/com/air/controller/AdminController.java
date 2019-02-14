package com.air.controller;

import com.air.bean.*;
import com.air.common.utils.CommonsUtils;
import com.air.common.utils.Page;
import com.air.service.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

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
                if (cookie.getName().equals("userName")) {
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
     * @param request
     * @return
     */
    @RequestMapping(value = "choose", method = RequestMethod.GET)
    @ResponseBody
    public ResultData choose(HttpServletRequest request) {
        if (request.getParameter("menuName") != null) {
            Menu menu = Menu.getMenuByName(request.getParameter("menuName").trim());
            if (menu != null) {
                request.getSession().setAttribute("menu", menu);
            }
        }
        List dataList = null;
        switch ((Menu) request.getSession().getAttribute("menu")) {
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
            case USER:

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
     * @return
     */
    @RequestMapping(value = "itemList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
                               HttpServletRequest request) {
        Map<String, Object> dataList = new HashMap<>();
        Menu menu = (Menu) request.getSession().getAttribute("menu");
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
                dataList.put("menuType", menu.getTypeName()); //当前数据类型
                dataList.put("itemName", itemName);
                dataList.put("category", category);
                dataList.put("subcategory", subcategory);
                dataList.put("itemPage", itemPage);
                break;
            case PARAM:
                break;
            case USER:
                String userName = request.getParameter("userName");
                Page<UserLogin> userPage = userService.selectUserList(page, rows, userName);
                dataList.put("itemPage", userPage);
                dataList.put("menuType", menu.getTypeName());
                break;
        }
        return new ResultData().success(dataList);
    }

    /**
     * 插入
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public ResultData insert(HttpServletRequest request) throws IOException {
        Menu menu = (Menu) request.getSession().getAttribute("menu");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        Boolean success = false;
        switch (menu) {
            case USER:
                UserLogin userLogin = CommonsUtils.parse(UserLogin.class, json);
                success = userService.insertUserLogin(userLogin);
                break;
        }
        if (success) {
            return new ResultData().success();
        } else {
            return new ResultData().failure();
        }
    }

    /**
     * 查找
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "select", method = RequestMethod.GET)
    @ResponseBody
    public ResultData select(HttpServletRequest request) {
        Menu menu = (Menu) request.getSession().getAttribute("menu");
        String id = request.getParameter("itemId");
        Map<String, Object> data = new HashMap<>();
        switch (menu) {
            case ITEM:
                Item item = itemService.selectDetailedItemById(Long.valueOf(id));
                Category category = categoryService.selectCategoryByItemId(item.getId());
                Subcategory subcategory = subcategoryService.selectSubcategoryByItemId(item.getId());
                data.put("category", category);
                data.put("subcategory", subcategory);
                data.put("item", item);
                break;
            case USER:
                UserLogin userLogin = userService.selectUserLoginById(id);
                data.put("item", userLogin);
                break;
        }
        return new ResultData().success(data);
    }


    /**
     * 更新
     *
     * @param request
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public ResultData update(HttpServletRequest request) throws IOException, SQLException {
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        Menu menu = (Menu) request.getSession().getAttribute("menu");
        Boolean success = false;
        switch (menu) {
            case CATE:
                break;
            case SUBCATE:
                break;
            case ITEM:
                Item item = CommonsUtils.parse(Item.class, json);
                success = itemService.updateItemById(item);
                break;
            case PARAM:
                break;
            case USER:
                UserLogin userLogin = CommonsUtils.parse(UserLogin.class, json);
                try {
                    success = userService.updateUser(userLogin);
                } catch (Exception e) {
                    throw new MySQLIntegrityConstraintViolationException("该号码已被注册");
                }
                break;
        }
        if (success) {
            return new ResultData().success();
        } else {
            return new ResultData().failure();
        }
    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultData delete(HttpServletRequest request) {
        Menu menu = (Menu) request.getSession().getAttribute("menu");
        String id = request.getParameter("id");
        Boolean success = false;
        switch (menu) {
            case CATE:
                break;
            case SUBCATE:
                break;
            case ITEM:
                success = itemService.deleteItemById(Long.valueOf(id));
                break;
            case PARAM:
                break;
            case USER:
                success = userService.deleteUserLoginById(id);
                break;
        }
        if (success) {
            return new ResultData().success();
        } else {
            return new ResultData().failure();
        }
    }

}
