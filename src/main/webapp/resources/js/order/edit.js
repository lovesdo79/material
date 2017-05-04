/**
 * Created by bgfang on 2017/4/27.
 */
var productTypes = [{type: 1, name: "窗帘"}, {type: 2, name: "橱柜"}, {type: 3, name: "配件"}];
var customerTypes = [{type: 1, name: "客户"}, {type: 2, name: "批发商"}, {type: 3, name: "供应商"}];
var valuationMethods = [{type: 1, name: "数量"}, {type: 2, name: "面积"}, {type: 3, name: "长度"}];

$(function () {
    $("#customerName").combobox({
        url: "../../customer/listAll",
        valueField: "customerId",
        textField: "name",
        panelHeight: 'auto',
        // mode: "local",
        editable: true,
        onSelect: function (rec) {
            $("#name").textbox('setValue', rec.name);
            $("#customerId").val(rec.customerId);
            $("#tel").textbox('setValue', rec.tel);
            $("#address").textbox('setValue', rec.address);
            $("#type").combobox('setValue', rec.type);
        },
        filter: function (q, row) {
            var opts = $(this).combobox('options');
            return row[opts.textField].indexOf(q) == 0;
        },
        onLoadSuccess: function () {
            var customerId = $("#customerId").val();
            $("#customerName").combobox('setValue', customerId);
        }
    });


    var coustomerType = $("#customerType").val();
    $("#type").combobox({
        valueField: 'type',
        textField: 'name',
        panelHeight: 'auto',
        data: customerTypes
    }).combobox('setValue', coustomerType);

    $("#name").textbox("textbox").bind("keyup", function (e) {
        $("#name").textbox("setValue", $(this).val().replace(/\D^./g, ""))
        $("#customerName").combobox('clear');
        $("#type").combobox('clear');
        $("#tel").textbox('setValue', '');
        $("#address").textbox('setValue', '');
        $("#customerId").val();
    });

    loadGrid();

    $("#btnSumbit").click(function () {
        save();
    });
});

function loadGrid() {
    $("#gridList").datagrid({
        url: "../../product/list",
        fit: true,
        columns: [[ //显示的列
            {
                field: 'productId',
                width: 20,
                // hidden: true,
                checkbox: true,
                formatter: function (value, row) {
                    if (true == row.checked) {
                        return "on";
                    }
                }
            }, {
                field: 'productType',
                title: '产品类型',
                width: 50,
                halign: "center",
                align: "left",
                formatter: function (value, row) {
                    for (var i = 0; i < productTypes.length; i++) {
                        if (productTypes[i].type == value) {
                            return productTypes[i].name;
                        }
                    }
                },
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'type',
                        textField: 'name',
                        panelHeight: 'auto',
                        data: productTypes
                    }
                }
            }, {
                field: 'productName',
                title: '产品名称',
                width: 100,
                halign: "center",
                align: "left",
                editor: {
                    type: 'textbox',
                    options: {}
                }
            }, {
                field: 'property',
                title: '属性',
                width: 100,
                halign: "center",
                align: "left",
                editor: {
                    type: 'textbox',
                    options: {
                        multiline: true,
                        width: '200px',
                        height: "44px"
                    }
                }
            }, {
                field: 'unitPrice',
                title: '单价',
                width: 100,
                halign: "center",
                align: "right",
                formatter: formatternumber,
                editor: {
                    type: 'numberbox',
                    options: {}
                }
            }, {
                field: 'valuation_method',
                title: '计价方式',
                width: 50,
                formatter: function (value, row) {
                    for (var i = 0; i < valuationMethods.length; i++) {
                        if (valuationMethods[i].type == value) {
                            return valuationMethods[i].name;
                        }
                    }
                },
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'type',
                        textField: 'name',
                        panelHeight: 'auto',
                        data: valuationMethods
                    }
                }
            }, {
                field: 'quantity',
                title: '数量',
                width: 50,
                halign: "center",
                align: "right",
                editor: {
                    type: 'numberbox',
                    options: {}
                }
            }, {
                field: 'remark',
                title: '备注',
                width: 150,
                halign: "center",
                align: "left",
                editor: {
                    type: 'textbox',
                    options: {}
                }
            }]],
        toolbar: "#searchDiv",
        fit: true,
        fitColumns: true,
        loadMsg: '加载中...', //加载提示
        rownumbers: true, //显示行号列
        singleSelect: false,//是允许选择一行
        checkOnSelect: true,
        selctOnCheck: true,
        onClickCell: onClickCell,
        onDblClickRow: onDblClickRow,
        queryParams: {   //在请求数据是发送的额外参数，如果没有则不用写
        },
        onLoadSuccess: function (data) {
        },
        rowStyler: function (index, row) {
        }
    });
}

