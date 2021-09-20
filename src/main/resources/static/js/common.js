function isNotBlank(value) {
    //正则表达式用于判斷字符串是否全部由空格或换行符组成
    var reg = /^\s*$/
    //返回值为true表示不是空字符串
    return (value != null && value != undefined && !reg.test(value))
}

function isBlank(value) {
    return !isNotBlank(value);
}

var ymd = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
var hms = /^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
var ymdhms = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;

var ymdRE = new RegExp(ymd);
var hmsRE = new RegExp(hms);
var ymdhmsRE = new RegExp(ymdhms);

$.format = function (value, outFormat, defValue) {
    var formatString = outFormat ? outFormat : "yyyy-MM-dd hh:mm:ss";
    var defaultValue = defValue ? defValue : "";
    if (this instanceof String) {
        formatString = this;
    } else if (this instanceof Array) {
        formatString = this.length > 0 ? this[0] : formatString;
        defaultValue = this.length > 1 ? this[1] : defaultValue;
    }
    var date = getDateByString(value);
    return date ? date.format(formatString) : defaultValue;
};

function getDateByString(value) {
    var date = null;
    try {
        if (value) {
            console.info(value + "=" + value.replace(/\-/g, "/"));
            if (ymdRE.test(value)) {
                value = value.replace(/\-/g, "/");
                date = new Date(value + " 00:00:00");
            } else if (hmsRE.test(value)) {
                value = value.replace(/\-/g, "/");
                date = new Date("1970/01/01 " + value);
            } else if (ymdhmsRE.test(value)) {
                value = value.replace(/\-/g, "/");
                date = new Date(value);
            }
        }
    } catch (e) {
        console.log("Date error");
    }
    console.info("date=" + date);
    return date;
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

function isTime(str) {
    var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) { return false; }
    if (a[1] >= 24 || a[3] >= 60 || a[4] >= 60) {
        return false
    }
    return true;
}

function isDate(str) {
    var result = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (result == null) return false;
    var d = new Date(result[1], result[3] - 1, result[4]);
    return (d.getFullYear() == result[1] && d.getMonth() + 1 == result[3] && d.getDate() == result[4]);
}

function isDatetime(str) {//日期格式：yyyy-mm-dd hh:mm:ss
    var result = str.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
    if (result == null) return false;
    var d = new Date(result[1], result[3] - 1, result[4], result[5], result[6], result[7]);
    return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4] && d.getHours() == result[5] && d.getMinutes() == result[6] && d.getSeconds() == result[7]);
}
