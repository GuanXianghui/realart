/**
 * 初始化
 */
$(document).ready(function() {
    //不知道为毛不显示
});

/**
* 按钮监听
*/
function keyPress(e){
    if( 13 == e.keyCode){
        resetPassword();
    }
}

/**
* 重置密码
*/
function resetPassword(){
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    var securityCode = document.getElementById("securityCode").value;
    //判非空
    if (password == EMPTY) {
        showAttention("请输入密码!");
        return;
    }
    if (confirmPassword == EMPTY) {
        showAttention("请输入确认密码!");
        return;
    }
    if (password != confirmPassword){
        showAttention("两次密码不一致!");
        return;
    }
    if (securityCode == EMPTY) {
        showAttention("请输入验证码!");
        return;
    }
    //md5签名
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    //md5签名
    var md5ConfirmPassword = MD5(confirmPassword + md5Key);
    document.getElementById("confirm_password").value = md5ConfirmPassword;
    document.forms["resetPasswordForm"].submit();
}
