/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * 修改艺术家密码
 */
function updateReviewPassword(){
    var oldPassword = document.getElementById("old_password").value;
    var newPassword = document.getElementById("new_password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    if(newPassword == EMPTY){
        showAttention("请输入新密码");
        return;
    }
    if(confirmPassword == EMPTY){
        showAttention("请输入确认密码");
        return;
    }
    if(newPassword != confirmPassword){
        showAttention("两次密码输入不一致");
        return;
    }
    //md5签名
    var md5OldPwd = MD5(oldPassword + md5Key);
    document.getElementById("old_password").value = md5OldPwd;
    //md5签名
    var md5NewPassword = MD5(newPassword + md5Key);
    document.getElementById("new_password").value = md5NewPassword;
    //md5签名
    var md5ConfirmPassword = MD5(confirmPassword + md5Key);
    document.getElementById("confirm_password").value = md5ConfirmPassword;
    document.forms["updateReviewPasswordForm"].submit();
}