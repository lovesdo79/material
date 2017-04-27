$(function () {
    loadGrid();
    enterSearch()
});

function loadGrid() {
    $("#gridList").datagrid({
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        url: "../order/list",
        sortOrder: "desc",
        sortName: "updateTime",
        remoteSort: true,
        rownumbers: true,
        fit: true,
        idField: "orderId",
        pagination: true,
        pageNumber: 1,
        pageSize: 20,
        singleSelect: true,
        checkOnSelect: true,
        showFooter: true,
        pageList: [20, 25, 30, 35],
        columns: [[{
            field: "name",
            title: "客户姓名",
            width: 100,
            halign: "center",
            align: "left",
            formatter: function (value, row) {
                if (row.customer !== null) {
                    return row.customer.name
                }
            }
        }, {
            field: "tel",
            title: "电话",
            width: 150,
            halign: "center",
            align: "left",
            formatter: function (value, row) {
                if (row !== null) {
                    return row.customer.tel;
                }
            }
        }, {
            field: "address",
            title: "地址",
            width: 200,
            halign: "center",
            align: "left",
            formatter: function (value, row) {
                if (row !== null) {
                    return row.customer.address;
                }
            }
        }, {
            field: "productNames",
            title: "产品",
            width: 250,
            formatter: formatterTitle,
            halign: "center",
            align: "left"
        }, {
            field: "totalPrice",
            title: "订单总金额",
            width: 100,
            formatter: formatternumber,
            halign: "center",
            align: "right"
        }
            // , {
            //     field: "opt",
            //     title: "操作",
            //     width: 115,
            //     halign: "center",
            //     align: "left",
            //     formatter: function (value, rec) {
            //         if (rec.customerId === undefined) {
            //             return ""
            //         }
            //         var btn = "<a class='editcls' onclick=\"edit('" + rec.customerId + '\',true)" href="javascript:void(0)"> 查看</a>';
            //         btn += "<a class='editcls' onclick=\"edit('" + rec.customerId + "',false)\" href='javascript:void(0)'>编辑</a>";
            //         btn += "<a class='editcls' onclick=\"del('" + rec.customerId + "')\" href='javascript:void(0)'>删除</a>";
            //         return btn
            //     }
            // }
        ]],
        toolbar: "#searchDiv",
        onHeaderContextMenu: function (e, field) {
            e.preventDefault();
            if (!$("#tmenu").length) {
                createColumnMenu()
            }
            $("#tmenu").menu("show", {left: e.pageX, top: e.pageY})
        },
        view: detailview,
        detailFormatter: function (index, row) {
            if (row.orderProductVos.length == 0) {
                return
            }
            return '<div style="padding:2px"><table class="ddv"></table></div>'
        },
        onExpandRow: function (index, row) {
            var ddv = $(this).datagrid("getRowDetail", index).find("table.ddv");
            ddv.datagrid({
                fitColumns: true,
                singleSelect: true,
                rownumbers: true,
                loadMsg: "正在加载中",
                height: "auto",
                columns: [[{
                    field: "productName",
                    title: "商品名称",
                    width: 200,
                    formatter: function (value, row) {
                        if (row !== null) {
                            return row.product.productName;
                        }
                    },
                    halign: "center",
                    align: "left"
                }, {
                    field: "property",
                    title: "属性",
                    width: 200,
                    formatter: function (value, row) {
                        if (row !== null) {
                            return row.product.property;
                        }
                    },
                    halign: "center",
                    align: "left"
                }, {
                    field: "unitPrice",
                    title: "单价",
                    width: 200,
                    formatter: function (value, row) {
                        if (row !== null) {
                            return formatternumber(row.product.unitPrice, row);
                        }
                    },
                    halign: "center",
                    align: "right"
                }, {
                    field: "quantity",
                    title: "数量",
                    width: 200,
                    formatter: formatterTitle,
                    halign: "center",
                    align: "right"
                }]],
                onResize: function () {
                    $("#gridList").datagrid("fixDetailRowHeight", index)
                },
                onLoadSuccess: function () {
                    setTimeout(function () {
                        $("#gridList").datagrid("fixDetailRowHeight", index)
                    }, 0)
                }
            }).datagrid("loadData", {total: row.orderProductVos.length, rows: row.orderProductVos});
            $("#historyList").datagrid("fixDetailRowHeight", index)
        },
        onLoadError: function (data) {
            $.messager.alert("警告", "网络连接异常", "error")
        }
    })
}

function enterSearch() {
    registerEnterSearch();
    $("#name").textbox("textbox").bind("keyup", function (e) {
        $("#name").textbox("setValue", $(this).val().replace(/\D^./g, ""))
    });
    $("#productName").textbox("textbox").bind("keyup", function (e) {
        $("#productName").textbox("setValue", $(this).val().replace(/\D^./g, ""))
    });
    $("#address").textbox("textbox").bind("keyup", function (e) {
        $("#address").textbox("setValue", $(this).val().replace(/\D^./g, ""))
    })
};