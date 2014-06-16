/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
});

/**
 * 修改艺术品分类
 * @param kindId
 */
function updateArtKind(kindId){
    var newArtKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入艺术品分类");
            return;
        }
        if(newArtKinds != EMPTY){
            newArtKinds += SYMBOL_COMMA;
        }
        newArtKinds += $("#kind" + (i+1)).val();
    }
    $("#artKinds").val(newArtKinds);
    document.forms["updateArtKindsForm"].submit();
}

/**
 * 删除艺术品分类
 * @param kindId
 */
function deleteArtKind(kindId){
    var newArtKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入艺术品分类");
            return;
        }
        if(kindId == "kind" + (i+1)){
            continue;
        }
        if(newArtKinds != EMPTY){
            newArtKinds += SYMBOL_COMMA;
        }
        newArtKinds += $("#kind" + (i+1)).val();
    }
    $("#artKinds").val(newArtKinds);
    document.forms["updateArtKindsForm"].submit();
}

/**
 * 新增艺术品分类
 * @param kindId
 */
function createArtKind(){
    if(EMPTY == $("#create_art_kind").val()){
        showAttention("请输入艺术品分类");
        return;
    }
    var newArtKinds = EMPTY;
    for(var i=0;i<kindCount;i++){
        if(EMPTY == $("#kind" + (i+1)).val()){
            showAttention("请输入艺术品分类");
            return;
        }
        if(newArtKinds != EMPTY){
            newArtKinds += SYMBOL_COMMA;
        }
        newArtKinds += $("#kind" + (i+1)).val();
    }
    if(newArtKinds != EMPTY){
        newArtKinds += SYMBOL_COMMA;
    }
    newArtKinds += $("#create_art_kind").val();
    $("#artKinds").val(newArtKinds);
    document.forms["updateArtKindsForm"].submit();
}