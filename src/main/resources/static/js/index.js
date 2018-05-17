var $, table, element, layer, laytpl;
var navList = [{
    id: 11,
    name: "订单管理",
    url: "order/showList"
}, {
    id: 12,
    name: "客户管理",
    url: "customer/showList"
}, {
    id: 13,
    name: "用户管理",
    url: "customer/showList"
}, {
    id: 21,
    name: "供货商管理",
    url: "customer/showList"
}, {
    id: 22,
    name: "布料管理",
    url: "customer/showList"
}, {
    id: 23,
    name: "配件管理",
    url: "customer/showList"
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
        $("a[data-id=" + id + "]").parent().addClass("layui-this");
    });


    active.tabAdd(navList[0]);
    active.tabChange(navList[0].id);
});


//触发事件
var active = {
    tabAdd: function (data) {
        //新增一个Tab项
        var content = createTabContent(data);
        element.tabAdd('main-container', {
            title: data.name,
            content: content,
            id: data.id
        });
    },
    tabChange: function (id) {
        //切换到指定Tab项
        element.tabChange('main-container', id); //切换到：用户管理

    },
    tabExist: function (id) {
        return element.tabExist('main-container', id);
    }
};

function createTabContent(nav) {

    return "<iframe src='" + nav.url + " 'frameborder='0' style='height: 100%;width: 100%;' ></iframe>";

    // return "<div id='" + tabConf.viewId + "'></div>";
}


