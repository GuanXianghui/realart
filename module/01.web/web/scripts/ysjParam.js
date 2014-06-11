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
function deleteYsjParam(index){
    $("#delete_ysj_param_id").val(index);
    document.forms["deleteYsjParamForm"].submit();
}

//新增真艺展品
function addYsjParam(){
    if(ysjParamCount >= maxCount){
        showAttention("真艺展品最多" + maxCount + "个，不能再多了哦~");
        return;
    }
    //导航数量加1
    ysjParamCount ++;
    $("#ysjParamCount").val(ysjParamCount);
    var html = $("#ysj_param_table").html();

    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"name" + ysjParamCount + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"url" + ysjParamCount + "\">" +
        "</td>" +
        "<td>" +
        "<input type=\"file\" name=\"img" + ysjParamCount + "\">" +
        "</td>" +
        "<td>" +
        "<select class=\"text-input large-input\" name=\"dis" + ysjParamCount + "\">" +
        "<option value=\"1\">显示</option>" +
        "<option value=\"0\">不显示</option>" +
        "</select>" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#ysj_param_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}
