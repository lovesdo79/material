var $, table, element, layer, laytpl;
var navList = [{
    id: 11,
    name: "订单管理"
}, {
    id: 12,
    name: "客户管理"
}, {
    id: 13,
    name: "用户管理"
}, {
    id: 21,
    name: "供货商管理"
}, {
    id: 22,
    name: "布料管理"
}, {
    id: 23,
    name: "配件管理"
}];
var token = {
    token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjpcIjFcIixcInVzZXJOYW1lXCI6XCJiZ2ZhbmdcIixcInVzZXJGdWxsTmFtZVwiOlwi5pa556eJ57qyXCIsXCJwYXNzd2RcIjpcIjEyMzQ1NlwiLFwiaXNEZWxldGVcIjpmYWxzZSxcImNyZWF0ZVRpbWVcIjpudWxsLFwidXBkYXRlVGltZVwiOjE0OTM5OTg5NDQwMDB9IiwiZXhwIjoxNTI1OTIyMDczfQ.IBi_4cTHUm8gOT_E9YRo7ZIPLjip2jwL8XRCRy5MRlI"
};
layui.config({
    base: '/js/layui/lay.modules/'
}).use(['element', 'form', 'table', 'laytpl'], function () {
    $ = layui.jquery;
    element = layui.element;
    table = layui.table;
    layer = layui.layer;
    laytpl = layui.laytpl;

    $('.site-nav-active').on('click', function () {
        var othis = $(this), id = othis.data('id');
        // active[type] ? active[type].call(this, othis) : '';


        var li = $("li[lay-id=" + id + "]").length;
        // console.log(li);
        console.log();
        if (active['tabExist'].call(this, id)) {
            //tab已经存在直接切换tab
            active['tabChange'].call(this, id);
        } else {
            //创建tab
            var data = {};
            navList.forEach(function (value) {
                if (value.id === id) {
                    data = value;
                }
            });
            active['tabAdd'].call(this, data);
            active['tabChange'].call(this, id);
        }
    });

    element.on('tab(main-container)', function (elem) {
        var id = $(this).attr('lay-id');
        location.hash = 'tabId=' + id;

        $("dd").removeClass("layui-this");
        var li = $("a[data-id=" + id + "]");
        li.parent().addClass("layui-this");
    });

    $("#orderSearch").on('click', function () {
        layer.msg("查询");
    });

    loadList(11);
});


//触发事件
var active = {
    tabAdd: function (data) {
        // console.log(data);
        //新增一个Tab项
        var content = createTabContent(data.id);
        element.tabAdd('main-container', {
            title: data.name,
            content: content,
            id: data.id
        });
        loadList(data.id);
    },
    tabChange: function (id) {
        //切换到指定Tab项
        element.tabChange('main-container', id); //切换到：用户管理
    },
    tabExist: function (id) {
        return element.tabExist('main-container', id);
    }
};

function createTabContent(id) {
    var tabConf = getTabConf(id);

    return "<div id='" + tabConf.viewId + "'></div>";
}

function loadList(id) {
    var tabConf = getTabConf(id);
    var tabId = tabConf.id;

    var view = document.getElementById(tabConf.viewId);
    var tpl = tabConf.tpl.innerHTML;
    console.log(tpl);
    laytpl(tpl).render({}, function (html) {
        view.innerHTML = html;
    });
    var sortObj = tabConf.conf.sortObj;
    var whereObj = {
        token: token.token,
        sort: sortObj.field,
        order: sortObj.type
    };
    var tableIns = table.render({
            elem: "#" + tabId,
            method: "POST",
            url: tabConf.url,
            height: "full-235",
            where: whereObj,
            request: {pageName: "page", limitName: "pageSize"},
            response: {
                statusName: 'rtnCode',
                statusCode: "000",
                countName: 'total',
                dataName: 'rows'
            },
            initSort: sortObj,
            cols: tabConf.conf.cols,
            text: {none: "暂无数据"},
            limit: 5,
            limits: [5, 10, 20],
            page: true
        }
    );
    table.on('sort(' + tabId + ')', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.field); //当前排序的字段名
        console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
        console.log(this); //当前排序的 th 对象
        sortObj = obj;
        whereObj = {
            token: token.token,
            sort: sortObj.field,
            order: sortObj.type
        };
        //尽管我们的 table 自带排序功能，但并没有请求服务端。
        //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
        tableIns.reload({
            initSort: obj,//记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            where: whereObj
        });
    });
}

function formatterdate(val) {
    if (val != null) {
        var date = new Date(val);
        var years = date.getFullYear();
        var months = date.getMonth() + 1;
        var dates = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        return years + '-' + (months < 10 ? ('0' + months) : months) + '-' + (dates < 10 ? ('0' + dates) : dates)
            + " " + (hours < 10 ? ('0' + hours) : hours) + ":" + (minutes < 10 ? ('0' + minutes) : minutes)
            + ":" + (seconds < 10 ? ('0' + seconds) : seconds);
    }
}

function getTabConf(id) {
    var tab = {};

    if (id === 11) {
        tab = {
            id: "orderListTable",
            viewId: "orderListTableView",
            tpl: orderListTpl,
            conf: getOrderConf(),
            url: "./order/orderList"
        }
    } else if (id === 12) {
        tab = {
            id: "customerListTable",
            viewId: "customerListTableView",
            tpl: customerListTpl,
            conf: getCustomCols(),
            url: "./order/list/wechat"
        }
    } else if (id === 13) {
        tab = {
            id: "customerListTable",
            viewId: "customerListTableView",
            tpl: customerListTpl,
            conf: getCustomCols(),
            url: "./order/list/wechat"
        }
    }

    return tab;
}



