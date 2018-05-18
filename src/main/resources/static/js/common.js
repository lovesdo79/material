var $, layer;

layui.config({}).use([], function () {
    $ = layui.jquery;
    layer = layui.layer;
});


function openWindow(option) {

    var data = option.data;

    var param = "";
    var first = true;
    for (var i in data) {
        if (first) {
            param += "?";
        } else {
            param += "&";
        }
        first = false;
        param += i + "=" + data[i];
    }

    layer.open({
        type: 2,//此处以iframe举例
        title: option.title,
        shade: 0,
        maxmin: true,
        area: option.area,
        offset: option.offset,
        content: option.url + param,
        shade: [0.8, '#393D49'],
        zIndex: layer.zIndex,//重点1
        moveOut: true,
        success: function (layero) {
            layer.setTop(layero); //重点2
        }
    });
}

/**
 * Created by bgfang on 2017/4/15.
 */
/**
 * 功能：序列化form表单数据成Json对象
 * 1.name均是简单的一级对象
 * 2.同名的name属性，值会被序列化为数组，例如checkbox等控件
 */
// $.fn.serializeObject = function () {
//     var json = {};
//     var arrObj = this.serializeArray();
//     $.each(arrObj, function () {
//         if (json[this.name]) {
//             if (!json[this.name].push) {
//                 json[this.name] = [json[this.name]];
//             }
//             json[this.name].push(this.value || '');
//         } else {
//             json[this.name] = this.value || '';
//         }
//     });
//     return json;
// };
//
// /**
//  * 功能：序列化form表单元素
//  * 1.同名的name属性，值会被序列化为数组，例如checkbox等控件
//  * 2.可以嵌套对象，name和value会被序列化为嵌套的json对象格式
//  * 3.可以嵌套对象列表，name和value会被序列化成嵌套的json数组对象
//  */
// $.fn.serializeNestedObject = function () {
//     var json = {};
//     var arrObj = this.serializeArray();
//     //alert(JSON.stringify(arrObj));
//     $.each(arrObj, function () {
//         // 对重复的name属性，会将对应的众多值存储成json数组
//         if (json[this.name]) {
//             if (!json[this.name].push) {
//                 json[this.name] = [json[this.name]];
//             }
//             json[this.name].push(this.value || '');
//         } else {
//             // 有嵌套的属性，用'.'分隔的
//             if (this.name.indexOf('.') > -1) {
//                 var pos = this.name.indexOf('.');
//                 var key = this.name.substring(0, pos);
//                 // 判断此key是否已存在json数据中，不存在则新建一个对象出来
//                 if (!existKeyInJSON(key, json)) {
//                     json[key] = {};
//                 }
//                 var subKey = this.name.substring(pos + 1);
//                 json[key][subKey] = this.value || '';
//             }
//             // 普通属性
//             else {
//                 json[this.name] = this.value || '';
//             }
//
//         }
//     });
//
//     // 处理那些值应该属于数组的元素，即带'[number]'的key-value对
//     var resultJson = {};
//     for (var key in json) {
//         // 数组元素
//         if (key.indexOf('[') > -1) {
//             var pos = key.indexOf('[');
//             var realKey = key.substring(0, pos);
//             // 判断此key是否已存在json数据中，不存在则新建一个数组出来
//             if (!existKeyInJSON(realKey, resultJson)) {
//                 resultJson[realKey] = [];
//             }
//             resultJson[realKey].push(json[key]);
//
//         }
//         else { // 单元素
//             resultJson[key] = json[key];
//         }
//     }
//     return resultJson;
// };

/**
 * 功能：判断key在Json结构中是否存在
 * 存在，返回true; 否则，返回false.
 */
function existKeyInJSON(key, json) {
    if (key == null || key == '' || $.isEmptyObject(json)) {
        return false;
    }
    var exist = false;
    for (var k in json) {
        if (key === k) {
            exist = true;
        }
    }
    return exist;
}

if (!Object.assign) {
    Object.defineProperty(Object, "assign", {
        enumerable: false,
        configurable: true,
        writable: true,
        value: function (target, firstSource) {
            "use strict";
            if (target === undefined || target === null)
                throw new TypeError("Cannot convert first argument to object");
            var to = Object(target);
            for (var i = 1; i < arguments.length; i++) {
                var nextSource = arguments[i];
                if (nextSource === undefined || nextSource === null) continue;
                var keysArray = Object.keys(Object(nextSource));
                for (var nextIndex = 0, len = keysArray.length; nextIndex < len; nextIndex++) {
                    var nextKey = keysArray[nextIndex];
                    var desc = Object.getOwnPropertyDescriptor(nextSource, nextKey);
                    if (desc !== undefined && desc.enumerable) to[nextKey] = nextSource[nextKey];
                }
            }
            return to;
        }
    });
}


/**
 * 格式化日期
 *
 * @param val
 * @param row
 * @returns {String}
 */
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

/**
 * 格式化数字
 *
 * @param val
 * @param row
 * @returns {Number}
 */
function formatternumber(val) {
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
function formatterTitle(val) {
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