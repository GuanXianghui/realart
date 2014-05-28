/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
});

//点击修改底图
function beforeUploadBgImg(){
    document.getElementById("before_upload_bg_img_td").style.display = "none";
    document.getElementById("upload_bg_img_td").style.display = EMPTY;
}

//取消修改底图
function cancelUploadBgImg(){
    document.getElementById("before_upload_bg_img_td").style.display = EMPTY;
    document.getElementById("upload_bg_img_td").style.display = "none";
}

//上传底图
function uploadBgImg(){
    document.forms["uploadBgImgForm"].action = baseUrl + "uploadBgImg.do?token=" + token;
    document.forms["uploadBgImgForm"].submit();
}

//点击修改logo
function beforeUploadLogoImg(){
    document.getElementById("before_upload_logo_img_td").style.display = "none";
    document.getElementById("upload_logo_img_td").style.display = EMPTY;
}

//取消修改logo
function cancelUploadLogoImg(){
    document.getElementById("before_upload_logo_img_td").style.display = EMPTY;
    document.getElementById("upload_logo_img_td").style.display = "none";
}

//上传logo
function uploadLogoImg(){
    document.forms["uploadLogoImgForm"].action = baseUrl + "uploadLogoImg.do?token=" + token;
    document.forms["uploadLogoImgForm"].submit();
}

//点击修改realart
function beforeUploadRealartImg(){
    document.getElementById("before_upload_realart_img_td").style.display = "none";
    document.getElementById("upload_realart_img_td").style.display = EMPTY;
}

//取消修改realart
function cancelUploadRealartImg(){
    document.getElementById("before_upload_realart_img_td").style.display = EMPTY;
    document.getElementById("upload_realart_img_td").style.display = "none";
}

//上传realart
function uploadRealartImg(){
    document.forms["uploadRealartImgForm"].action = baseUrl + "uploadRealartImg.do?token=" + token;
    document.forms["uploadRealartImgForm"].submit();
}