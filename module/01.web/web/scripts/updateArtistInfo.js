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
function updateArtistInfo(){
    var certName = $("#cert_name").val();
    var certNo = $("#cert_no").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    document.forms["updateArtistInfoForm"].submit();
}