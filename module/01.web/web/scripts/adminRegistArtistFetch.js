/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * ����ע��������
 */
function registArtistFetch(){
    document.forms["adminRegistArtistFetchForm"].submit();
}