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
    $("#delete_contact_guide_index").val(index);
    document.forms["deleteContactGuideForm"].submit();
}

//新增导航
function addGuide(){
    if(guideCount >= maxCount){
        showAttention("联系方法最多" + maxCount + "个，不能再多了哦~");
        return;
    }
    //导航数量加1
    guideCount ++;
    $("#guideCount").val(guideCount);
    var html = $("#guide_table").html();
    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"name" + guideCount + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"text" + guideCount + "\">" +
        "</td>" +
        "<td>" +
        "<input type=\"file\" name=\"ico" + guideCount + "\">" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#guide_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}
