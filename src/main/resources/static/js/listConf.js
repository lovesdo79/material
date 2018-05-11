function getOrderConf() {
    var conf = {};
    conf.cols = [[
        {type: 'checkbox', fixed: 'left'},
        {
            field: 'name', width: 100, title: '客户姓名', templet: function (d) {
                return d.customer.name;
            }
        },
        {
            field: 'tel', width: 150, title: '电话', templet: function (d) {
                return d.customer.tel;
            }, sort: true
        },
        {
            field: 'address', width: 200, title: '地址', templet: function (d) {
                return d.customer.address;
            }
        },
        {field: 'productNames', width: 250, title: '产品', minWidth: 150},
        {field: 'totalPrice', width: 120, title: '订单总金额', sort: true},
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
        {fixed: 'right', width: 178, align: 'center', toolbar: '#barDemo'}
    ]];
    conf.sortObj = {field: "updateTime", type: "desc"};
    return conf;
}

function getCustomCols() {
    var cols = [[
        {field: 'id', width: 80, title: 'ID', sort: true},
        {field: 'username', width: 80, title: '客户姓名'},
        {field: 'sex', width: 80, title: '性别', sort: true},
        {field: 'city', width: 80, title: '城市'},
        {field: 'sign', title: '签名', minWidth: 150},
        {field: 'experience', width: 80, title: '积分', sort: true},
        {field: 'score', width: 80, title: '评分', sort: true},
        {field: 'classify', width: 80, title: '职业'},
        {field: 'wealth', width: 135, title: '财富', sort: true}
    ]];
    return cols;
}

