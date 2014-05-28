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
function deleteAURI(index){
    $("#delete_auri").val($("#auri_name_" + index).val());
    document.forms["deleteAURIForm"].submit();
}

//新增
function addAURI(){
    //数量加1
    auriCount ++;
    var html = $("#auri_table").html();
    var trHtml = "<tr>"+
        "<td>"+
        "<input class=\"text-input large-input\" type=\"text\" id=\"auri_name_" + auriCount + "\">"+
        "</td>"+
        "<td>"+
        "<select class=\"text-input large-input\" id=\"auri_need_" + auriCount + "\">"+
        "<option value=\"1\">必输</option>"+
        "<option value=\"2\">非必输</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "<select class=\"text-input large-input\" id=\"auri_type_" + auriCount + "\">"+
        "<option value=\"1\">字符串</option>"+
        "<option value=\"2\">图片/文件</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "请保存后再删除" +
        "</td>"+
        "</tr>";
    html = html + trHtml;
    $("#auri_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}

//初始化项
var INIT_ITEMS = new Array("专栏用户名","身份证姓名","密码","确认密码","专栏压题图","个人照片");

//保存
function saveAURI(){
    var auri = EMPTY;
    for(var i=0;i<auriCount;i++){
        if(auri != EMPTY){
            auri += SYMBOL_COMMA;
        }
        if(trim($("#auri_name_" + (i+1)).val()) == EMPTY){
            showAttention("不能输入空字串");
            return;
        }
        for(var j=0;j<INIT_ITEMS.length;j++){
            if(INIT_ITEMS[j] == trim($("#auri_name_" + (i+1)).val())){
                showAttention("已初始化过输入项[" + INIT_ITEMS[j] + "]，请更换输入项！");
                return;
            }
        }
        for(var j=i+2;j<auriCount;j++){
            if(trim($("#auri_name_" + (j)).val()) == trim($("#auri_name_" + (i+1)).val())){
                showAttention("输入项[" + trim($("#auri_name_" + (j)).val()) + "]已重复！");
                return;
            }
        }
        auri += "{\"name\":\"" + trim($("#auri_name_" + (i+1)).val()) + "\",\"need\":\"" +
            $("#auri_need_" + (i+1)).val() + "\",\"type\":\"" + $("#auri_type_" + (i+1)).val() + "\"}";
    }
    auri = "[" + auri + "]";
    $("#save_auri").val(auri);
    document.forms["saveAURIForm"].submit();
}