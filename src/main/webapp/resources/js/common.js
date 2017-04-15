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