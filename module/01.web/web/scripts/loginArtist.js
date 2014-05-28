/**
 * ³õÊ¼»¯
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
    }
});

/**
 * µÇÂ¼
 */
function loginArtist(){
    var password = document.getElementById("password").value;
    //md5Ç©Ãû
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    document.forms["loginArtistForm"].submit();
}
