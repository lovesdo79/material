<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/13
  Time: 下午4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${root}/WEB-INF/common/header.jsp"></jsp:include>
    <title>客户列表</title>
    <link type="text/css" rel="stylesheet" href="${root}/resources/css/customer/list.css"/>
    <script src="${root}/resources/js/customer/list.js" type="text/javascript"></script>
</head>
<body class="easyui-layout hidden_y" scroll="no">

<div id="searchDiv">
    <table id="searchTable">
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>姓名：</label></td>
            <td><input type="text" id="search_name" class="easyui-textbox" style="width: 160px;"/></td>
            <td class="rightAlign"><label style='margin-left: 5px'>电话：</label></td>
            <td><input type="text" id="search_tel" class="easyui-textbox" style="width: 160px;"/></td>
            <td class="rightAlign"><label style='margin-left: 5px'>地址：</label></td>
            <td><input type="text" id="search_address" class="easyui-textbox" style="width: 160px;"/></td>
            <td><a id="btnSelect" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a></td>
        </tr>
    </table>

    <a id="btnAdd" class="easyui-linkbutton" onclick="add()" data-options="plain:true,iconCls:'icon-add'">新增</a>
</div>

<div region="center" style="padding: 1px; overflow-y: hidden;">
    <div id="gridList"></div>
</div>

<!-- 表列编辑弹窗 -->
<div id="editdlg" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-save',closed:true"
     style="width: 600px; height: 405px; padding: 10px;">
    <table>
        <tr>
            <td class="rightAlign" style="width: 120px;"><label style='margin-left: 5px;'>姓名：</label></td>
            <td><input type="text" id="name" class="easyui-textbox" style="width: 160px;"/></td>
        </tr>
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>电话：</label></td>
            <td><input type="text" id="tel" class="easyui-textbox" style="width: 160px;"/></td>
        </tr>
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>地址：</label></td>
            <td><input type="text" id="address" class="easyui-textbox" style="width: 160px;"/></td>
        </tr>
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>备注：</label></td>
            <td><input type="text" id="remark" class="easyui-textbox" style="width: 160px;"/></td>
        </tr>
    </table>
</div>

</body>
</html>
