/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
    }
});

/**
 * ����רԱע��
 */
function registReview(){
    var password = document.getElementById("password").value;
    //md5ǩ��
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    var confirmPassword = document.getElementById("confirm_password").value;
    //md5ǩ��
    var md5ConfirmPassword = MD5(confirmPassword + md5Key);
    document.getElementById("confirm_password").value = md5ConfirmPassword;
    document.forms["registReviewForm"].submit();
}