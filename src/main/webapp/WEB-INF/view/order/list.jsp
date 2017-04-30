<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/20
  Time: 下午7:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${root}/WEB-INF/common/header.jsp"></jsp:include>
    <title>订单列表</title>
    <link type="text/css" rel="stylesheet" href="${root}/resources/css/order.css"/>
    <script type="text/javascript" src="${root}/resources/js/jquery-easyui-1.4/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${root}/resources/js/order/list.js"></script>
</head>
<body class="easyui-layout hidden_y" scroll="no">
<div id="searchDiv">
    <table id="searchTable">
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>姓名：</label></td>
            <td><input type="text" id="name" class="easyui-textbox" style="width: 160px;"/></td>
            <td class="rightAlign"><label style='margin-left: 5px'>商品：</label></td>
            <td><input type="text" id="productName" class="easyui-textbox" style="width: 160px;"/></td>
            <td class="rightAlign"><label style='margin-left: 5px'>地址：</label></td>
            <td><input type="text" id="address" class="easyui-textbox" style="width: 160px;"/></td>
            <td><a id="btnSelect" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a></td>
        </tr>
    </table>

    <a id="btnAdd" class="easyui-linkbutton" onclick="add()" data-options="plain:true,iconCls:'icon-add'">新增</a>
</div>

<div region="center" style="padding: 1px; overflow-y: hidden;">
    <div id="gridList"></div>
</div>
</body>
</html>
