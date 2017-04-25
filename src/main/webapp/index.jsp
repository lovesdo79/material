<%@ page import="com.bgfang.material.entity.domain.UserDomain" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <jsp:include page="WEB-INF/common/header.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>运营系统</title>
    <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/js/index.js"></script>

</head>
<body class="easyui-layout hidden_y" scroll="no">
<%
    UserDomain user = (UserDomain) session.getAttribute("user");
%>
<noscript>
    <div class="noscript">
        <img src="${root}/resources/navmenu/images/noscript.gif"
             alt='抱歉，请开启脚本支持！'/>
    </div>
</noscript>
<div region="north" split="true" border="false"
     class="north">
		<span style="float: right; padding-right: 20px; padding-top: 15px;" class="head">
            <span id="bgclock"></span><br/>
            <span style="position: absolute;right: 55px;">欢迎&nbsp;&nbsp;<%=user.getUserFullName()%></span>
            <a style="float: right;" href="${root}/logout" id="loginOut">注销</a>
        </span>
    <span style="padding-left: 30px; padding-top: 20px; font-size: 22px; float: left;">运 营 系 统</span>
    <ul id="css3menu"
        style="display: none; padding: 0px; margin: 0px; list-type: none; float: left; margin-left: 40px;">
        <li><a class="active" name="basic" href="javascript:;"
               title="基础数据">基础数据</a></li>
    </ul>
</div>
<div region="south" split="true" style="height: 45px;">
    <div class="footer">
        版权所有：<a href="http://www.baidu.com/" target="_blank"
                style="color: White; font-size: 12px; text-decoration: none">琪琪窗帘布艺</a>&nbsp;&nbsp;Copyright
        &copy; 2017,All rights reserved.
    </div>
</div>
<div region="west" hide="true" split="true" title="导航菜单"
     style="width: 180px;" id="west">
    <div id='wnav' class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>
</div>
<div id="mainPanle" region="center" style="background: #eee; width:1280px; overflow-y: hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">

    </div>
</div>

<div id="mm" class="easyui-menu" style="width: 150px;">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭当前</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>
</body>
</html>
