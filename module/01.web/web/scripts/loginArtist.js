/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
    }
});

/**
 * ��¼
 */
function loginArtist(){
    var password = document.getElementById("password").value;
    //md5ǩ��
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    document.forms["loginArtistForm"].submit();
}
