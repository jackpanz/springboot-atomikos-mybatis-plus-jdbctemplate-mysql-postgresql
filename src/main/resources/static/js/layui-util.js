function setLayuiOnTool(name) {
    layui.table.on('tool(' + name + ')', function (obj) {
        var fun = eval(obj.event);
        if (typeof fun === "function") {
            fun(obj.data,obj);
        }
    });
}

function templateAnalysis(template, data) {
    var regular = /{{(.*?)}}/g;
    var template_cp = template
    while (match = regular.exec(template)) {
        var fullName = match[0];
        var name = match[1];
        var value = null;
        if ($.trim(name) === "") {
            return null;
        }
        try {
            value = eval(name);
        } catch (e) {
        }
        if (typeof (value) === "undefined" || value == null) {
            return null;
        }
        console.info("url replace:fullName=" + fullName + " value=" + value);
        template_cp = template_cp.replace(fullName, value);
    }
    return template_cp;
}

function tmd(args, data, queueName) {
    var url = null;
    if (typeof args == "string") {
        var regular = /{{(.*?)}}/g;
        url = args;
        if (regular.test(url)) {
            url = templateAnalysis(url, data);
        }
    } else if (typeof args == "function") {
        try {
            url = args.apply(null, data);
        } catch (e) {
        }
    }

    url = url.indexOf("?") === -1 ? url + "?times=" + new Date().getTime() : url + "&times=" + new Date().getTime();

    //console.info("url:"+ url);
    if (url && url != "") {
        $.ajax({
            url: url,
            method: 'GET',
            dataType: 'json',
            success: function (result) {
                data.push(result.data || result.rows);
                $(document).dequeue(queueName);
            },
            error: function () {
                data.push(null);
                $(document).dequeue(queueName);
            }
        });
    } else {
        data.push(null);
        $(document).dequeue(queueName);
    }
}

$.ajaxObject = function () {
    var queueName = "queue" + guid();
    var data = Array();
    var callback = arguments[arguments.length - 1];
    for (var i = 0; i < arguments.length - 1; i++) {
        $(document).queue(queueName, tmd.bind(this, arguments[i], data, queueName));
    }
    $(document).queue(queueName, function () {
        callback.apply(null, data);
    });
    $(document).dequeue(queueName);
};

function setCheckboxValue(entity, content) {
    $.each(entity, function (name, attr) {
        if(content === attr){
            $.each(content, function (jname, jattr) {
                entity[name+'.' + jname ] = jattr;
            });
            return false;
        }
    });
}

function S4() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

function guid() {
    return (S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4());
}

$.fn.layuiSearch = function (options) {
    var form = this.get(0);
    if (!form) {
        return;
    }
    if (typeof (options) == "undefined") {
        return $.data(form, "layuiSearch");
    } else {
        return new layuiSearch(form, options);
    }
};

function attrHTML(name, value) {
    var reg = /^\s*$/
    if (value != null && value != undefined && !reg.test(value)) {
        return ' ' + name + '="' + value + '"'
    } else {
        return "";
    }
}

function layuiSearch(form, option) {

    var html = '<div class="layui-form-item">';

    $.each(option.els, function (i, el) {
        el = jQuery.extend({}, {
            id: el.id || el.name,
            titleKey: 'title',
            valueKey: 'value',
            value: null,
            placeholder: el.placeholder || el.title || null
        }, el);

        el = jQuery.extend({}, {
            placeholderText: attrHTML('placeholder', el.placeholder),
            idText: attrHTML('id', el.id),
            nameText: attrHTML('name', el.name),
            valueText: attrHTML('value', el.value),
        }, el);

        if (el.type === 'text') {
            el.attributeText = el.idText + el.nameText + el.placeholderText + el.valueText;
        } else {
            el.attributeText = el.idText + el.nameText;
        }

        if (el.type === 'hidden') {
            html += '<input ' + el.attributeText + ' type="hidden" />'
        } else if (el.type === 'text') {
            html += '<div class="layui-inline">'
            html += '<label class="layui-form-label">' + el.title + '</label>'
            html += '<div class="layui-input-inline">'
            html += '<input type="text" ' + el.attributeText + ' autocomplete="off" class="layui-input">'
            html += '</div>'
            html += '</div>'
        } else if (el.type === 'select') {
            html += '<div class="layui-inline">'
            html += '<label class="layui-form-label">' + el.title + '</label>'
            html += '<div class="layui-input-inline">'
            html += '<select ' + el.attributeText + '>'
            $.each(el.rows, function (i, opt) {
                if (el.value === opt[el.valueKey]) {
                    html += '<option selected value="' + opt[el.valueKey] + '">' + opt[el.titleKey] + '</option>';
                } else {
                    html += '<option value="' + opt[el.valueKey] + '">' + opt[el.titleKey] + '</option>';
                }
            })
            html += '</select>'
            html += '</div>'
            html += '</div>'
        }

    })

    //submit
    html += '<div class="layui-inline">'
    html += '<button id="ff-search" class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="' + form.id + '-submit">'
    html += '<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>'
    html += '</button>'
    html += '</div>'

    html += '</div>'
    $(form).append(html);

}

$.ajaxForm = function (options) {
    layui.form.on('submit(' + options.layFilter + ')', function(params){
        options = jQuery.extend({}, {
            type:'post',
            dataType:"json",
            data:params.field,
            success: $.ajaxForm.defSuccess(),
            error:function(e){
                layer.alert("提交失敗！")
            },
        }, options);
        $.ajax(options);
        return false;
    });
}

$.ajaxForm.defSuccess = function() {
    return function(data) {
        if(data.action){
            layer.msg('OK', {
                offset: '15px'
                ,icon: 1
                ,time: 1000
            });
        }
        else{
            layer.alert(data.msg)
        }
    }
}


function showLoading(){
    layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
}

function dismissLoading(){
    layer.closeAll('loading');
}

function alertE(msg){
    layer.alert(msg,{icon: 2})
}

function alertD(){
    layer.alert(msg,{icon: 0})
}

function setTypesValueByArguments(start,arguments,types){
    for (var i = start; i < arguments.length; i++) {
        for(attr in types) {
            if( attr.toString() === typeof arguments[i] ){
                types[attr] = arguments[i];
            }
        }
    }
}

function confirmD(msg,fun1){
    layer.confirm('確定刪除嗎？', {icon: 3, title:'提示'} , fun1);
}

function msgD(){
    var msg = arguments[0];
    var types = {
        'string':null,
        'number':1000,
        'function':null
    }
    setTypesValueByArguments(1,arguments,types);

    layer.msg(msg, {
        offset: '15px'
        ,icon: 1
        ,time: types['number']
    }, function(){
        if( types['function'] !== null ){
            types['function']();
        } else if( isNotBlank(types['string']) ){
            location.href = types['string'] ; //後臺主頁
        }
    });

}

function rule_password(value){
    var regex = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[\W])(?=.*[\S])^[0-9A-Za-z\S]{6,12}$/g;
    if( !regex.test(value) ){
        return "密碼长度为8位或以上，並包括數字、大寫字母、小寫字母以及特殊字符";
    }
}

function rule_repassword(value){
    if (value !== $("#password").val())
        return "新密碼和確認密碼不一樣"
}

function rule_username(value){
    var regex = /^[a-zA-Z]([-_a-zA-Z0-9]{4,20})$/;
    if( !regex.test(value) ){
        return "必须以字母开头，英文、数字";
    }
}