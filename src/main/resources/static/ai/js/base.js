const $ = jQuery;
const ExpireTime = 3600000 * 24 * 7;

var ResultStatus = {
    success: 1,//成功
    fail: 0,//失败
    error: -1,//错误
    logout: -100,//登陆超时
}

var MSG = {
    reading: "读取中...",
    saving: "保存中...",
    submiting: "提交中...",
    dealing: "处理中...",
    doNotSubmiting: "正在处理中,请稍候再提交...",
    dataGetFail: "数据获取失败",
    landing: "登陆中...",
}

var alertMsg = {
    theme: 'dark',
    index: null,
    error: function (content) {
        layer.msg(content);
    },
    confirm: function (content, callback) {
        //询问框
        alertMsg.index = layer.confirm(content, {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(alertMsg.index);
            callback();
        }, function () {

        });
    },
    loading: function () {
        this.index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
    },
    success: function (msg, callback) {
        var time = 3000;
        this.index = layer.msg(msg, {anim: 5, time: time, shade: 0.5});
        if (callback != null) {
            setTimeout(function () {
                callback();
            }, time);
        }
    },
    prompt: function (title, callback) {
        this.index = layer.prompt({title: title, formType: 2}, function (text, index) {
            //layer.close(index);
            callback(text);
        });
    },
    closeLoading: function () {
        layer.close(this.index);
    },
    closeAll: function () {
        layer.closeAll();
    }
}

/**
 * 获取请求后台接口的
 * @returns {*}
 */
function getCsrfToken() {
    return $("meta[name='csrf-token']").attr("content");
}

var Ajax = {
    startRequest: function () {
        alertMsg.loading();
    },
    /**
     * 发送get请求
     * @param data json格式
     */
    get: function (url, data, callback, headers) {
        this.startRequest();
        data.csrftoken = getCsrfToken();
        var authorization = getToken();
        var sign = getSign();
        axios.get(url, {
            headers: {'Authorization': authorization, 'sign': sign},
            params: data
        }).then(function (response) {
            Ajax.end();
            callback(response.data);
        }).catch(function (error) {
            Ajax.end(); //关闭加载层
            console.log(error);
        });
    },
    /**
     * 发送post请求
     * @param url
     * @param data json格式
     * @param callback
     */
    post: function (url, data, callback, headers) {
        this.startRequest();
        data._csrf = getCsrfToken();
        var authorization = getToken();
        var sign = getSign();
        axios.post(url, data, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authorization,
                'sign': sign
            }
        }).then(function (response) {
            Ajax.end();
            if (response.data.status == ResultStatus.logout) {
                toLogin();
            }
            callback(response.data);
        }).catch(function (error) {
            Ajax.end(); //关闭加载层
            console.log(error);
        });
    },
    /**
     * 发送put请求
     * @param url
     * @param data json格式
     * @param callback
     */
    put: function (url, data, callback, headers) {
        this.startRequest();
        data.csrftoken = getCsrfToken();
        var authorization = getToken();
        var sign = getSign();
        axios.put(url, data, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authorization,
                'sign': sign
            }
        }).then(function (response) {
            Ajax.end();
            callback(response.data);
        }).catch(function (error) {
            Ajax.end(); //关闭加载层
            console.log(error);
        });
    },
    end: function () {
        alertMsg.closeLoading();
    }
}

/**
 * 表单校验
 */
function checkForm(id) {
    var inputs = $("#" + id).find("input[required='true']");
    var len = inputs.length;
    for (var i = 0; i < len; i++) {
        var item = inputs[i];
        var value = $(item).val();
        if (!value) {
            alertMsg.warn($(item).attr('placeholder'));
            return;
        }
    }
}

/**
 * 判断是否为空或undefined
 * @param obj
 */
function isNull(obj) {
    if (obj == null || typeof(obj) == 'undefined') {
        return true;
    }
    return false;
}

function sleep(delay) {
    var start = (new Date()).getTime();
    while ((new Date()).getTime() - start < delay) {
        continue;
    }
}

function setLoginInfo(json) {
    setSign(json.sign);
    setToken(json.token);
}

/**
 * 清空登录信息的cookie
 */
function clearLoginInfo() {
    clearCookie(['sign', 'Authorization']);
}

/**
 * 清空cookie
 * @param array 数组
 */
function clearCookie(array) {
    var len = array.length;
    for (var i = 0; i < len; i++) {
        $.cookie(array[i], '', {expires: -1, path: '/'}); // 删除 cookie
    }
}

function setSign(sign) {
    var cookietime = new Date();
    cookietime.setTime(cookietime.getTime() + ExpireTime);//coockie保存时间
    $.cookie('sign', sign, {expires: cookietime, path: '/'});
}

