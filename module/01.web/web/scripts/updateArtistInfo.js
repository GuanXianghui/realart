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
function updateArtistInfo(){
    var certName = $("#cert_name").val();
    var certNo = $("#cert_no").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    document.forms["updateArtistInfoForm"].submit();
}