//可编辑行
var editIndex = undefined;
function endEditing() {
    if (editIndex == undefined) {
        return true
    }
    if ($('#gridList').datagrid('validateRow', editIndex)) {
        $('#gridList').datagrid('endEdit', editIndex);
        $('#gridList').datagrid('unselectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
//修改的方式是直接点击单元格，所以table要加上onClickCell属性，然后重写onClickCell方法
function onClickCell() {
    if ($('#gridList').datagrid('validateRow', editIndex)) {
        $('#gridList').datagrid('endEdit', editIndex);
        editIndex = undefined;
    }
}

function onDblClickRow(index, field) {
    if (editIndex == index) {
        return;
    }
    if (endEditing()) {
        $('#gridList').datagrid('selectRow', index).datagrid('beginEdit', index);
        editIndex = index;
    } else {
        $('#gridList').datagrid('selectRow', editIndex);
    }
}
// 添加
function addRemark() {
    if (endEditing()) {
        $('#gridList').datagrid('appendRow', {checked: false});
        editIndex = $('#gridList').datagrid('getRows').length - 1;
        $('#gridList').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
}
// 删除
function removeit() {
    var rows = $('#gridList').datagrid('getSelections');
    if (rows.length <= 0) {
        $.messager.alert("错误", '请选择需要删除的行', 'error');
        return;
    }
    $.messager.confirm('Confirm', '确认删除?', function (r) {
        if (r) {
            for (var i = 0; i < rows.length; i++) {
                var rowIndex = $('#gridList').datagrid('getRowIndex', rows[i]);
                //alert(rindex);
                $('#gridList').datagrid('deleteRow', rowIndex);
            }
        }
    });

}
// 保存方法（添加修改用了一个方法accept()）
function save() {
    if (!endEditing()) {
        return;
    }
    var rows = $('#gridList').datagrid('getRows');

    for (var i = 0; i < rows.length; i++) {

    }

    var formData = $("#orderForm").serializeObject();
    // formData = Object.assert(formData, rows)

    // formData = $.extend(formData, rows);

    var data = $("#orderForm").serialize();
    if (rows.length > 0) {
        data += "&products=" + JSON.stringify(rows);
    }
    $.ajax({
        url: "../save",
        type: "post",
        data: data,
        // dataType: "json",
        // contentType: "application/json; charset=utf-8",
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            if ("00" === data.rtnCode) {
                // self.close();

                $.messager.confirm("提示", '订单已提交，关闭当前窗口吗？',
                    function (r) {
                        if (r) {
                            // opener.location.href=opener.location.href;opener=null;window.close();
                            // window.location.href =
                            // "./showList.do";
                            self.close();
                            window.opener.$("#gridList").datagrid(
                                'reload');
                        } else {
                            id = result.msg;
                            $("#id").val(id);
                        }
                    });
            } else {
                $.messager.alert("错误", data.rtnMsg, 'error');
            }
        },
        error: function () {
            $.messager.show({
                title: '提示',
                msg: '系统错误，请联系管理员------'
            });
        }
    });
}
function saveConten() {
    var mbid = $("#mbid").val();
    var content = $("#content").val();
    var xx = $('#tb input[name="x"]:checked').val();
    $.ajax({
        url: "./save",
        type: "post",
        dataType: "json",
        data: {
            mbid: mbid,
            content: content,
            xx: xx
        },
        success: function (data) {
            var treeObj = $.fn.zTree.getZTreeObj("z_tree"); //参数为树的id
            var nodes = treeObj.getSelectedNodes();
            //判断选中的是否为子节点
            //若为父节点flag = true
            if (nodes.length > 0) {
                var flag = nodes[0].isParent;
                if (flag == true) {
                    $.messager.show({
                        title: '提示',
                        msg: '父节点不能保存 ------'
                    });
                } else {
                    $.messager.show({
                        title: '提示',
                        msg: '操作成功'
                    });
                }
            }


        },
        error: function () {
            $.messager.show({
                title: '提示',
                msg: '系统错误，请联系管理员------'
            });
        }
    });
}
