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
    <title>新增用户</title>
    <script type="text/javascript" src="${root}/resources/js/user/add.js"></script>
</head>
<body>
<form id="userForm" action="${root}/customer/save">
    <input type="hidden" id="customerId" name="customerId" value="${customer.customerId}">
    <input type="hidden" id="isLook" name="isLook" value="${isLook}">

    <table cellpadding="5" border="0" style="width: auto;margin:0 auto;">
        <tr>
            <td class='rightAlign'>账户：</td>
            <td><input type="text" name="userName" id="userName" class="easyui-textbox" style="width: 200px;"
                       data-options="required:true,validType:['length[1,100]','verification']"
                       value="${customer.name}"/>
                <span class="red">*</span>
            </td>
        </tr>
        <tr>
            <td class='rightAlign'>姓名：</td>
            <td><input type="text" name="userFullName" id="userFullName" class="easyui-textbox" style="width: 200px;"
                       data-options="required:true,validType:['length[1,100]','verification']"
                       value="${customer.tel}"/>
                <span class="red">*</span>
            </td>
        </tr>
        <tr align="center">
            <td colspan="6">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
                   id='btnSumbit' href="javascript:void(0)">提交</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
