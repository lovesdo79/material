var $, table, sortObj, whereObj;
layui.config({
    base: '/js/layui/lay.modules/'
}).use(['table'], function () {
    $ = layui.jquery;
    table = layui.table;

    loadList();


    //监听查询按钮
    $("#customerSearch").on('click', function () {
        search();
    });

    //监听新增按钮
    $("#customerAdd").on('click', function () {
        var option = {
            url: "/customer/edit/null",
            area: ["800px", "600px"],
            offset: ["10px", "50px"],
            title: "新增客户",
            data: {
                islook: false
            }
        };
        openWindow(option);
    });
});

/**
 * 表单查询
 */
function search() {
    whereObj.name = $("#name").val();
    whereObj.tel = $("#tel").val();
    whereObj.address = $("#address").val();

    reloadList();
}


/**
 * 列表加载
 */
function loadList() {
    var tabId = "customerListTable";

    sortObj = {field: "updateTime", type: "desc"};
    whereObj = {
        sort: sortObj.field,
        order: sortObj.type
    };
    var option = {
        elem: "#" + tabId,
        method: "POST",
        url: "custList",
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
            {field: 'name', width: 120, title: '客户姓名', sort: true},
            {field: 'tel', width: 150, title: '电话'},
            {field: 'customerType', width: 80, title: '类别'},
            {field: 'address', title: '地址', minWidth: 200},
            {field: 'remark', width: 200, title: '备注'},
            {
                field: 'updateTime', width: 162, title: '更新时间', templet:
                    function (d) {
                        return formatterdate(d.updateTime);
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

    table.on('sort(' + tabId + ')', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        whereObj.sort = obj.field;
        whereObj.order = obj.type;
        sortObj = obj;

        reloadList();
    });

    /**
     * 右侧工具按钮检测
     */
    table.on("tool(" + tabId + ")", function (obj) {
        var data = obj.data;
        var option = {
            url: "/customer/edit/" + data.customerId,
            area: ["800px", "600px"],
            offset: ["10px", "50px"]
        };
        if (obj.event === 'detail') {
            option.title = "客户详情";
            option.data = {islook: "true"};
            openWindow(option);
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({//异步请求返回给后台
                    url: '/customer/del/' + data.customerId,
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
            option.title = "客户详情";
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