<%--
  Created by IntelliJ IDEA.
  User: bgfang
  Date: 2017/4/26
  Time: 上午10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${root}/WEB-INF/common/header.jsp"></jsp:include>
    <title>用户管理</title>
    <script type="text/javascript" src="${root}/resources/js/user/list.js"></script>
</head>
<body class="easyui-layout hidden_y" scroll="no">

<div id="searchDiv">
    <table id="searchTable">
        <tr>
            <td class="rightAlign"><label style='margin-left: 5px'>姓名：</label></td>
            <td><input type="text" id="name" class="easyui-textbox" style="width: 160px;"/></td>
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
