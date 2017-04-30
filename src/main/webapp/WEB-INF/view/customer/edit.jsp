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
<form id="customerForm" action="${root}/customer/save">
    <input type="hidden" id="customerId" name="customerId" value="${customer.customerId}">
    <input type="hidden" id="isLook" name="isLook" value="${isLook}">

    <table cellpadding="5" border="0" style="width: auto;margin:0 auto;">
        <tr>
            <td class='td_left'>姓名：</td>
            <td><input type="text" name="name" id="name" class="easyui-textbox" style="width: 200px;"
                       data-options="required:true,validType:['length[1,100]','verification']"
                       value="${customer.name}"/>
                <span class="red">*</span>
            </td>
        </tr>
        <tr>
            <td class='td_left'>电话：</td>
            <td><input type="text" name="tel" id="tel" class="easyui-textbox" style="width: 200px;"
                       data-options="required:true,validType:['length[1,100]','verification']"
                       value="${customer.tel}"/>
                <span class="red">*</span>
            </td>
        </tr>
        <tr>
            <td class='td_left'>地址：</td>
            <td>
                <input type="text" name="address" id="address" class="easyui-textbox" style="width: 400px;"
                       data-options="required:true,validType:['length[1,100]','verification']"
                       value="${customer.address}"/>
                <span class="red">*</span>
            </td>
        </tr>

        <tr>
            <td class='td_left'>备注：</td>
            <td>
                <input type="text" name="remark" id="remark" class="easyui-textbox"
                       style="width: 400px;height: 80px; overflow: hidden;"
                       data-options="multiline:true,validType:['length[0,200]','verification']"
                       value="${customer.remark}"/>
                <%--<span class="textbox" style="width: 405px; height: 100px;">--%>
                <%--<textarea class="easyui-validatebox textbox-text" autocomplete="off" name="remark" id="remark"--%>
                <%--data-options="validType:['length[0,200]','verification']"--%>
                <%--style="margin-left: 0px; margin-right: 0px; height: 94px; width: 400px;">--%>
                <%--${customer.remark}--%>
                <%--</textarea>--%>
                <%--</span>--%>
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
