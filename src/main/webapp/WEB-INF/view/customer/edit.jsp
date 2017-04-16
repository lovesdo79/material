<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/16
  Time: 下午7:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${root}/WEB-INF/common/header.jsp"></jsp:include>
    <title>客户</title>
    <link type="text/css" rel="stylesheet" href="${root}/resources/css/customer.css"/>
    <script type="text/javascript" src="${root}/resources/js/customer/edit.js"></script>
</head>
<body>
<form id="customerForm" action="${root}/customer/save.do">
    <input type="hidden" id="id" name="id" value="${customer.id}">

    <table cellpadding="5" border="0" style="width: auto">
        <tr>
            <td class='rightAlign'>姓名：</td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
