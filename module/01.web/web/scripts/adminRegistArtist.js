/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * ����רԱע��
 */
function registArtist(){
    var name = $("#name").val();
    var password = document.getElementById("password").value;
    //md5ǩ��
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    var confirmPassword = document.getElementById("confirm_password").value;
    //md5ǩ��
    var md5ConfirmPassword = MD5(confirmPassword + md5Key);
    document.getElementById("confirm_password").value = md5ConfirmPassword;
    document.forms["adminRegistArtistForm"].submit();
}