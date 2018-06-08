var utils = {
    timeFormat: function (value) {
		if(value===null){
			return '-';
		}
        var date = new Date(value)
        var Y = date.getFullYear()
        var m = date.getMonth() + 1
        var d = date.getDate()
        var H = date.getHours()
        var i = date.getMinutes()
        var s = date.getSeconds()
        if (m < 10) {
            m = '0' + m
        }
        if (d < 10) {
            d = '0' + d
        }
        if (H < 10) {
            H = '0' + H
        }
        if (i < 10) {
            i = '0' + i
        }
        if (s < 10) {
            s = '0' + s
        }
        var vart = Y + '-' + m + '-' + d + ' ' + H + ':' + i + ':' + s
        return vart
    },
    dateFormat: function (value) {
		if(value===null){
			return '-';
		}
        var date = new Date(value)
        var Y = date.getFullYear()
        var m = date.getMonth() + 1
        var d = date.getDate()
        var H = date.getHours()
        var i = date.getMinutes()
        var s = date.getSeconds()
        if (m < 10) {
            m = '0' + m
        }
        if (d < 10) {
            d = '0' + d
        }
        if (H < 10) {
            H = '0' + H
        }
        if (i < 10) {
            i = '0' + i
        }
        if (s < 10) {
            s = '0' + s
        }
        return Y + '-' + m + '-' + d
    },
    priceFormat: function (value) {
		return value.toFixed(2);
	}
}


export default utils;
