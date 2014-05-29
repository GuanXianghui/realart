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
});

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    location.href = "qrCode.jsp?pageNum=" + pageNum;
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
                    return;
                } else {
                    //请求成功
                    showSuccess(data["message"]);
                    $("#bigImg").attr("src", data["previewImg"]);
                    $("#showBigImgA").click();
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
function showBigImg(url){
    $("#bigImg").attr("src", url);
    $("#showBigImgA").click();
}

/**
 * 选择logo
 * @param t
 * @param logoIndex
 */
function chooseQrCodeLogo(t, logoIndex){
    $(".choose_logo").css("border", "0px solid red");
    $(t).css("border", "1px solid red");
    chooseLogo = logoIndex;
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