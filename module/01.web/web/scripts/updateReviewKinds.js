/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * 修改评论分类
 * @param kindId
 */
function updateReviewKind(kindId){
    var newReviewKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入评论分类");
            return;
        }
        if(newReviewKinds != EMPTY){
            newReviewKinds += SYMBOL_COMMA;
        }
        newReviewKinds += $("#kind" + (i+1)).val();
    }
    $("#reviewKinds").val(newReviewKinds);
    document.forms["updateReviewKindsForm"].submit();
}

/**
 * 删除评论分类
 * @param kindId
 */
function deleteReviewKind(kindId){
    var newReviewKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入评论分类");
            return;
        }
        if(kindId == "kind" + (i+1)){
            continue;
        }
        if(newReviewKinds != EMPTY){
            newReviewKinds += SYMBOL_COMMA;
        }
        newReviewKinds += $("#kind" + (i+1)).val();
    }
    $("#reviewKinds").val(newReviewKinds);
    document.forms["updateReviewKindsForm"].submit();
}

/**
 * 新增评论分类
 * @param kindId
 */
function createReviewKind(){
    if(EMPTY == $("#create_review_kind").val()){
        showAttention("请输入评论分类");
        return;
    }
    var newReviewKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入评论分类");
            return;
        }
        if(newReviewKinds != EMPTY){
            newReviewKinds += SYMBOL_COMMA;
        }
        newReviewKinds += $("#kind" + (i+1)).val();
    }
    if(newReviewKinds != EMPTY){
        newReviewKinds += SYMBOL_COMMA;
    }
    newReviewKinds += $("#create_review_kind").val();
    $("#reviewKinds").val(newReviewKinds);
    document.forms["updateReviewKindsForm"].submit();
}