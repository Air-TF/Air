<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="air" uri="http://air.com/common/" %>
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
                       required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                       required>
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
            <!-- Navigation -->
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
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown"><a class="dropdown-toggle"
                                            data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
                        <i class="fa fa-caret-down"></i>
                    </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户设置</a></li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>
                            <li class="divider"></li>
                            <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>
                                退出登录</a></li>
                        </ul> <!-- /.dropdown-user --></li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <input type="text" class="form-control" placeholder="查询内容...">
                                    <span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
								</button>
							</span>
                                </div> <!-- /input-group -->
                            </li>
                            <li><a class="menu-item" id="menu"><i class="fa fa-edit fa-fw"></i>目录管理</a></li>
                            <li><a class="menu-item" id="attr"><i class="fa fa-dashboard fa-fw"></i>属性管理</a></li>
                            <li><a class="menu-item" id="product"><i class="fa fa-dashboard fa-fw"></i>产品管理</a></li>
                            <li><a class="menu-item" id="user"><i class="fa fa-dashboard fa-fw"></i>用户管理</a></li>
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="detail-menu">

                        <%--content list--%>
                    <div data-type="menu">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-inline">
                                    <div class="form-group">
                                        <label for="userName">客户名称</label>
                                        <input type="text" class="form-control" id="userName" value="${userName}"
                                               name="userName">
                                    </div>
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
                                    <!-- /.panel-heading -->
                                    <table class="table table-bordered table-striped">
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
                        <%--product list--%>
                    <div data-type="product">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-inline" id="form_product">
                                    <div class="form-group">
                                        <label for="productName">名称</label>
                                        <input type="text" class="form-control" name="productName" id="productName">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerFrom">分区</label>
                                        <select class="form-control" placeholder="产品分区" name="category">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="customerFrom">类别</label>
                                        <select class="form-control" placeholder="产品类别" name="content">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </div>
                                    <input type="submit" class="btn btn-primary" id="submit-item" value="查询"/>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">产品信息列表</div>
                                    <!-- /.panel-heading -->
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>卖点</th>
                                                <%--<th>图片</th>--%>
                                            <th>价格</th>
                                            <th>品牌</th>
                                            <th>别名</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="product_body">
                                        </tbody>
                                    </table>
                                    <!-- /.panel-body -->
                                </div>
                                <!-- /.panel -->
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                    </div>
                    <div data-type="user">
                        <div>用户</div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 text-right">
                        <nav>
                            <ul class="pagination"></ul>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- 产品编辑对话框 -->
        <div class="modal fade" id="itemEditDialog" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">修改产品信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="edit_item_form">
                            <input type="hidden" id="edit_itemId" name="id"/>
                            <div class="form-group">
                                <label for="edit_itemName" class="col-sm-2 control-label">产品名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_itemName" placeholder="产品名称"
                                           name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemCategory" style="float:left;padding:7px 15px 0 27px;">产品分区</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="edit_itemCategory" placeholder="产品分区"
                                            name="category">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemContent" style="float:left;padding:7px 15px 0 27px;">产品类别</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="edit_itemContent" name="contentId">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemTitle" class="col-sm-2 control-label">卖点</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_itemTitle" placeholder="卖点"
                                           name="title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemImage" class="col-sm-2 control-label">图片</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_itemImage" placeholder="图片"
                                           name="image">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemPrice" class="col-sm-2 control-label">价格</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="edit_itemPrice" placeholder="价格"
                                           name="price">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemBrand" class="col-sm-2 control-label">品牌</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_itemBrand" placeholder="品牌"
                                           name="brand">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_itemAlias" class="col-sm-2 control-label">别名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_itemAlias" placeholder="别名"
                                           name="alias">
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="updateItem()">保存修改</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#wrapper -->
        <script type="text/javascript">
            $.fn.serializeObject = function()
            {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function() {
                    if (o[this.name] !== undefined) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
            $(function () {
                $(".detail-menu select[name='category']").change(function () {
                    $.ajax({
                        url: "admin/choose",
                        type: "GET",
                        data: {"categoryId": $(this).val()},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            var mySelect = $(".detail-menu select[name='content']")[0];
                            mySelect.options.length = 1;
                            $.each(data.data, function (i, d) {
                                mySelect.options.add(new Option(d.name, d.id));
                            });
                        }
                    })
                });

                $("#itemEditDialog select[name='category']").change(function () {
                    $.ajax({
                        url: "admin/choose",
                        type: "GET",
                        data: {"categoryId": $(this).val()},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            var mySelect = $("#edit_itemContent")[0];
                            mySelect.options.length = 1;
                            $.each(data.data, function (i, d) {
                                mySelect.options.add(new Option(d.name, d.id));
                            });
                        }
                    })
                });

                $("#side-menu .menu-item").click(function () {
                    if ($(".detail-menu select[name='category']")[0].options.length < 2) {
                        $.get("admin/choose", {"index": $(".menu-item").index(this)}, function (data) {
                            $.each($("select[name='category']"), function (i, mySelect) {
                                mySelect.options.length = 1;
                                $.each(data.data, function (i, d) {
                                    mySelect.options.add(new Option(d.name, d.id));
                                });
                            })
                        }, "json")
                    }
                })

                $('#form_product').submit(function () {
                    return refreshTabel(2);
                })
            })

            function refreshTabel(index, page, rows) {
                page = page || 1
                rows = rows || 10
                switch (index) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        $.get("admin/itemList", $("#form_product").serialize() + "&page=" + page + "&rows=" + rows, function (data) {
                            console.log(data)
                            var tableBody = $("#product_body")[0];
                            $(tableBody).empty()
                            $.each(data.data["itemPage"]["rows"], function (i, d) {
                                $(tableBody).append("<tr>" +
                                    "<td>" + d.id + "</td>" +
                                    "<td>" + d.name + "</td>" +
                                    "<td>" + d.title + "</td>" +
                                    // "<td>" + d.image + "</td>" +
                                    "<td>" + d.price + "</td>" +
                                    "<td>" + d.brand + "</td>" +
                                    "<td>" + d.alias + "</td>" +
                                    "<td>" +
                                    "<a href=\"javascript:void(0);\" class=\"btn btn-primary btn-xs\" data-toggle=\"modal\" data-target=\"#itemEditDialog\" onclick=\"editItem(" + d.id + ")\">修改</a>" +
                                    "<a href=\"javascript:void(0);\" class=\"btn btn-danger btn-xs\" onclick=\"deleteItem(" + d.id + ")\">删除</a>" +
                                    "</td>" +
                                    "</tr>")
                            })
                            pageProcess(data.data)
                        }, "json")
                        break;
                    case 3:
                        break;
                }
                return false
            }

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
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(" + data["menu_index"] + "," + (page.page - 1) + "," + page.size +
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
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(" + data["menu_index"] + "," + indexPage + "," + page.size + ")\">" + indexPage + "</a></li>");
                }
                //显示“下一页”按钮
                if (page.page < pageCount) {
                    NavigationTag.append("<li><a href=\"javascript:void(0);\" onclick=\"refreshTabel(" + data["menu_index"] + "," + (page.page + 1) + "," + page.size + ")\">下一页</a></li>");
                } else {
                    NavigationTag.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">下一页</a></li>");
                }
            }

            function editItem(id) {
                $.ajax({
                    type: "get",
                    url: "admin/edit",
                    data: {"itemId": id},
                    success: function (data) {
                        console.log(data)
                        item = data.data.item;
                        category = data.data.category
                        $("#edit_itemId").val(item.id);
                        $("#edit_itemName").val(item.name);
                        $("#edit_itemCategory").val(category.id)
                        $("#edit_itemCategory").change();
                        $("#edit_itemContent").val(item.contentId);
                        $("#edit_itemTitle").val(item.title);
                        $("#edit_itemImage").val(item.image);
                        $("#edit_itemPrice").val(item.price);
                        $("#edit_itemBrand").val(item.brand);
                        $("#edit_itemAlias").val(item.alias);
                    }
                });
            }

            function updateItem() {
                $.ajax({
                    type: "put",
                    url: "admin/update",
                    data: JSON.stringify($("#edit_item_form").serializeObject()),
                    dataType:"json",
                    contentType:"application/json",
                    success: function (data) {
                        if (data.meta.success) {
                            alert("客户信息更新成功！");
                            $("#itemEditDialog").modal('hide')
                            refreshTabel(2, $(".pagination li.active a").text());
                        } else {
                            return "Error"
                        }
                    }
                });
            }

            function deleteItem(id) {
                if (confirm('确实要删除该客户吗?')) {
                    $.ajax({
                        type:"POST",
                        url: "admin/delete",
                        data: {_method:"DELETE","id": id},
                        dataType: "json",
                        // contentType:"application/json;charset=utf-8",
                        async: false,
                        success: function (data) {
                            if (data.meta.success) {
                                alert("客户信息更新成功！");
                                $("#itemEditDialog").modal('hide')
                                refreshTabel(2, $(".pagination li.active a").text());
                            } else {
                                alert("delete error！");
                                return "Error"
                            }
                        }
                    });
                }
            }
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>