function getSign() {
    return $.cookie('sign');
}

function setToken(token) {
    var cookietime = new Date();
    cookietime.setTime(cookietime.getTime() + ExpireTime);//coockie保存一小时
    $.cookie('Authorization', token, {expires: cookietime, path: '/'});
}

function getToken() {
    return $.cookie('Authorization');
}

function supportLocalStorage() {
    /*alert(navigator.userAgent);*/
    if (navigator.userAgent.indexOf('UCBrowser') > -1) {
        return false;
    }
    var uaFlag = 0;
    var uaArr = new Array('Chrome', 'MQQBrowser', 'QQ', 'TBS', 'wxwork', 'MicroMessenger', 'T7', 'baiduboxapp', 'baidubrowser', 'MiuiBrowser', 'NetType', 'OPR');
    for (var i = 0; i < uaArr.length; i++) {
        if (navigator.userAgent.indexOf(uaArr[i]) > -1) {
            uaFlag = 1;
        }
    }
    if (uaFlag != 1) {
        if (navigator.userAgent.indexOf('HUAWEIEVA') > -1 || navigator.userAgent.indexOf('HUAWEIVTR') > -1) {
            return false;
        }
    }
    var testKey = 'test';
    try {
        window.localStorage.setItem(testKey, 'testValue');
        window.localStorage.removeItem(testKey);
        return true;
    } catch (e) {
        return false;
    }
}

/**
 * 打开弹窗页面
 * @param title 标题
 * @param url 页面路径
 */
function openTopFrameDialog(title, url) {
    window.parent.openFrameWin(title, url);
}

var winIndex = null;

/**
 * 关闭弹窗
 * @param reload 是否刷新父页面
 */
function closeWin(reload) {
    parent.layer.closeAll();
    //layer.close(winIndex);
    if (reload == true && typeof window.parent.getData == 'function') {
        console.log('刷新列表...');
        window.parent.getData(true);
    }
}

/**
 * 打开弹窗
 * @param title
 * @param url
 */
function openWin(title, url, width, height, zIndex) {
    var wWidth = width == null ? '60%' : width;
    var wHeight = height == null ? '90%' : height;
    winIndex = layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: 0.3,
        area: [wWidth, wHeight],
        zIndex: (zIndex == null ? 10000 : zIndex),
        content: url //iframe的url
    });
}

/**
 * 时间戳转日期yyyy-MM-dd HH:mm:ss
 * @param timestamp
 * @returns {string}
 */
function timestampToTime(timestamp) {
    var date = null;
    if (timestamp.toString().length == 10) {
        console.log('we');
        date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    } else {
        date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    }
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y + M + D + h + m + s;
}

function kindeditorOpts() {
    return {
        uploadJson: API + '/v1/auth/ai/common/image/upload?from=kindeditor',
        fileManagerJson: '/kindeditor/fileManager',
        allowFileManager: false,
        filePostName: 'file',
        extraFileUploadParams: {'Authorization': getToken(), 'sign': getSign()},
        items: ['source', '|', 'undo', 'redo', '|', 'preview', 'template', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
            'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink']
    }
}

//long类型或运算
function longOr(arr) {
    var arrLen = arr.length;
    if (arrLen < 1) {
        return '';
    }
    var longValue = Long.fromValue(arr[0], true);
    for (var i = 1; i < arrLen; i++) {
        longValue = longValue.or(arr[i]);
    }
    return longValue.toString();
}

//long类型根据数值得到选中的名字
function longAnd(arr, num) {
    var cateLen = arr.length;
    var tagArr = [];
    var tagcodeArr = [];
    var tagnameArr = [];
    for (var i = 0; i < cateLen; i++) {
        var longValue = Long.fromValue(num, true);
        //console.log(longValue.and(arr[i].id).toString());
        if (longValue.and(arr[i].id).toString() > 0) {
            tagnameArr.push(arr[i].tagName);
            tagcodeArr.push(arr[i].id);
        }
    }
    tagArr = [tagnameArr, tagcodeArr]
    return tagArr;
}

/**
 * 数组转json
 */
function array2Json(arr) {
    return JSON.stringify(arr);
}

/**
 * json转数组
 */
function json2Array(jsonStr) {
    return JSON.parse(jsonStr);
}

/**
 * 生成列表页脚
 * @param id 页脚元素
 * @param pageable
 * @param callback
 */
