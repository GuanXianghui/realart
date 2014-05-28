/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
});

//删除导航
function deleteGuide(index){
    $("#delete_guide_index").val(index);
    document.forms["deleteGuideForm"].submit();
}

//新增导航
function addGuide(){
    //导航数量加1
    guideCount ++;
    var html = $("#guide_table").html();
    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" id=\"guide_name_" + guideCount + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" id=\"guide_url_" + guideCount + "\">" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#guide_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}

//保存导航
function saveGuide(){
    var guide = EMPTY;
    for(var i=0;i<guideCount;i++){
        if(guide != EMPTY){
            guide += SYMBOL_COMMA;
        }
        guide += "{\"name\":\"" + $("#guide_name_" + (i+1)).val() + "\",\"url\":\"" + $("#guide_url_" + (i+1)).val() + "\"}";
    }
    guide = "[" + guide + "]";
    $("#save_guide").val(guide);
    document.forms["saveGuideForm"].submit();
}