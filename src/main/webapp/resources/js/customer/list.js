/**
 * Created by bgfang on 2017/4/14.
 */
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
        url: '../customer/list.do',
        sortOrder: 'desc',
        sortName: "updateTime",
        remoteSort: true,
        rownumbers: true,
        fit: true,
        fitColumns: true,
        idField: 'id',
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        singleSelect: true,
        checkOnSelect: true,
        showFooter: true,
        pageList: [20, 25, 30, 35],
        columns: [[
            {
                field: 'name',
                title: '客户姓名',
                halign: 'center',
                align: 'left',
                sortable: true,
            }, {
                field: 'address',
                title: '地址',
                halign: 'center',
                align: 'left',
                sortable: true
            }, {
                field: 'tel',
                title: '电话',
                halign: 'center',
                align: 'left',
                sortable: true
            }, {
                field: 'remark',
                title: '备注',
                halign: 'center',
                align: 'left'
            }, {
                field: 'updateTime',
                title: '更新时间',
                halign: 'center',
                align: 'center',
                sortable: true,
                formatter: formatterdate
            }, {
                field: 'opt',
                title: '操作',
                halign: 'center',
                align: 'left',
                sortable: true,
                formatter: function (value, rec) {
                    if (rec.id === undefined) {
                        return "";
                    }
                    var btn = "<a class='editcls' onclick=\"edit('" + rec.id
                        + "',false)\" href=\"javascript:void(0)\"> 查看</a>"
                        + "<a class='editcls' onclick=\"edit('"
                        + rec.id
                        + "',true)\" href='javascript:void(0)'>编辑</a><a class='editcls' onclick=\"del('"
                        + rec.id
                        + "')\" href='javascript:void(0)'>删除</a>";
                    return btn;

                }
            }
        ]],
        toolbar: "#searchDiv",
        onHeaderContextMenu: function (e, field) {
            e.preventDefault();
            if (!$('#tmenu').length) {
                createColumnMenu();
            }
            $('#tmenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        // onBeforeLoad : function(node, param) {
        // if (page != 1) {
        // node.page = page;
        // }
        // if (rows != 20) {
        // node.rows = rows;
        // }
        // },
        // onLoadSuccess: totalFun,
        onLoadError: function (data) {
            $.messager.alert("警告", "网络连接异常", "error");
        }
    });
}

/**
 * 回车键搜索
 */
function enterSearch() {
    document.onkeydown = function (e) {
        var e = e || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            select();
        }
    };

    $("#name").textbox('textbox').bind(
        'keyup',
        function (e) {
            $("#name").textbox('setValue',
                $(this).val().replace(/\D^./g, ''));
        });

    $("#tel").textbox('textbox').bind(
        'keyup',
        function (e) {
            $("#tel").textbox('setValue',
                $(this).val().replace(/\D^./g, ''));
        });

    $("#address").textbox('textbox').bind(
        'keyup',
        function (e) {
            $("#address").textbox('setValue',
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
    param.tel = $("#tel").textbox('getValue');
    param.address = $("#address").textbox('getValue');

    $("#gridList").datagrid('options').queryParams = param;
    $("#gridList").datagrid("reload");
}


function add() {
    window.location.href = "./show.do"
}

function edit(id, isLook) {
    window.location.href = "./show.do?id=" + id + "&islook=" + isLook;
}

function del(id) {

}

function openWindow() {
    var hidden = $("#editdlg").parent().is(":hidden");

    if (hidden) {
        var clientHeight = document.documentElement.clientHeight;
        var clientWidth = document.documentElement.clientWidth;
        var divHeight = $("#editdlg").parent().height();
        var divWidth = $("#editdlg").parent().width();

        var top = (clientHeight - divHeight - 20) * 0.5;
        var left = (clientWidth - divWidth - 50) * 0.5;

        $('#editdlg').dialog('open').dialog('resize', {
            top: top,
            left: left
        });
        // initColumnsDiv();
    } else {
        $('#editdlg').dialog('close');
    }
}

