/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
    //<!-- jQuery 颜色选择器 Spectrum -->
    $("#bgColor").spectrum({
        color: "#fff"
    });
    $("#frontColor").spectrum({
        color: "#000"
    });
    $("#logoBorderColor").spectrum({
        color: "#0f0"
    });
    //初始化默认数据
    initDefaultValue();
    //日期控件初始化
    $("#startDate").datepicker();
    $( "#startDate" ).datepicker( "option", "dateFormat", "yymmdd" );
    $( "#startDate" ).datepicker( "option", "showAnim", "clip" );
    $( "#startDate" ).datepicker( "option", "onSelect", function(dateText, inst){
    });
    $("#endDate").datepicker();
    $( "#endDate" ).datepicker( "option", "dateFormat", "yymmdd" );
    $( "#endDate" ).datepicker( "option", "showAnim", "clip" );
    $( "#endDate" ).datepicker( "option", "onSelect", function(dateText, inst){
    });
    //赋值起始日期
    $("#startDate").val(initStartDate);
    //赋值终止日期
    $("#endDate").val(initEndDate);
});

/**
 * 初始化默认数据
 */
function initDefaultValue(){
    $("#antiError").val(defaultAntiError);
    $("#size").val(defaultSize);
    $("#bgColor").spectrum("set", defaultBgColor);
    $("#frontColor").spectrum("set", defaultFrontColor);
    $("#type").val(defaultType);
    $("#qrLogo").val(defaultQrLogo);
    chooseLogo = defaultQrLogo;
    var qrLogos = $("#qr_logos img");
    for(var i=0;i<qrLogos.length;i++){
        if((i+1) == chooseLogo){
            $(qrLogos[i]).css("border", "2px solid red");
        }
    }
    $("#logoBorderType").val(defaultLogoBorderType);
    $("#logoBorderColor").spectrum("set", defaultLogoBorderColor);
}

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    var html = "qrCode.jsp?pageNum=" + pageNum + "&uuid="+ $("#uuid").val()
        + "&state=" + $("#state").val() + "&startId=" + $("#startId").val() + "&endId=" + $("#endId").val()
        + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
    location.href = html;
}

/**
 * 预览二维码
 */
function preViewQrCode(){
    $("#bgColor").val($("#bgColor").spectrum("get"));
    $("#frontColor").val($("#frontColor").spectrum("get"));
    $("#logoBorderColor").val($("#logoBorderColor").spectrum("get"));
    $("#qrLogo").val(chooseLogo);
    //ajax请求
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "preViewQrCode.do",
        data:"antiError=" + $("#antiError").val() + "&size=" + $("#size").val() + "&bgColor=" + $("#bgColor").val() +
            "&frontColor=" + $("#frontColor").val() + "&type=" + $("#type").val() + "&qrLogo=" + $("#qrLogo").val() +
            "&logoBorderType=" + $("#logoBorderType").val() + "&logoBorderColor=" + $("#logoBorderColor").val() +
            "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判请求是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //请求成功
                    showSuccess(data["message"]);
                    //查看大图
                    showBigImg(data["previewImg"], EMPTY);
                }
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}

/**
 * 生成序列号
 */
function generateQrCode(){
    if($("#num").val() > 20){
        if(!confirm("请确认要一次性批量生成" + $("#num").val() + "个序列号吗")){
            return;
        }
    }
    $("#bgColor").val($("#bgColor").spectrum("get"));
    $("#frontColor").val($("#frontColor").spectrum("get"));
    $("#logoBorderColor").val($("#logoBorderColor").spectrum("get"));
    $("#qrLogo").val(chooseLogo);
    document.forms["generateQrCodeForm"].action = "generateQrCode.do?token=" + token;
    document.forms["generateQrCodeForm"].submit();
}

/**
 * 点击查看大图
 * @param url
 */
function showBigImg(url, uuid){
    $("#bigImg").attr("src", url);
    if(EMPTY == uuid){
        $("#qrCodeUrl").css("display", "none");
    } else {
        $("#qrCodeUrl").html(qrCodeUrlPrefix + uuid);
        $("#qrCodeUrl").css("font-weight", "bold");
        $("#qrCodeUrl").css("display", "");
    }
    $("#showBigImgA").click();
}

/**
 * 选择logo
 * @param t
 * @param logoIndex
 */
function chooseQrCodeLogo(t, logoIndex){
    $(".choose_logo").css("border", "0px solid red");
    $(t).css("border", "2px solid red");
    chooseLogo = logoIndex;
}

/**
 * 取消logo
 */
function clearQrCodeLogo(){
    $(".choose_logo").css("border", "0px solid red");
    chooseLogo = 0;
}

/**
 * 上传二维码logo
 */
function uploadQrCodeLogo(){
    document.forms["uploadQrCodeLogoForm"].action = "uploadQrCodeLogo.do?token=" + token;
    $("#upload_qr_code_logo").click();
}

/**
 * 删除二维码logo
 */
function deleteQrCodeLogo(){
    if(chooseLogo == 0){
        showAttention("请选择logo");
        return;
    }
    $("#delete_qr_code_logo").val(chooseLogo);
    document.forms["deleteQrCodeLogoForm"].action = "deleteQrCodeLogo.do?token=" + token;
    document.forms["deleteQrCodeLogoForm"].submit();
}

/**
 * 保存二维码默认配置
 */
function saveDefaultQrCode(){
    $("#bgColor").val($("#bgColor").spectrum("get"));
    $("#frontColor").val($("#frontColor").spectrum("get"));
    $("#logoBorderColor").val($("#logoBorderColor").spectrum("get"));
    $("#qrLogo").val(chooseLogo);
    var defaultQrCode = "antiError=" + $("#antiError").val() + "&size=" + $("#size").val() + "&bgColor=" + $("#bgColor").val() +
        "&frontColor=" + $("#frontColor").val() + "&type=" + $("#type").val() + "&qrLogo=" + $("#qrLogo").val() +
        "&logoBorderType=" + $("#logoBorderType").val() + "&logoBorderColor=" + $("#logoBorderColor").val();
    $("#save_default_qr_code").val(defaultQrCode);
    $("#save_default_qr_code_info").val($("#info").val());
    document.forms["saveDefaultQrCodeForm"].action = "saveDefaultQrCode.do?token=" + token;
    document.forms["saveDefaultQrCodeForm"].submit();
}

/**
 * 下载
 */
function downloadQrCode(){
    //ajax请求
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "downloadQrCode.do",
        data:"uuid=" + $("#uuid").val() + "&state=" + $("#state").val() + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判请求是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //请求成功
                    showSuccess(data["message"]);
                    //打开新页面下载zip
                    window.open(data["downloadZip"]);
                }
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}

/**
 * 删除二维码
 * @param qrCode
 * @param used
 */
function deleteQrCode(qrCode, used){
    //已被用 不能删除
    if(used){
        showError("该序列号已绑定艺术品，不能删除！")
        return;
    }
    $("#delete_qr_code").val(qrCode);
    document.forms["deleteQrCodeForm"].action = "deleteQrCode.do?token=" + token;
    document.forms["deleteQrCodeForm"].submit();
}