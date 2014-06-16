/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * 评论专员注册
 */
function updateReviewInfo(){
    document.forms["updateReviewInfoForm"].submit();
}