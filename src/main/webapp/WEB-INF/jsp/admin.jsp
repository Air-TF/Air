<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Air-后台管理</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="css/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/boot-crm.css" rel="stylesheet" type="text/css">


    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Vue JavaScript -->
    <script src="js/vue.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/admin.js"></script>

</head>
<body>
<c:choose>
    <c:when test="${cookie.get('userName') == null}">
        <div class="container-login">
            <form class="form-login" method="post">
                <h2 class="form-login-heading">Please sign in</h2>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="text" id="inputEmail" name="phone" class="form-control" placeholder="Phone number"
                       value="123" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                       value="123" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="remember" value="remember-me"> Remember me
                    </label>
                </div>
                <input class="btn btn-lg btn-primary btn-block" id="submit-login" type="button" value="Sign in"/>
            </form>
        </div>

        <script type="text/javascript">
            $("#submit-login").click(function () {
                $.post("admin/login",
                    $(".form-login").serialize(),
                    function () {
                        window.location.reload()
                    }
                )
            })
        </script>
    </c:when>
    <c:otherwise>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-static-top" role="navigation"
                 style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/admin">后台管理系统 v1.0</a>
                </div>

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li><a href="javascript:void(0);" aria-expanded="false"><i
                                    class="fa fa-list-alt fa-fw"></i>目录管理</a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="javascript:void(0);" aria-expanded="false"><i
                                                class="fa fa-dashboard fa-fw"></i>分区管理</a>
                                        <ul class="nav nav-third-level">
                                            <li>
                                                <a href="javascript:void(0);"><i class="fa fa-dashboard fa-fw"></i>新增分区</a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0);" class="menu-item" id="menu">
                                                    <i class="fa fa-dashboard fa-fw"></i>修改分区
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><i class="fa fa-dashboard fa-fw"></i>类别管理</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="menu-item" id="attr" aria-expanded="false">
                                    <i class="fa fa-dashboard fa-fw"></i>属性管理</a>
                                <ul class="nav nav-second-level"></ul>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="menu-item" id="item" aria-expanded="false">
                                    <i class="fa fa-dashboard fa-fw"></i>产品管理</a>
                                <ul class="nav nav-second-level">
                                    <li><a href="javascript:void(0);" data-toggle="modal" data-target="#edit_dialog">
                                        <i class="fa fa-dashboard fa-fw"></i>新增产品</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="menu-item" id="user" aria-expanded="false">
                                    <i class="fa fa-dashboard fa-fw"></i>用户管理</a>
                                <ul class="nav nav-second-level">
                                    <li><a href="javascript:void(0);" data-toggle="modal"
                                           data-target="#edit_dialog">
                                        <i class="fa fa-dashboard fa-fw"></i>新增用户</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"></h1>
                    </div>
                </div>
                <div class="detail-menu">
                        <%--content list--%>
                    <div data-type="menu">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-inline">
                                    <div class="form-group">
                                        <label for="customerFrom">客户来源</label>
                                        <select class="form-control" id="customerFrom" placeholder="客户来源"
                                                name="custSource">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="custIndustry">所属行业</label>
                                        <select class="form-control" id="custIndustry" name="custIndustry">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="custLevel">客户级别</label>
                                        <select class="form-control" id="custLevel" name="custLevel">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">查询</button>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">客户信息列表</div>
                                    <table class="table table-bordered table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>客户名称</th>
                                            <th>客户来源</th>
                                            <th>客户所属行业</th>
                                            <th>客户级别</th>
                                            <th>固定电话</th>
                                            <th>手机</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <!-- /.panel-body -->
                                </div>
                                <!-- /.panel -->
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                    </div>
                        <%--attribute list--%>
                    <div data-type="attr">
                        <div>属性</div>
                    </div>
                        <%--item list--%>
                    <div data-type="item">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-inline" id="form_item" data-type="item">
                                    <div class="form-group">
                                        <label for="itemName">名称</label>
                                        <input type="text" class="form-control" name="itemName" id="itemName">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerFrom">分区</label>
                                        <select class="form-control" placeholder="产品分区" name="category">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="customerFrom">类别</label>
                                        <select class="form-control" placeholder="产品类别" name="subcategory">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <input type="submit" class="btn btn-primary submit" value="查询"/>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" v-show="total > 0 && menu_type === currentMenuType">
                                <div class="panel panel-default table-responsive">
                                    <div class="panel-heading">产品信息列表</div>
                                    <table class="table table-bordered table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>卖点</th>
                                            <th>价格</th>
                                            <th>品牌</th>
                                            <th>别名</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <template v-for="item in items">
                                            <tr>
                                                <td>{{ item.id }}</td>
                                                <td>{{ item.name }}</td>
                                                <td>{{ item.title }}</td>
                                                <td>{{ item.price }}</td>
                                                <td>{{ item.brand }}</td>
                                                <td>{{ item.alias }}</td>
                                                <td>
                                                    <a href="javascript:void(0);" class="btn btn-primary btn-xs"
                                                       data-toggle="modal" data-target="#edit_dialog"
                                                       @click="selectItem(item.id)">修改</a>
                                                    <a href="javascript:void(0);" class="btn btn-danger btn-xs"
                                                       @click="deleteItem(item.id)">删除</a>
                                                </td>
                                            </tr>
                                        </template>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-lg-12" v-show="total== 0">
                                数据为空
                            </div>
                        </div>
                    </div>
                        <%-- user list --%>
                    <div data-type="user">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-inline" id="form_user" data-type="user">
                                    <div class="form-group">
                                        <label for="userName">用户名</label>
                                        <input type="text" class="form-control" name="userName" id="userName">
                                    </div>
                                    <input type="submit" class="btn btn-primary submit" value="查询"/>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" v-show="total > 0 && menu_type === currentMenuType">
                                <div class="panel panel-default table-responsive">
                                    <div class="panel-heading">用户信息列表</div>
                                    <table class="table table-bordered table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>昵称</th>
                                            <th>密码</th>
                                            <th>电话</th>
                                            <th>邮箱</th>
                                            <th>创建时间</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <template v-for="item in items">
                                            <tr>
                                                <td>{{ item.id }}</td>
                                                <td>{{ item.name }}</td>
                                                <td>{{ item.password }}</td>
                                                <td>{{ item.phone }}</td>
                                                <td>{{ item.email }}</td>
                                                <td>{{ item.created }}</td>
                                                <td>{{ item.status }}</td>
                                                <td>
                                                    <a href="javascript:void(0);" class="btn btn-primary btn-xs"
                                                       data-toggle="modal" data-target="#edit_dialog"
                                                       @click="selectItem(item.id)">修改</a>
                                                    <a href="javascript:void(0);" class="btn btn-danger btn-xs"
                                                       @click="deleteItem(item.id)">删除</a>
                                                </td>
                                            </tr>
                                        </template>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-lg-12" v-show="total== 0">
                                数据为空
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" v-show="total > 0 && menu_type === currentMenuType">
                    <div class="col-md-12 text-right">
                        <nav>
                            <ul class="pagination"></ul>
                        </nav>
                    </div>
                </div>
            </div>

        </div>

        <!-- 编辑对话框 -->
        <div class="modal fade" id="edit_dialog" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content" v-show="item_body.currentMenuType === 'item'">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">产品信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="edit_item_form">
                            <input type="hidden" id="edit_itemId" name="id"/>
                            <div class="form-group">
                                <label for="edit_itemName" class="col-sm-3 control-label">产品名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="edit_itemName" placeholder="产品名称"
                                           name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemCategory" class="col-sm-3 control-label">产品分区</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="edit_itemCategory" placeholder="产品分区"
                                            name="category">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemSubcategory" class="col-sm-3 control-label">产品类别</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="edit_itemSubcategory" name="subcategoryId">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemTitle" class="col-sm-3 control-label">卖点</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="edit_itemTitle" placeholder="卖点"
                                           name="title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemImage" class="col-sm-3 control-label">图片</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="edit_itemImage" placeholder="图片"
                                           name="image">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemPrice" class="col-sm-3 control-label">价格</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" id="edit_itemPrice" placeholder="价格"
                                           name="price">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemBrand" class="col-sm-3 control-label">品牌</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="edit_itemBrand" placeholder="品牌"
                                           name="brand">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemAlias" class="col-sm-3 control-label">别名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="edit_itemAlias" placeholder="别名"
                                           name="alias">
                                </div>
                            </div>
                            <template v-for="(Category,i) in CategoryList">
                                <input type="hidden" :name="'paramCategoryList['+i+'].id'" :value="Category.id"/>
                                <div class="form-group">
                                    <label class="col-sm-12 label-info text-center"
                                           :name="'paramCategoryList['+i+'].name'">{{ Category.name }}</label>
                                </div>
                                <template v-for="(detail,j) in Category.paramDescList">
                                    <input type="hidden"
                                           :name="'paramCategoryList[' + i + '].paramDescList[' + j + '].id'"
                                           :value="detail.id"/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"
                                               :name="'paramCategoryList[' + i + '].paramDescList[' + j + '].name'">{{
                                            detail.name }}</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" :placeholder="detail.name"
                                                   v-model="detail.describe"
                                                   :name="'paramCategoryList[' + i + '].paramDescList[' + j + '].describe'">
                                        </div>
                                    </div>
                                </template>
                            </template>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="isNew?insertItem():updateItem()">保存
                        </button>
                    </div>
                </div>
                <div class="modal-content" v-show="item_body.currentMenuType === 'user'">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">用户信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="edit_user_form">
                            <input type="hidden" id="edit_userId" name="id"/>
                            <div class="form-group">
                                <label for="edit_userName" class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_userName" placeholder="姓名"
                                           name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_userPassword" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_userPassword" placeholder="密码"
                                           name="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_userPhone" class="col-sm-2 control-label">电话</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="edit_userPhone" placeholder="电话"
                                           name="phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_userEmail" class="col-sm-2 control-label">邮箱</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" id="edit_userEmail" placeholder="邮箱"
                                           name="email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_userStatus" class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="edit_userStatus" placeholder="状态"
                                           name="status">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="isNew?insertItem():updateItem()">保存
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $.fn.serializeObject = function () {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function () {
                    var name = this.name.split(/\[\d+\]\./)
                    var num = this.name.match(/\d+/g)
                    switch (name.length) {
                        case 3:
                            if (o[name[0]] == undefined) add(o, name[0], [{}])
                            if (o[name[0]][num[0]] == undefined && num[0] > 0) add(o, name[0], {})
                            if (o[name[0]][num[0]][name[1]] == undefined) add(o[name[0]][num[0]], name[1], [{}])
                            if (o[name[0]][num[0]][name[1]][num[1]] == undefined && num[1] > 0) add(o[name[0]][num[0]], name[1], {})
                            add(o[name[0]][num[0]][name[1]][num[1]], name[2], this.value);
                            break;
                        case 2:
                            if (o[name[0]] == undefined) add(o, name[0], [{}])
                            if ((o[name[0]][num[0]] == undefined && num[0] > 0)) add(o, name[0], {})
                            add(o[name[0]][num[0]], name[1], this.value)
                            break;
                        case 1:
                            add(o, name[0], this.value)
                    }
                });

                function add(obj, name, value) {
                    if (obj[name] !== undefined) {
                        if (!obj[name].push) {
                            obj[name] = [obj[name]];
                        }
                        obj[name].push(value || '');
                    } else {
                        obj[name] = value || '';
                    }
                }

                return o;
            };

            var item_body = new Vue({
                el: "#page-wrapper",
                data: {
                    row: 0,
                    menu_type: "",
                    currentMenuType: "",
                    total: -1,
                    items: []
                }
            })

            var item_dialog = new Vue({
                el: "#edit_dialog",
                data: {
                    CategoryList: [],
                    isNew: true
                }
            })

            $(function () {
                //产品管理 类别选择改变
                $(".detail-menu select[name='category']").change(function () {
                    $.ajax({
                        url: "category/choose",
                        type: "GET",
                        data: {"categoryId": $(this).val()},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            var mySelect = $(".detail-menu select[name='subcategory']")[0];
                            mySelect.options.length = 1;
                            $.each(data.data, function (i, d) {
                                mySelect.options.add(new Option(d.name, d.id));
                            });
                        }
                    })
                });

                //编辑窗口 类别选择器
                $("#edit_dialog select[name='category']").change(function () {
                    $.ajax({
                        url: "category/choose",
                        type: "GET",
                        data: {"categoryId": $(this).val()},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            var mySelect = $("#edit_itemSubcategory")[0];
                            mySelect.options.length = 1;
                            $.each(data.data, function (i, d) {
                                mySelect.options.add(new Option(d.name, d.id));
                            });
                        }
                    })
                });

                // 侧边菜单
                $("#side-menu .menu-item").click(function () {
                    var menuName = this.id
                    // item_body.total = -1;
                    item_body.currentMenuType = menuName;
                    $.get("category/choose", {"menuName": menuName}, function (data) {
                        if (data.meta.success) {
                            switch (menuName) {
                                case 'item':
                                    if ($(".detail-menu select[name='category']")[0].options.length < 2) {
                                        $.each($("select[name='category']"), function (i, mySelect) {
                                            mySelect.options.length = 1;
                                            $.each(data.data, function (i, d) {
                                                mySelect.options.add(new Option(d.name, d.id));
                                            });
                                        })
                                    }
                                    break;
                            }
                        } else {
                            console.log('error')
                        }

                    }, "json")

                })

                // 查询按钮表单提交
                $('.detail-menu .form-inline').submit(function () {
                    return refreshTabel($(this).attr("data-type"));
                })

                // 模态框初始化
                $('#edit_dialog').on('hide.bs.modal', function () {
                    $("#edit_itemId").val("");
                    $("#edit_itemName").val("");
                    $("#edit_itemCategory").val("");
                    $("#edit_itemSubcategory")[0].options.length = 1;
                    $("#edit_itemSubcategory").val("")
                    $("#edit_itemTitle").val("");
                    $("#edit_itemImage").val("");
                    $("#edit_itemPrice").val("");
                    $("#edit_itemBrand").val("");
                    $("#edit_itemAlias").val("");
                    item_dialog.CategoryList = [];

                    $("#edit_userId").val("")
                    $("#edit_userName").val("")
                    $("#edit_userPassword").val("")
                    $("#edit_userPhone").val("")
                    $("#edit_userEmail").val("")
                    $("#edit_userStatus").val("")
                })
            })

            //刷新表格
            function refreshTabel(type, page, rows) {
                page = page || 1
                rows = rows || 10
                var requsetParam = "&page=" + page + "&rows=" + rows;
                switch (type) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 'item':
                        requsetParam = $("#form_item").serialize() + requsetParam;
                        break;
                    case 'user':
                        requsetParam = $("#form_user").serialize() + requsetParam
                        break;
                }
                $.get("admin/itemList", requsetParam, function (data) {
                    console.log(data)
                    item_body.total = data.data["itemPage"]["total"]
                    item_body.items = data.data["itemPage"]["rows"]
                    item_body.menu_type = data.data["menuType"]
                    pageProcess(data.data)
                }, "json")
                return false
            }

            //分页器
            function pageProcess(data) {
                var NavigationTag = $(".pagination");
                NavigationTag.empty()
                var page = data["itemPage"]
                if (page["rows"].length == 0) {
                    NavigationTag.css("display", "none")
                } else {
                    NavigationTag.css("display", "")
                }
                var pageCount = Math.ceil(page.total / page.size);
                //显示“上一页”按钮
                if (page.page > 1) {
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(\'" + item_body.menu_type + "\'," + (page.page - 1) + "," + page.size +
                        ")\">上一页</a></li>"
                    );
                } else {
                    NavigationTag.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">上一页</a></li>");
                }
                //显示当前页码的前2页码和后两页码
                var number = 5
                //若1 则 1 2 3 4 5, 若2 则 1 2 3 4 5, 若3 则1 2 3 4 5,
                //若4 则 2 3 4 5 6 ,若10  则 8 9 10 11 12
                var indexPage = (page.page - 2 > 0) ? page.page - 2 : 1;
                for (var i = 1; i <= number && indexPage <= pageCount; indexPage++, i++) {
                    if (indexPage == page.page) {
                        NavigationTag.append("<li class=\"active\"><a href=\"javascript:void(0);\">" + indexPage + "</a></li>");
                        continue;
                    }
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(\'" + item_body.menu_type + "\'," + indexPage + "," + page.size + ")\">" + indexPage + "</a></li>");
                }
                //显示“下一页”按钮
                if (page.page < pageCount) {
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(\'" + item_body.menu_type + "\'," + (page.page + 1) + "," + page.size + ")\">下一页</a></li>");
                } else {
                    NavigationTag.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">下一页</a></li>");
                }
            }

            function insertItem() {
                var data_json;
                switch (item_body.currentMenuType) {
                    case 'item':
                        data_json = JSON.stringify($("#edit_user_form").serializeObject());
                        break;
                    case 'user':
                        data_json = JSON.stringify($("#edit_user_form").serializeObject());
                        break;
                }
                $.ajax({
                    type: "post",
                    url: "admin/insert",
                    data: data_json,
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        if (data.meta.success) {
                            alert("添加成功！");
                            $("#edit_dialog").modal('hide')
                            refreshTabel(item_body.currentMenuType, $(".pagination li.active a").text());
                        } else {
                            alert("添加异常！")
                            return "Error"
                        }
                    }
                });
            }

            function deleteItem(id) {
                if (confirm('确实要删除该客户吗?')) {
                    $.ajax({
                        type: "POST",
                        url: "admin/delete",
                        data: {_method: "DELETE", "id": id},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (data.meta.success) {
                                alert("客户信息更新成功！");
                                $("#edit_dialog").modal('hide')
                                refreshTabel(item_body.currentMenuType, $(".pagination li.active a").text());
                            } else {
                                alert("delete error！");
                                return "Error"
                            }
                        }
                    });
                }
            }

            function updateItem() {
                var data_json;
                switch (item_body.currentMenuType) {
                    case 'item':
                        data_json = JSON.stringify($("#edit_item_form").serializeObject())
                        break;
                    case 'user':
                        data_json = JSON.stringify($("#edit_user_form").serializeObject())
                        break
                }
                $.ajax({
                    type: "put",
                    url: "admin/update",
                    data: data_json,
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        if (data.meta.success) {
                            alert("修改成功")
                            item_dialog.isNew = true
                            $("#edit_dialog").modal('hide')
                            refreshTabel(item_body.currentMenuType, $(".pagination li.active a").text());
                        } else {
                            alert("未修改")
                            return "Error"
                        }
                    },
                    error:function (XMLHttpRequest) {
                        alert(JSON.parse(XMLHttpRequest.responseText).meta.message);
                    }
                });
            }

            function selectItem(id) {
                item_dialog.isNew = false
                $.ajax({
                    type: "get",
                    url: "admin/select",
                    data: {"itemId": id},
                    success: function (data) {
                        console.log(data)
                        item = data.data.item;
                        switch (item_body.currentMenuType) {
                            case 'item':
                                category = data.data.category
                                subcategory = data.data.subcategory
                                $("#edit_itemId").val(item.id);
                                $("#edit_itemName").val(item.name);
                                $("#edit_itemCategory").val(category.id)
                                $("#edit_itemCategory").change();
                                $("#edit_itemSubcategory").val(subcategory.id);
                                $("#edit_itemTitle").val(item.title);
                                $("#edit_itemImage").val(item.image);
                                $("#edit_itemPrice").val(item.price);
                                $("#edit_itemBrand").val(item.brand);
                                $("#edit_itemAlias").val(item.alias);
                                item_dialog.CategoryList = item.paramCategoryList;
                                break;
                            case 'user':
                                $("#edit_userId").val(item.id)
                                $("#edit_userName").val(item.name)
                                $("#edit_userPassword").val(item.password)
                                $("#edit_userPhone").val(item.phone)
                                $("#edit_userEmail").val(item.email)
                                $("#edit_userStatus").val(item.status)
                                break;
                        }
                    }
                });
            }
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>
