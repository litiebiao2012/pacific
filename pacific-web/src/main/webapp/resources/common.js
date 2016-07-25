function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

$(document).ajaxSuccess(function (event, xhr, settings) {
    try {
        var json = xhr.responseJSON;
        // console.log(json);
        if (json.status == "NO_LOGIN") {
            location.href = json.data;
        } else if (json.status == 'NO_PERMISSION') {
            //没有全限
            alert('没有权限, 请联系管理员');
        } else if (json.status != 'OK') {
            alert('服务器响应超时, 请联系管理员');
        }

    } catch (e) {
    }
});

//初始化表单
function resetForm(dom) {
    $(':input', dom)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

//数组去重
Array.prototype.unique = function () {
    var res = [this[0]];
    for (var i = 1; i < this.length; i++) {
        var repeat = false;
        for (var j = 0; j < res.length; j++) {
            if (this[i] == res[j]) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            res.push(this[i]);
        }
    }
    return res;
}

ResponseStatus = {
    OK: 'OK'
}

Common = {
    language: {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    }
    ,
    uuid: function () {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;

    },


    cellMore: function (text) {
        if (text == undefined) {
            return '';
        }

        var showText = '';
        if (text.length > 100) {
            showText = text.substring(0, 100) + " ...<br>";

            var id = Common.uuid();
            if (Common.cellMoreData == undefined) {
                Common.cellMoreData = {};
            }
            Common.cellMoreData[id] = text;
            var moreButton = '<button name="cellMore" data="' + id + '"  class="glyphicon glyphicon-eye-open btn btn-link" aria-hidden="true">更多</button>';
            showText += moreButton;
        }

        return showText;

    },

    dateFormatter: function (data) {
        if (data == undefined) {
            return '';
        }

        if (!isNaN(data)) {
            return DateFormat.format.date(parseInt(data), 'yyyy-MM-dd HH:mm:ss');
        }

        return data;
    }


};


$(function () {

    /**
     * 表格中,显示全部按钮事件
     */
    $('body').on('click', 'button[name="cellMore"]', function () {

        var id = $(this).attr('data');
        var html = Common.cellMoreData[id];

        //页面层
        layer.open({
            type: 1,
            shadeClose: true,
            skin: 'layui-layer-rim', //加上边框
            area: ['700px', '500px'], //宽高
            content: html
        });
    });
});


function dataTable(opt) {
    var _opt = {
        language: Common.language,
        searching: false,
        lengthChange: false,
        autoWidth: false,

        columnDefs: [
            {
                defaultContent: '',
                targets: '_all'
            }],
        order: [],
        columns: opt.columns
    };

    if (opt.data != undefined) {
        _opt.data = opt.data;
    }

    if (opt.url != undefined) {
        _opt.serverSide = true;
        _opt.ajax = function (data, callback, settings) {
            loading = layer.load(2, {
                shade: [0.1, '#000']
            });

            var orderLength = data.order.length;
            var sortProperty = orderLength == 0 ? '' : settings.aoColumns[data.order[0].column].sort || data.columns[data.order[0].column].data;
            var sortDirection = orderLength == 0 ? '' : data.order[0].dir;

            var commonParam = {
                'pageSize': data.length,
                'currentPage': data.start / data.length + 1,
                'property': sortProperty,
                'direction': sortDirection,
            };
            if (opt.param != undefined) {
                commonParam = $.extend(opt.param, commonParam);
            }

            $.ajax({
                type: "post",
                url: opt.url,
                dataType: "json",
                data: $.extend($(opt.form).serializeObject(), commonParam),
                success: function (res) {
                    var data = res.data;
                    callback({
                        recordsTotal: data.totalCount,
                        recordsFiltered: data.totalCount,
                        data: data.data
                    });
                },
                error: function () {
                    console.log('请求失败')
                },
                complete: function () {
                    layer.close(loading);
                }
            })
        }
    }
    return $(opt.selector).DataTable(_opt);

}


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


function getClientIpParam() {
    return getQueryString('clientIp') == null ? 'all' : getQueryString('clientIp');
}

function getTimeInternalParam() {
    return getQueryString('timeInternal') == null ? 'tenMinutes' : getQueryString('timeInternal');
}

function getTypeParam() {
    return getQueryString('type') == null ? 'jvmReport' : getQueryString('type');
}

function getApplicationCode() {
    return getQueryString('applicationCode') == null ? 'pacific' : getQueryString('applicationCode');
}

function getHostName() {
    return getQueryString('hostName') == null ? 'all' : getQueryString('hostName')
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}