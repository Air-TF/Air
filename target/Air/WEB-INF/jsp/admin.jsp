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
                                <form class="form-inline" action="${pageContext.request.contextPath }/admin/itemList"
                                      method="post">
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
                                            <c:forEach items="${fromType}" var="item">
                                                <option value="${item.dict_id}"<c:if
                                                        test="${item.dict_id == custSource}"> selected</c:if>>${item.dict_item_name }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="custIndustry">所属行业</label>
                                        <select class="form-control" id="custIndustry" name="custIndustry">
                                            <option value="">--请选择--</option>
                                            <c:forEach items="${industryType}" var="item">
                                                <option value="${item.dict_id}"<c:if
                                                        test="${item.dict_id == custIndustry}"> selected</c:if>>${item.dict_item_name }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="custLevel">客户级别</label>
                                        <select class="form-control" id="custLevel" name="custLevel">
                                            <option value="">--请选择--</option>
                                            <c:forEach items="${levelType}" var="item">
                                                <option value="${item.dict_id}"<c:if
                                                        test="${item.dict_id == custLevel}"> selected</c:if>>${item.dict_item_name }</option>
                                            </c:forEach>
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
                                        <c:forEach items="${page.rows}" var="row">
                                            <tr>
                                                <td>${row.cust_id}</td>
                                                <td>${row.cust_name}</td>
                                                <td>${row.cust_source}</td>
                                                <td>${row.cust_industry}</td>
                                                <td>${row.cust_level}</td>
                                                <td>${row.cust_phone}</td>
                                                <td>${row.cust_mobile}</td>
                                                <td>
                                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                                       data-target="#customerEditDialog"
                                                       onclick="editCustomer(${row.cust_id})">修改</a>
                                                    <a href="#" class="btn btn-danger btn-xs"
                                                       onclick="deleteCustomer(${row.cust_id})">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="col-md-12 text-right">
                                        <air:page url="${pageContext.request.contextPath }/admin"/>
                                    </div>
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
                                    <div class="col-md-12 text-right">
                                        <air:page url="${pageContext.request.contextPath }/admin/itemList"/>
                                    </div>
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

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- 客户编辑对话框 -->
        <div class="modal fade" id="customerEditDialog" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">修改客户信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="edit_customer_form">
                            <input type="hidden" id="edit_cust_id" name="cust_id"/>
                            <div class="form-group">
                                <label for="edit_customerName" class="col-sm-2 control-label">客户名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_customerName" placeholder="客户名称"
                                           name="cust_name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_customerFrom" style="float:left;padding:7px 15px 0 27px;">客户来源</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="edit_customerFrom" placeholder="客户来源"
                                            name="cust_source">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${fromType}" var="item">
                                            <option value="${item.dict_id}"<c:if
                                                    test="${item.dict_id == custSource}"> selected</c:if>>${item.dict_item_name }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_custIndustry" style="float:left;padding:7px 15px 0 27px;">所属行业</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="edit_custIndustry" name="cust_industry">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${industryType}" var="item">
                                            <option value="${item.dict_id}"<c:if
                                                    test="${item.dict_id == custIndustry}"> selected</c:if>>${item.dict_item_name }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_custLevel" style="float:left;padding:7px 15px 0 27px;">客户级别</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="edit_custLevel" name="cust_level">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${levelType}" var="item">
                                            <option value="${item.dict_id}"<c:if
                                                    test="${item.dict_id == custLevel}"> selected</c:if>>${item.dict_item_name }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_linkMan" class="col-sm-2 control-label">联系人</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_linkMan" placeholder="联系人"
                                           name="cust_linkman">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_phone" class="col-sm-2 control-label">固定电话</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_phone" placeholder="固定电话"
                                           name="cust_phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_mobile" class="col-sm-2 control-label">移动电话</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_mobile" placeholder="移动电话"
                                           name="cust_mobile">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_zipcode" class="col-sm-2 control-label">邮政编码</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_zipcode" placeholder="邮政编码"
                                           name="cust_zipcode">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_address" class="col-sm-2 control-label">联系地址</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_address" placeholder="联系地址"
                                           name="cust_address">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="updateCustomer()">保存修改</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#wrapper -->
        <script type="text/javascript">
            $(function () {
                $("select[name='category']").change(function () {
                    $.get("admin/choose", {"categoryId": $("select[name='category']").val()}, function (data) {
                        var mySelect = $("select[name='content']")[0];
                        mySelect.options.length = 1;
                        $.each(data.data, function (i, d) {
                            mySelect.options.add(new Option(d.name, d.id));
                        });
                    })
                });

                $("#side-menu .menu-item").click(function () {
                    $.get("admin/choose", {"index": $(".menu-item").index(this)}, function (data) {
                        var mySelect = $("select[name='category']")[0];
                        mySelect.options.length = 1;
                        $.each(data.data, function (i, d) {
                            mySelect.options.add(new Option(d.name, d.id));
                        });
                    }, "json")
                })

                $('#form_product').submit(function () {
                    $.get("admin/itemList", $("#form_product").serialize(), function (data) {
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
                                "<a href=\"#\" class=\"btn btn-primary btn-xs\" data-toggle=\"modal\" data-target=\"#customerEditDialog\" onclick=\"editCustomer(${row.cust_id})\">修改</a>" +
                                "<a href=\"#\" class=\"btn btn-danger btn-xs\" onclick=\"deleteCustomer(${row.cust_id})\">删除</a>" +
                                "</td>" +
                                "</tr>")
                        })
                    }, "json")
                    return false
                })

                function editCustomer(id) {
                    $.ajax({
                        type: "get",
                        url: "customer/edit.action",
                        data: {"id": id},
                        success: function (data) {
                            $("#edit_cust_id").val(data.cust_id);
                            $("#edit_customerName").val(data.cust_name);
                            $("#edit_customerFrom").val(data.cust_source)
                            $("#edit_custIndustry").val(data.cust_industry)
                            $("#edit_custLevel").val(data.cust_level)
                            $("#edit_linkMan").val(data.cust_linkman);
                            $("#edit_phone").val(data.cust_phone);
                            $("#edit_mobile").val(data.cust_mobile);
                            $("#edit_zipcode").val(data.cust_zipcode);
                            $("#edit_address").val(data.cust_address);

                        }
                    });
                }

                function updateCustomer() {
                    $.post("customer/update.action", $("#edit_customer_form").serialize(), function (data) {
                        alert("客户信息更新成功！");
                        window.location.reload();
                    });
                }

                function deleteCustomer(id) {
                    if (confirm('确实要删除该客户吗?')) {
                        $.post("customer/delete.action", {"id": id}, function (data) {
                            alert("客户删除更新成功！");
                            window.location.reload();
                        });
                    }
                }
            })
        </script>
    </c:otherwise>
</c:choose>
</body>
</html>
