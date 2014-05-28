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
function uploadArt(){
    document.forms["uploadArtForm"].submit();
}