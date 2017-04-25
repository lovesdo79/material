<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/25
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="WEB-INF/common/header.jsp"></jsp:include>
    <jsp:include page="WEB-INF/common/bootstrap.jsp"></jsp:include>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="resources/css/login.css">

    <script type="text/javascript">
        $(function () {
            $("#loginForm").form({
                success: function (data) {
                    data = JSON.parse(data);
                    if (data.rtnCode == "00") {
                        window.location.href = "./index.jsp"
                    } else {
                        $("#userName").val('');
                        $("#password").val('');
                        $("#tipMsg").children().remove();
                        var ch = '<div class="alert alert-danger" id="tip" style="margin-bottom: 0px;">';
                        ch += '<strong>注：</strong>';
                        ch += data.rtnMsg + '</div>';
                        $("#tipMsg").append(ch);
                    }
                }
            });
//            $("#login").click(function () {
//                sumbitForm();
//            });
        })

        function sumbitForm() {

        }
    </script>
</head>
<body class="easyui-layout hidden_y" scroll="no">
<div class="container">
    <div class="col-md-12">
        <h1 class="margin-bottom-15">Login</h1>
        <form id="loginForm" class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30"
              role="form" action="/login" method="post">
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="control-wrapper">
                        <input type="text" class="form-control" id="userName" name="userName"
                               placeholder="请输入用户名">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="control-wrapper">
                        <input type="password" class="form-control" id="passwd" name="passwd"
                               placeholder="请输入密码">
                    </div>
                </div>
            </div>
            <%--<div class="form-group">--%>
            <%--<div class="col-md-12">--%>
            <%--<div class="checkbox control-wrapper">--%>
            <%--<label>--%>
            <%--<input type="checkbox"> Remember me--%>
            <%--</label>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="control-wrapper">
                        <button type="submit" id="login" class="btn btn-info icon-ok icon-2px">&nbsp;&nbsp;Login
                        </button>
                        <a href="#" class="text-right pull-right" id="forgotPas">Forgot password?</a>
                    </div>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-md-12" id="tipMsg">
                    <div class="alert alert-warning" id="tip" style="margin-bottom: 0px;"><strong>注：</strong>注册功能暂未开放，请知悉，谢谢。
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>


</body>
</html>
