/**
 * Created by bgfang on 2017/4/26.
 */
var width = "400px";
var height = "185px";
$(function () {
    loadGrid();

    $("#btnSelect").click(function () {
        select();
    });

    enterSearch();
});


function loadGrid() {
    $("#gridList").datagrid({
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        url: "../user/list",
        sortOrder: "desc",
        sortName: "updateTime",
        remoteSort: true,
        rownumbers: true,
        fit: true,
        idField: "userId",
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        singleSelect: true,
        checkOnSelect: true,
        showFooter: true,
        pageList: [20, 25, 30, 35],
        columns: [[{
            field: "userFullName",
            title: "客户姓名",
            width: 100,
            halign: "center",
            align: "left"
        }, {
            field: "userName",
            title: "登录名",
            width: 150,
            halign: "center",
            align: "left"
        }, {
            field: "opt",
            title: "操作",
            width: 115,
            halign: "center",
            align: "left",
            formatter: function (value, rec) {
                if (rec.userId === undefined) {
                    return ""
                }
                var btn = "<a class='editcls' onclick=\"resetPassword('" + rec.userId + "',false)\" href='javascript:void(0)'>重置密码</a>";
                btn += "<a class='editcls' onclick=\"del('" + rec.userId + "')\" href='javascript:void(0)'>删除</a>";
                return btn
            }
        }
        ]],
        toolbar: "#searchDiv",
        onHeaderContextMenu: function (e, field) {
            e.preventDefault();
            if (!$("#tmenu").length) {
                createColumnMenu()
            }
            $("#tmenu").menu("show", {left: e.pageX, top: e.pageY})
        },
        onLoadError: function (data) {
            $.messager.alert("警告", "网络连接异常", "error")
        }
    })
}

/**
 * 回车键搜索
 */
function enterSearch() {
    registerEnterSearch();

    $("#name").textbox('textbox').bind(
        'keyup',
        function (e) {
            $("#name").textbox('setValue',
                $(this).val().replace(/\D^./g, ''));
        });
}

function select() {
    // 校验是否存在不通过的验证
    var validate = $(".validatebox-invalid");
    if (validate.length > 0) {
        return false;
    }

    var param = $("#gridList").datagrid("options").queryParams;
    param.name = $("#name").textbox('getValue');

    $("#gridList").datagrid('options').queryParams = param;
    $("#gridList").datagrid("reload");
}

function resetPassword(id) {
    $.messager.confirm("提示", "此操作不可逆转，您确认重置此用户密码吗？", function (r) {
        if (r) {
            $.ajax({
                type: "POST",
                url: "./reset/" + id,
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                success: function (data) {
                    var code = data.rtnCode;
                    if ("00" === code) {
                        $.messager.alert("成功", data.rtnMsg, 'info')
                    } else {
                        $.messager.alert("错误", data.rtnMsg, 'error')
                    }

                    loadGrid();
                }
            });
        }
    });
}

function add() {
    var title = "新增用户";
    var url = "./show";
    openWindow(title, url, width, height);
}

function del(id) {
    $.messager.confirm("提示", "此操作不可逆转，您确认要删除这个用户吗？", function (r) {
        if (r) {
            $.ajax({
                type: "POST",
                url: "./del/" + id,
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                success: function (data) {
                    var code = data.rtnCode;
                    if ("00" === code) {
                        $.messager.alert("成功", data.rtnMsg, 'info')
                    } else {
                        $.messager.alert("错误", data.rtnMsg, 'error')
                    }

                    loadGrid();
                }
            });
        }
    });
}
