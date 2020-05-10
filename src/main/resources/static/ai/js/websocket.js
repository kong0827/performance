/**
 * 本脚本用于与服务端进行数据交互，如：通知信息
 */
var authorization = $.cookie('Authorization');
var sign = $.cookie('sign');
var repeatTime = 30000;//与后台交互时间

function initSocket(option) {
    //服务器地址
    var locate = window.location;
    var url = option.url;
    //回调函数
    var callback = option.callback;
    if (typeof callback !== "function") {
        console.log('callback 必须为函数');
        return false;
    }
    //一些对浏览器的兼容已经在插件里面完成
    websocket = new ReconnectingWebSocket(url);

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("websocket.error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        console.log("onopen");
        //发送信息
        var param = {sign: sign, authorization: authorization}
        websocket.send(JSON.stringify(param));
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        callback(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        websocket.close();
        console.log("websocket.onclose");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }
    return websocket;
}

var socket = null;
var option = {};
//option.url = 'ws://adminblm.bolemeng.com/ws/ai';
option.url = 'ws://localhost:8000/ws/ai';
option.callback = function (resultJson) {
    console.log(resultJson);
    if (resultJson == 'connected') {

    }
    else if (resultJson) {
        var json = JSON.parse(resultJson);
        $('#topBarNoticeCount').text(json.noticeCount);
        var phomeUserNoticeRecordEntityList = json.phomeUserNoticeRecordEntityList;
        var html = '';
        for (var i = 0; i < phomeUserNoticeRecordEntityList.length; i++) {
            html += '<li>\n' +
                '                        <a href="javascript:void(0);" class="dropdown-item">\n' +
                '                            <div>\n' +
                '                                <i class="fa fa-envelope fa-fw"></i> ' + phomeUserNoticeRecordEntityList[i].title + '\n' +
                '                                <span class="float-right text-muted small">' + myDate.timestamp2Date(phomeUserNoticeRecordEntityList[i].createTime * 1000) +
                '<div style="display:none;" class="text-right" ><a onclick="removeNotice(this)" id="' + phomeUserNoticeRecordEntityList[i].id + '" href="javascript:void(0);" >不再提醒</a></div></span>\n' +
                '                            </div>\n' +
                '                        </a>\n' +
                '                    </li>\n' +
                '                    <li class="dropdown-divider"></li>';
        }
        html += '<li>\n' +
            '                        <div class="text-center link-block">\n' +
            '                            <a href="' + CTX + '/ai/notice/list" class="dropdown-item">\n' +
            '                                <strong>查看所有提醒</strong>\n' +
            '                                <i class="fa fa-angle-right"></i>\n' +
            '                            </a>\n' +
            '                        </div>\n' +
            '                    </li>';
        $('#topBarNoticeList').html(html);
    }
}

socket = initSocket(option);

/**
 * 每个一段时间与后台交互数据
 */
setInterval(function () {
    //发送信息
    var param = {sign: sign, authorization: authorization}
    socket.send(JSON.stringify(param));
}, repeatTime);

/**
 * 移除通知
 * @param elem
 */
function removeNotice(elem) {
    var id = $(elem).attr('id');
    console.log(id);
    Ajax.post('${API}/v1/auth/ai/notice/read', {id: id}, function (resultJson) {
        if (resultJson.status == ResultStatus.success) {
            //删除成功，移除相关元素

        } else {
            alertMsg.error(resultJson.msg);
        }
    });
}