<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/27
  Time: 下午2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${root}/WEB-INF/common/header.jsp"></jsp:include>
    <%--<jsp:include page="${root}/WEB-INF/common/bootstrap.jsp"></jsp:include>--%>
    <title>Title</title>

    <link type="text/css" rel="stylesheet" href="${root}/resources/css/customer.css">
    <link type="text/css" rel="stylesheet" href="${root}/resources/css/order.css">

    <script type="text/javascript" src="${root}/resources/js/order/edit.js"></script>
</head>
<body>
<form id="orderForm">
    <!-- 客户信息 -->
    <fieldset style="width: 800px; margin: 0 auto;border-radius: 8px; padding: 10px 5px;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
           id='btnSumbit' href="javascript:void(0)">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-undo'"
           id='btnClose' href="javascript:void(0)">关闭</a>

        <input type="hidden" id="orderId" name="orderId" value="${order.orderId}">
        <input type="hidden" id="isLook" name="isLook" value="${isLook}">
    </fieldset>

    <!-- 客户信息 -->
    <fieldset style="width: 800px; margin: 0 auto;border-radius: 8px; padding: 10px 5px;">
        <legend>客户信息</legend>
        <table id="customerTab" cellpadding="5" border="0" style="width: auto;margin:0 auto;">
            <tr>
                <td class="td_left">客户姓名：</td>
                <td colspan="3">
                    <input type="hidden" id="customerId" name="customerId" value="${customer.customerId}"/>
                    <input type="text" id="name" name="name" class="easyui-textbox info_text"
                           placeholder="请输入或从右侧选择" value="${customer.name}"/><input
                        type="text" id="customerName" name="customerName" class="easyui-combobox"/>
                </td>
            </tr>
            <tr>
                <td class="td_left">客户类型：</td>
                <td><input type="text" id="type" name="type" class="easyui-combobox info_text"/>
                    <input type="hidden" id="customerType" name="customerType" value="${customer.type}">
                </td>
                <td class="td_left">电话号码：</td>
                <td><input type="text" id="tel" name="tel" value="${customer.tel}" class="easyui-textbox info_text"/>
                </td>
            </tr>
            <tr>
                <td class="td_left">地址：</td>
                <td colspan="3"><input type="text" id="address" name="address" value="${customer.address}"
                                       class="easyui-textbox address_text"/>
                </td>
            </tr>
            <tr>
                <td class="td_left">测量日期：</td>
                <td><input type="text" id="surveyDate" name="surveyDate" value="${order.surveyDate}"
                           class="easyui-datebox info_text"/></td>
                <td class="td_left">安装日期：</td>
                <td><input type="text" id="installationDate" name="installationDate" value="${order.installationDate}"
                           class="easyui-datebox info_text"/></td>
            </tr>
        </table>
    </fieldset>
    <fieldset style="width: 800px; height: 400px; margin: 0 auto;border-radius: 8px; padding: 10px 5px 20px 5px;">
        <legend>商品信息</legend>
        <div id="searchDiv">
            <%--<a id="btnSave" class="easyui-linkbutton" onclick="save()" data-options="plain:true,iconCls:'icon-save'">保存</a>--%>
            <a id="btnAdd" class="easyui-linkbutton" onclick="addRemark()"
               data-options="plain:true,iconCls:'icon-edit-add'">添加</a>
            <a id="btnDel" class="easyui-linkbutton" onclick="removeit()"
               data-options="plain:true,iconCls:'icon-edit-remove'">删除</a>
            <%--<table class="table table-hover table-bordered">--%>
            <%--<thead>--%>
            <%--<tr>--%>
            <%--<td>产品名称</td>--%>
            <%--<td>产品类型</td>--%>
            <%--<td>产品属性</td>--%>
            <%--<td>产品单价</td>--%>
            <%--<td>产品数量</td>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<tr>--%>
            <%--<td><input type="text" class="text-span"/></td>--%>
            <%--<td><input type="text" class="text-span"/></td>--%>
            <%--<td><input type="text" class="text-span"/></td>--%>
            <%--<td><input type="text" class="text-span"/></td>--%>
            <%--<td><input type="text" class="text-span"/></td>--%>
            <%--</tr>--%>
            <%--</tbody>--%>
            <%--</table>--%>
        </div>
        <div id="gridList"></div>
    </fieldset>
</form>
</body>
</html>
