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
function loginReview(){
    var password = document.getElementById("password").value;
    //md5ǩ��
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;
    document.forms["loginReviewForm"].submit();
}

/**
 * ע��
 */
function registReview(){
    location.href = "registReview.jsp";
}