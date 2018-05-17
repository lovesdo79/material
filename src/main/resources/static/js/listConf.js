function getOrderConf() {
    return {
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
        ]], sortObj: {field: "updateTime", type: "desc"}
    };
}

function getCustomConf() {
    return {
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
        ]], sortObj: {field: "updateTime", type: "desc"}
    }
}

function getUserConf() {
    return {
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'name', width: 120, title: '姓名'},
            {field: 'tel', width: 150, title: '登录名'},
            {fixed: 'right', width: 178, align: 'center', toolbar: '#userBar'}
        ]],
        sortObj: {}
    };
}

