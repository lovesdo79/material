/**
 * Created by bgfang on 2017/4/14.
 */
var _menus = {
    "menus": [{
        "menuid": "1",
        "icon": "icon-sys",
        "menuname": "琪琪窗帘",
        "menus": [{
            "menuid": "11",
            "menuname": "订单管理",
            "icon": "icon-trade",
            "url": "/order/showList"
        }, {
            "menuid": "12",
            "menuname": "客户管理",
            "icon": "icon-customer",
            "url": "/customer/showList"
        }, {
            "menuid": "13",
            "menuname": "用户管理",
            "icon": "icon-users",
            "url": "/user/showList"
        }/*, {
         "menuid": "14",
         "menuname": "权限管理",
         "icon": "icon-set",
         "url": "#"
         }*/]
    }, {
        "menuid": "2",
        "icon": "icon-sys",
        "menuname": "物料管理",
        "menus": [{
            "menuid": "21",
            "menuname": "供货商管理",
            "icon": "icon-nav",
            "url": "demo.html"
        }, {
            "menuid": "22",
            "menuname": "布料管理",
            "icon": "icon-nav",
            "url": "demo.html"
        }, {
            "menuid": "23",
            "menuname": "配件管理",
            "icon": "icon-nav",
            "url": "demo.html"
        }]
    }]
};


$(function () {
    clockon();


    InitLeftMenu();
    tabClose();
    tabCloseEven();

    initMainTab();
});

function initMainTab() {
    var tabTitle = '订单管理';

    var url = '/order/showList';
    var icon = getIcon('1');

    addTab(tabTitle, url, icon);
    // $('.easyui-accordion li div').removeClass("selected");
    // $(this).parent().addClass("selected");
}

// 初始化左侧
function InitLeftMenu() {
    $("#wnav").accordion({
        animate: true
    });

    $.each(_menus.menus, function (i, n) {
        var menulist = '';
        menulist += '<ul>';
        $.each(n.menus, function (j, o) {
            var cls;
            if (o.menuname == '订单管理') {
                cls = "class='selected'";
            }
            menulist += '<li><div ' + cls + '><a ref="' + o.menuid + '" href="javascript:void(0)" rel="'
                + o.url + '" ><span class="icon ' + o.icon
                + '" >&nbsp;</span><span class="nav">' + o.menuname
                + '</span></a></div></li> ';
        });
        menulist += '</ul>';
        $('#wnav').accordion('add', {
            title: n.menuname,
            animate: true,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).children('.nav').text();

        var url = $(this).attr("rel");
        var menuid = $(this).attr("ref");
        var icon = getIcon(menuid);

        addTab(tabTitle, url, icon);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });

    // 选中第一个
    var panels = $('#wnav').accordion('panels');
    var t = panels[0].panel('options').title;
    $('#wnav').accordion('select', t);
}
// 获取左侧导航的图标
function getIcon(menuid) {
    var icon = 'icon ';
    $.each(_menus.menus, function (i, n) {
        $.each(n.menus, function (j, o) {
            if (o.menuid == menuid) {
                icon += o.icon;
            }
        });
    });

    return icon;
}

function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            icon: icon
        });
    } else {
        $('#tabs').tabs('select', subtitle);
        $('#mm-tabupdate').click();
    }
    tabClose();
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
        + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /* 双击关闭TAB选项卡 */
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    });
    /* 为选项卡绑定右键 */
    $(".tabs-inner").bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children(".tabs-closable").text();

        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}
// 绑定右键菜单事件
function tabCloseEven() {
    // 刷新
    $('#mm-tabupdate').click(function () {
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        $('#tabs').tabs('update', {
            tab: currTab,
            options: {
                content: createFrame(url)
            }
        });
    });
    // 关闭当前;
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    // 全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
    });
    // 关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        $('#mm-tabcloseright').click();
        $('#mm-tabcloseleft').click();
    });
    // 关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            msgShow('系统提示', '后边没有啦~~', 'error');

            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    // 关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    // 退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}

function getUrlByTile(title) {

}


// 本地时钟
function clockon() {
    var now = new Date();
    var year = now.getFullYear();
    // getFullYear getYear
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    month = month < 10 ? "0" + month : month;
    date = date < 10 ? "0" + date : date;
    hour = hour < 10 ? "0" + hour : hour;
    minute = minute < 10 ? "0" + minute : minute;
    sec = sec < 10 ? "0" + sec : sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minute
        + ":" + sec + " " + week;

    $("#bgclock").html(time);

    setTimeout("clockon()", 200);
}

