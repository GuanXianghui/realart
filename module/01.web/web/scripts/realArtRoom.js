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
function deleteRealArt(index){
    $("#delete_real_art_room_index").val(index);
    document.forms["deleteRealArtRoomForm"].submit();
}

//新增真艺展品
function addRealArt(){
    if(realArtCount >= maxCount){
        showAttention("真艺展品最多" + maxCount + "个，不能再多了哦~");
        return;
    }
    //导航数量加1
    realArtCount ++;
    $("#realArtCount").val(realArtCount);
    var html = $("#real_art_room_table").html();
    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"title" + realArtCount + "\">" +
        "</td>" +
        "<td>" +
        "<input type=\"file\" name=\"url" + realArtCount + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"href" + realArtCount + "\">" +
        "</td>" +
        "<td>" +
        "<select class=\"text-input large-input\" name=\"type" + realArtCount + "\">" +
        "<option value=\"1\">显示</option>" +
        "<option value=\"0\">不显示</option>" +
        "</select>" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#real_art_room_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}
