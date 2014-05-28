/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
});

//删除
function deleteRURI(index){
    $("#delete_ruri").val($("#ruri_name_" + index).val());
    document.forms["deleteRURIForm"].submit();
}

//新增
function addRURI(){
    //数量加1
    ruriCount ++;
    var html = $("#ruri_table").html();
    var trHtml = "<tr>"+
        "<td>"+
        "<input class=\"text-input large-input\" type=\"text\" id=\"ruri_name_" + ruriCount + "\">"+
        "</td>"+
        "<td>"+
        "<select class=\"text-input large-input\" id=\"ruri_need_" + ruriCount + "\">"+
        "<option value=\"1\">必输</option>"+
        "<option value=\"2\">非必输</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "<select class=\"text-input large-input\" id=\"ruri_type_" + ruriCount + "\">"+
        "<option value=\"1\">字符串</option>"+
        "<option value=\"2\">图片/文件</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "请保存后再删除" +
        "</td>"+
        "</tr>";
    html = html + trHtml;
    $("#ruri_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}

//初始化项
var INIT_ITEMS = new Array("专栏用户名","身份证姓名","密码","确认密码","专栏压题图","个人照片");

//保存
function saveRURI(){
    var ruri = EMPTY;
    for(var i=0;i<ruriCount;i++){
        if(ruri != EMPTY){
            ruri += SYMBOL_COMMA;
        }
        if(trim($("#ruri_name_" + (i+1)).val()) == EMPTY){
            showAttention("不能输入空字串");
            return;
        }
        for(var j=0;j<INIT_ITEMS.length;j++){
            if(INIT_ITEMS[j] == trim($("#ruri_name_" + (i+1)).val())){
                showAttention("已初始化过输入项[" + INIT_ITEMS[j] + "]，请更换输入项！");
                return;
            }
        }
        for(var j=i+2;j<ruriCount;j++){
            if(trim($("#ruri_name_" + (j)).val()) == trim($("#ruri_name_" + (i+1)).val())){
                showAttention("输入项[" + trim($("#ruri_name_" + (j)).val()) + "]已重复！");
                return;
            }
        }
        ruri += "{\"name\":\"" + trim($("#ruri_name_" + (i+1)).val()) + "\",\"need\":\"" +
            $("#ruri_need_" + (i+1)).val() + "\",\"type\":\"" + $("#ruri_type_" + (i+1)).val() + "\"}";
    }
    ruri = "[" + ruri + "]";
    $("#save_ruri").val(ruri);
    document.forms["saveRURIForm"].submit();
}