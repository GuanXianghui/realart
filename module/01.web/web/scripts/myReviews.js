//评论Json数组
var reviewArray = new Array();
//ueditor编辑器
var editor;

/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    editor = UE.getEditor('content');
    //处理评论json串
    processReviewWithJson();
});

/**
 * 处理评论json串
 */
function processReviewWithJson() {
    //json串转json数组
    if(reviewJsonStr != EMPTY) {
        var array = reviewJsonStr.split(SYMBOL_BIT_AND);
        for(var i=0;i<array.length;i++) {
            reviewArray[reviewArray.length] = eval("(" + array[i] + ")");
        }
    }
}

/**
 * 选择艺术品
 * @param reviewId
 */
function chooseReview(reviewId){
    //选择评论Id
    chooseReviewId = reviewId;
    //根据id查艺术品
    var review = getReviewById(reviewId);
    $("#title").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"title\" value=\"" + review["title"] + "\">");
    $("#type").html(getType(review["type"]));
    var html = EMPTY;
    if(review["photo"] != EMPTY){
        html = "<img src=\"/" + review["photo"] + "\" height=\"100\">";
    }
    $("#photo").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo\">");
    editor.setContent(review["content"]);
}

/**
 * 根据id查评论
 * @param userId
 */
function getReviewById(reviewId){
    for(var i=0;i<reviewArray.length;i++){
        if(reviewArray[i]["id"] == reviewId){
            return reviewArray[i];
        }
    }
    return null;
}

/**
 * 返回分类
 * @param kind
 */
function getType(type){
    var html = EMPTY;
    html += "<select type='text-input between-medium-large-input' name='type'>" +
        "<option value=''";
    if(type == EMPTY){
        html += " SELECTED";
    }
    html += ">暂不分类</option>";
    if(reviewKinds != EMPTY){
        var kindArray = reviewKinds.split(SYMBOL_COMMA);
        for(var i=0;i<kindArray.length;i++){
            html +=  "<option value='" + kindArray[i] + "'";
            if(type == kindArray[i]){
                html += " SELECTED";
            }
            html +=  ">" + kindArray[i] + "</option>";
        }
    }
    html += "</select>";
    return html;
}

/**
 * 修改艺术品
 */
function updateReview(){
    if(chooseReviewId == 0){
        showAttention("请选择评论");
        return;
    }
    $("#content_text").val(editor.getContent());
    $("#updateReviewId").val(chooseReviewId);
    document.forms["updateReviewForm"].submit();
}