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
function deleteFriendSite(index){
    $("#delete_friend_site_id").val(index);
    document.forms["deleteFriendSiteForm"].submit();
}

//新增真艺展品
function addFriendSite(){
    if(friendSiteCount >= maxCount){
        showAttention("真艺展品最多" + maxCount + "个，不能再多了哦~");
        return;
    }
    //导航数量加1
    friendSiteCount ++;
    $("#friendSiteCount").val(friendSiteCount);
    var html = $("#friend_site_list_table").html();

    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"name" + friendSiteCount + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"url" + friendSiteCount + "\">" +
        "</td>" +
        "<td>" +
        "<input type=\"file\" name=\"img" + friendSiteCount + "\">" +
        "</td>" +
        "<td>" +
        "<select class=\"text-input large-input\" name=\"dis" + friendSiteCount + "\">" +
        "<option value=\"1\">显示</option>" +
        "<option value=\"0\">不显示</option>" +
        "</select>" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#friend_site_list_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}
