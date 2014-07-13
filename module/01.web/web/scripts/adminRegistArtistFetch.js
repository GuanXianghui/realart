/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * 批量注册艺术家
 */
function registArtistFetch(){
    document.forms["adminRegistArtistFetchForm"].submit();
}