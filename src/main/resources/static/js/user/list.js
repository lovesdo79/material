var $, table, sortObj, whereObj;
layui.config({
    base: '/js/layui/lay.modules/'
}).use(['table'], function () {
    $ = layui.jquery;
    table = layui.table;

    loadList();


    //监听查询按钮
    $("#orderSearch").on('click', function () {
        search();
    });

    //监听新增按钮
    $("#orderAdd").on('click', function () {
        layer.msg("新增");
    });
});

/**
 * 表单查询
 */
function search() {
    whereObj.name = $("#name").val();
    whereObj.product = $("#product").val();
    whereObj.address = $("#address").val();

    reloadList();
}

/**
 * 列表加载
 */
function loadList() {
    var tabId = "orderListTable";

    sortObj = {field: "updateTime", type: "desc"};
    whereObj = {
        sort: sortObj.field,
        order: sortObj.type
    };
    var option = {
        elem: "#" + tabId,
        method: "POST",
        url: "orderList",
        height: "full-102",
        where: whereObj,
        request: {pageName: "page", limitName: "pageSize"},
        response: {
            statusName: 'rtnCode',
            statusCode: "00",
            countName: 'total',
            dataName: 'rows'
        },
        initSort: sortObj,
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'orderNo', width: 156, title: '订单编号'},
            {field: 'name', width: 100, title: '客户姓名', sort: true},
            {field: 'tel', width: 150, title: '电话', sort: true},
            {field: 'address', width: 200, title: '地址'},
            {field: 'prodCount', width: 100, title: '商品数量', align: 'right', minWidth: 50},
            {field: 'totalPrice', width: 120, align: 'right', title: '订单总金额', sort: true},
            {
                field: 'updateTime', width: 162, title: '更新时间', templet: function (d) {
                    return formatterdate(d.updateTime);
                }, sort: true
            },
            {
                field: 'createTime', width: 162, title: '创建时间', templet: function (d) {
                    return formatterdate(d.createTime);
                }, sort: true
            },
            {fixed: 'right', width: 178, align: 'center', toolbar: '#editBar'}
        ]],
        text: {none: "暂无数据"},
        limit: 5,
        limits: [5, 10, 20],
        page: true
    };
    table.render(option);
    table.on('sort(' + tabId + ')', function (obj) {

        sortObj = obj;
        whereObj = {
            token: token.token,
            sort: sortObj.field,
            order: sortObj.type
        };

        reloadList();

    });
    table.on("tool(" + tabId + ")", function (obj) {
        var data = obj.data;
        var option = {
            url: "/order/edit/" + data.orderId,
            area: ["800px", "600px"],
            offset: ["100px", "50px"]
        };
        console.log(data);
        if (obj.event === 'detail') {
            option.title = "订单详情";
            option.data = {islook: "true"};
            openWindow(option);
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({//异步请求返回给后台
                    url: '/order/del/' + data.customerId,
                    type: 'POST',
                    data: data.field,
                    dataType: 'json',
                    success: function (data) {
                        //这里获取到数据执行显示
                        if (data.rtnCode === "00") {
                            reloadList();
                        } else {
                            layer.alert(data.rtnMsg);
                        }
                    }
                });
                // obj.del();//同步到后台
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            option.title = "订单详情";
            option.data = {islook: "false"};
            openWindow(option);
        }
    });
}

/**
 * 重新加载列表
 */
function reloadList() {
    table.reload("customerListTable", {
        page: {
            curr: 1
        },
        initSort: sortObj,
        where: whereObj
    });
}
