//ueditor�༭��
var editor;

/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * ��ʼ��
 */
$(document).ready(function() {
    //ʵ�����༭��
    //����ʹ�ù�������getEditor���������ñ༭��ʵ���������ĳ���հ������øñ༭����ֱ�ӵ���UE.getEditor('editor')�����õ���ص�ʵ��
    editor = UE.getEditor('editor');
});

/**
 * ����
 */
function createReview(){
    var content = editor.getContent();
    document.getElementById("content").value = content;
    //�ύ���
    document.forms["createReviewForm"].submit();
}