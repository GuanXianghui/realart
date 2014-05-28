/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
    }
});

/**
 * 评论专员注册
 */
function updateArtistPassword(){
    var oldPassword = document.getElementById("old_password").value;
    //md5签名
    var md5OldPwd = MD5(oldPassword + md5Key);
    document.getElementById("old_password").value = md5OldPwd;
    var newPassword = document.getElementById("new_password").value;
    //md5签名
    var md5NewPassword = MD5(newPassword + md5Key);
    document.getElementById("new_password").value = md5NewPassword;
    var confirmPassword = document.getElementById("confirm_password").value;
    //md5签名
    var md5ConfirmPassword = MD5(confirmPassword + md5Key);
    document.getElementById("confirm_password").value = md5ConfirmPassword;
    document.forms["updateArtistPasswordForm"].submit();
}