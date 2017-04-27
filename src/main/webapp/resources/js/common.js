/**
 * Created by bgfang on 2017/4/15.
 */

function openWindow(title, url, width, height) {
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';
    // var hidden = $("#editdlg").parent().is(":hidden");


    var boarddiv = '<div id="msgwindow" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条
    $(document.body).append(boarddiv);
    var win = $('#msgwindow').dialog({
        content: content,
        width: width,
        height: height,
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

/**
 * 格式化日期
 *
 * @param val
 * @param row
 * @returns {String}
 */
function formatterdate(val, row) {
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

/**
 * 格式化数字
 *
 * @param val
 * @param row
 * @returns {Number}
 */
function formatternumber(val, row) {
    if (val == null) {
        return '0.00';
    }
    return parseFloat(val).toFixed(2);
}

/**
 * 格式化长字段显示
 *
 * @param val
 * @param rec
 * @returns {String}
 */
function formatterTitle(val, rec) {
    if (rec == undefined) {
        return "";
    }

    if (typeof (val) == "string") {
        var title = val;
        if (val.length > 20) {
            title = title.substring(0, 18) + "...";
        }
        val = "<span title='" + val + "'>" + title
            + "</span>";
    }
    return val;
}

function registerEnterSearch() {
    document.onkeydown = function (e) {
        var e = e || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            select();
        }
    };
}