function setPager(id, pageable, callback) {
    $.jqPaginator('#' + id, {
        totalPages: pageable.totalPages == 0 ? 1 : pageable.totalPages,
        first: '<li class="paginate_button page-item  previous"><a class="page-link"  href="javascript:;">首页</a></li>',
        prev: '<li class="paginate_button page-item "><a class="page-link"  href="javascript:;">上一页</a></li>',
        next: '<li class="paginate_button page-item "><a class="page-link"  href="javascript:;">下一页</a></li>',
        last: '<li class="paginate_button page-item next"><a class="page-link" href="javascript:;">末页</a></li>',
        page: '<li class="paginate_button page-item "><a class="page-link"  href="javascript:;">{{page}}</a></li>',
        visiblePages: 10,
        currentPage: pageable.pageIndex,
        onPageChange: function (num, type) {
            if (type == 'change') {
                pageable.pageIndex = num;
                callback();
            }
        }
    });
}

/**
 * 返回登陆页面
 */
function toLogin() {
    var parent = window.parent;
    if (parent != null) {
        window.parent.location.href = '/ai/login';
    } else {
        window.location.href = '/ai/login';
    }
}

var myDate = {
    year: function () {
        var date = new Date();
        return date.getFullYear();
    },
    month: function () {
        var date = new Date();
        return date.getMonth();
    },
    date2Timestamp: function (date) {
        date = date.replace(/-/g, '/');
        var timestamp = new Date(date).getTime();
        return timestamp;
    },
    timestamp2Date: function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    },
    timestamp2MDDate: function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        return y + '-' + m ;
    },
    /**
     * 获取两日期相差天数
     * @param date1 开始日期
     * @param date2 结束日期
     * @returns {number}
     */
    getNumberOfDays: function (date1, date2) {//获得天数
        //date1：开始日期，date2结束日期
        var a1 = Date.parse(new Date(date1));
        var a2 = Date.parse(new Date(date2));
        var day = parseInt((a2 - a1) / (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
        return day
    },
    /**
     * 获取上个月
     * @param date 2019-01-02
     */
    getPreMonth: function (date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        var year2 = year;
        var month2 = parseInt(month) - 1;
        if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    },
    /**
     * 获取下一个月
     * @param date
     */
    getNextMonth: function (date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中的月的天数
        var year2 = year;
        var month2 = parseInt(month) + 1;
        if (month2 == 13) {
            year2 = parseInt(year2) + 1;
            month2 = 1;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    },
}

/**
 * 是否含有某个字符串
 * @param str 原字符
 * @param char 包含字符
 */
function containsStr(str, char) {
    if (str == null) {
        return false;
    }
    return str.indexOf(char) != -1;
}


/***
 * 创建上传工具
 * @param id 按钮
 * @param img 显示图片的元素
 */
function initFcupUploader(id, img) {
    $.fcup({
        upId: id, //上传dom的id
        upShardSize: '0.2', //切片大小,(单次上传最大值)单位M，默认2M
        upMaxSize: '2', //上传文件大小,单位M，不设置不限制
        upUrl: CTX + '/common/weight/image/upload?from=fcup', //文件上传接口
        upType: 'jpg,png,jpeg,gif', //上传类型检测,用,号分割
        //接口返回结果回调，根据结果返回的数据来进行判断，可以返回字符串或者json来进行判断处理
        upCallBack: function (res) {
            // 状态
            var status = res.status;
            // 信息
            var msg = res.msg;
            // url
            var url = res.url + "?" + Math.random();

            // 已经完成了
            if (status == 2) {
                alertMsg.closeLoading();
                alertMsg.success(msg);
                $('#' + img).attr("src", url);
            }
            // 还在上传中
            if (status == 1) {
                console.log(msg);
            }
            // 接口返回错误
            if (status == 0) {
                alertMsg.closeLoading();
                // 停止上传并且提示信息
                //$.upStop(msg);
                alertMsg.error(msg);
            }
        },
        // 上传过程监听，可以根据当前执行的进度值来改变进度条
        upEvent: function (num) {
            // num的值是上传的进度，从1到100
            //Progress(num);
        },
        // 发生错误后的处理
        upStop: function (errmsg) {
            // 这里只是简单的alert一下结果，可以使用其它的弹窗提醒插件
            alertMsg.closeLoading();
            alertMsg.error(errmsg);
        },
        // 开始上传前的处理和回调,比如进度条初始化等
        upStart: function () {
            //Progress(0);
            alertMsg.loading();
        }
    });
}

function initWebUploader(btnId, callback) {
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: CTX + '/static/ai/plugs/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: CTX + '/common/weight/image/uploadByFile?from=webuploader',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#' + btnId,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    uploader.on('error', function (handler) {

    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        alertMsg.loading();
    });
    uploader.on('uploadError', function (file, reason) {

    });
    uploader.on('uploadComplete', function (file) {

    });

    uploader.on('uploadSuccess', function (file, response) {
        console.log(response._raw)
        callback(JSON.parse(response._raw));
        alertMsg.closeLoading();
    });
}

/**
 * 生成冒泡提示
 * @param title
 * @param content
 * @returns {string}
 */
function createToolTips(title, content) {
    return '<span title="' + title + '：" style="cursor: pointer;" data-trigger="hover" onmouseenter="showTips(this)" data-content="' + content + '" >' + content + '</span>';
}

/**
 * 创建wangEditor在线编辑器
 * @param id
 */
function createWangEditor(id) {
    if (E == null) {
        alertMsg.error('请先初始化E');
    } else {
        var editor = new E('#' + id);
        // 配置服务器端地址
        editor.customConfig.uploadFileName = 'imgFile';
        editor.customConfig.uploadImgTimeout = 60 * 1000;
        editor.customConfig.uploadImgServer = CTX + '/common/weight/image/upload?from=editor';
        editor.customConfig.uploadImgHooks = {
            customInsert: function (insertImg, result, editor) {
                var url = result.data;
                insertImg(url);
            }
        };
        // 自定义菜单配置
        editor.customConfig.menus = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            'fontName',  // 字体
            'italic',  // 斜体
            'underline',  // 下划线
            'strikeThrough',  // 删除线
            'foreColor',  // 文字颜色
            'backColor',  // 背景颜色
            'link',  // 插入链接
            'list',  // 列表
            'justify',  // 对齐方式
            'quote',  // 引用
            'image',  // 插入图片
            'table',  // 表格
            'video',  // 插入视频
            'code',  // 插入代码
            'undo',  // 撤销
            'redo'  // 重复
        ]
        editor.create();
        return editor;
    }
}


/**
 * 表格行上移
 * @param obj
 */
function tableRowMoveUp(obj) {
    var current = $(obj).parent().parent();  //获取当前<tr>
    var prev = current.prev();   //获取当前<tr>前一个元素
    if (current.index() > 0) {
        current.insertBefore(prev);  //插入到当前<tr>前一个元素前
    }
}

/**
 * 表格行下移
 * @param obj
 */
function tableRowMoveDown(obj) {
    var current = $(obj).parent().parent();  //获取当前<tr>
    var next = current.next();  //获取当前<tr>后面一个元素
    if (next) {
        current.insertAfter(next);   //插入到当前<tr>后面一个元素后面
    }
}

/**
 * 移除word文档标签
 * @param text
 * @returns {*}
 */
function removeWordXml(text) {
    var html = text;
    html = html.replace(/<\/?SPANYES[^>]*>/gi, "");//  Remove  all  SPAN  tags
    // html = html.replace(/<(\w[^>]*)  class=([^|>]*)([^>]*)/gi, "<$1$3");  //  Remove  Class  attributes
    // html = html.replace(/<(\w[^>]*)  style="([^"]*)"([^>]*)/gi, "<$1$3");  //  Remove  Style  attributes
    html = html.replace(/<(\w[^>]*)  lang=([^|>]*)([^>]*)/gi, "<$1$3");//  Remove  Lang  attributes
    html = html.replace(/<\\?\?xml[^>]*>/gi, "");//  Remove  XML  elements  and  declarations
    html = html.replace(/<\/?\w+:[^>]*>/gi, "");//  Remove  Tags  with  XML  namespace  declarations:  <o:p></o:p>
    html = html.replace(/&nbsp;/, "");//  Replace  the  &nbsp;
    html = html.replace(/\n(\n)*( )*(\n)*\n/gi, '\n');
    //  Transform  <P>  to  <DIV>
    // var  re  =  new  RegExp("(<P)([^>]*>.*?)(<//P>)","gi")  ;            //  Different  because  of  a  IE  5.0  error
//        html = html.replace(re, "<div$2</div>");
    return html;
}

$(function () {
    setTimeout(function () {
        fixElTableHeader();
    }, 1200);
})

function fixElTableHeader() {
    if (app != null && app.$refs != null && app.$refs.tableDataRef != null) {
        app.$refs.tableDataRef.doLayout();
    }
}

/**
 * 虚构数据(用来初始化el-table)
 * @returns {Array}
 */
function initTableData() {
    var list = [];
    for (var i = 0; i < 0; i++) {
        var obj = {};
        list.push(obj);
    }
    return list;
}

/**
 * 获取表格高度
 * @returns {number}
 */
function getTableHeight() {
    return document.body.offsetHeight - 300;
}

/**
 * 展示折叠查询条件
 */
function showOrHideMoreSearchCondition() {
    console.log('in...');
    var elem = $('#moreSearchConditionContainer');
    if ($(elem).is(":visible")) {
        $(elem).fadeOut();
    } else {
        $(elem).fadeIn();
    }
}