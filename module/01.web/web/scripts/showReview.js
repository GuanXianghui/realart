/**
 * ��ʼ��
 */
$(document).ready(function() {
    uParse("#editor", {rootPath: baseUrl + '/ueditor/'});
});

/**
 * �޸��ö��Ƽ�
 * @param value
 */
function zdtj(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("zdtj");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸��ö��Ƽ�ͼ
 * @param value
 */
function zdtjTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("zdtjTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸����Ĺ���
 * @param value
 */
function mwgs(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("mwgs");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸����Ĺ���ͼ
 * @param value
 */
function mwgsTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("mwgsTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸ı�վ�Ƽ�
 * @param value
 */
function bzyc(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("bzyc");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸ı�վ�Ƽ�ͼ
 * @param value
 */
function bzycTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("bzycTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * �޸Ļ�Աר��ͼ
 * @param value
 */
function hyzlTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("hyzlTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}
