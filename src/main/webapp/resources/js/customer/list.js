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
        url: '../customer/list',
        sortOrder: 'desc',
        sortName: "updateTime",
        remoteSort: true,
        rownumbers: true,
        fit: true,
        // fitColumns: true,
        idField: 'customerId',
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
                width: 100,
                halign: 'center',
                align: 'left',
                sortable: true,
            }, {
                field: 'tel',
                title: '电话',
                width: 150,
                halign: 'center',
                align: 'left',
                sortable: true
            }, {
                field: 'customerType',
                title: '类型',
                width: 50,
                halign: 'center',
                align: 'left',
                sortable: true
            }, {
                field: 'address',
                title: '地址',
                width: 200,
                halign: 'center',
                align: 'left',
                sortable: true
            }, {
                field: 'remark',
                title: '备注',
                width: 250,
                halign: 'center',
                formatter: formatterTitle,
                align: 'left'
            }, {
                field: 'updateTime',
                title: '更新时间',
                width: 145,
                halign: 'center',
                align: 'center',
                sortable: true,
                formatter: formatterdate
            }, {
                field: 'opt',
                title: '操作',
                width: 115,
                halign: 'center',
                align: 'left',
                formatter: function (value, rec) {
                    if (rec.customerId === undefined) {
                        return "";
                    }
                    var btn = "<a class='editcls' onclick=\"edit('" + rec.customerId
                        + "',true)\" href=\"javascript:void(0)\"> 查看</a>";
                    btn += "<a class='editcls' onclick=\"edit('"
                        + rec.customerId
                        + "',false)\" href='javascript:void(0)'>编辑</a>";
                    btn += "<a class='editcls' onclick=\"del('"
                        + rec.customerId
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
        }
        // ,onBeforeLoad : function(node, param) {
        // if (page != 1) {
        // node.page = page;
        // }
        // if (rows != 20) {
        // node.rows = rows;
        // }
        // },
        // onLoadSuccess: totalFun,
    });
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
    openWindow('新增', './show');
}

function edit(id, isLook) {
    var url = "./show/" + id + "?islook=" + isLook;
    var title = isLook ? "查看" : "编辑";

    openWindow(title, url);
}

function del(id) {
    $.messager.confirm("提示", "此操作不可逆转，您确认要删除此客户吗？", function (r) {
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

function openWindow(title, url) {
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';
    // var hidden = $("#editdlg").parent().is(":hidden");


    var boarddiv = '<div id="msgwindow" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条
    $(document.body).append(boarddiv);
    var win = $('#msgwindow').dialog({
        content: content,
        width: "600px",
        height: "405px",
        modal: true,
        title: title,
        onClose: function () {
            $(this).dialog('destroy');//后面可以关闭后的事件
        }
    });
    win.dialog('open');
    /* if (hidden) {
     var clientHeight = document.documentElement.clientHeight;
     var clientWidth = document.documentElement.clientWidth;
     var divHeight = $("#editdlg").parent().height();
     var divWidth = $("#editdlg").parent().width();

     var top = (clientHeight - divHeight - 20) * 0.5;
     var left = (clientWidth - divWidth - 50) * 0.5;


     $('#editdlg').dialog({

     });
     $('#editdlg').dialog('open').dialog('resize', {
     top: top,
     left: left
     });
     // initColumnsDiv();
     } else {
     $('#editdlg').dialog('close');
     }*/
}

