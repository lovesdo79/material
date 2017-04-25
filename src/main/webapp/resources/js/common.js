/**
 * Created by bgfang on 2017/4/15.
 */


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