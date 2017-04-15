<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    request.setAttribute("root", request.getContextPath());
    request.setAttribute("ext", request.getContextPath() + "/ext-2.3.0");
%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<link type="text/css" rel="stylesheet" href="${root}/resources/css/common.css" />
<script type="text/javascript" src="${root}/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${root}/resources/js/common.js"></script>
<%@include file="taglib.jsp" %>
<%@include file="easyUI.jsp" %>
<%@include file="artDialog.jsp" %>

