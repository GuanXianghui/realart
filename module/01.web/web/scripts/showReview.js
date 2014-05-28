/**
 * 初始化
 */
$(document).ready(function() {
    uParse("#editor", {rootPath: baseUrl + '/ueditor/'});
});

/**
 * 修改置顶推荐
 * @param value
 */
function zdtj(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("zdtj");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改置顶推荐图
 * @param value
 */
function zdtjTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("zdtjTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改美文共赏
 * @param value
 */
function mwgs(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("mwgs");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改美文共赏图
 * @param value
 */
function mwgsTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("mwgsTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改本站推荐
 * @param value
 */
function bzyc(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("bzyc");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改本站推荐图
 * @param value
 */
function bzycTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("bzycTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}

/**
 * 修改会员专栏图
 * @param value
 */
function hyzlTop(value){
    $("#updateLocationBitReviewId").val(reviewId);
    $("#updateLocationBitType").val("hyzlTop");
    $("#updateLocationBitValue").val(value);
    document.forms["updateLocationBitForm"].submit();
}
