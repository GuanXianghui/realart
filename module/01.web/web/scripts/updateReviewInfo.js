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
function updateReviewInfo(){
    document.forms["updateReviewInfoForm"].submit();
}