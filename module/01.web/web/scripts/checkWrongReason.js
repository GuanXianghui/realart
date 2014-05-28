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
function deleteCWR(index){
    $("#delete_cwr").val(index);
    document.forms["deleteCWRForm"].submit();
}

//新增
function addCWR(){
    //数量加1
    cwrCount ++;
    var html = $("#cwr_table").html();
    var trHtml = "<tr>"+
        "<td>"+
        cwrCount +
        "</td>"+
        "<td>"+
        "<input class=\"text-input large-input\" type=\"text\" id=\"cwr_" + cwrCount + "\">"+
        "</td>"+
        "<td>"+
        "请保存后再删除" +
        "</td>"+
        "</tr>";
    html = html + trHtml;
    $("#cwr_table").html(html);
    $('tbody tr:even').addClass("alt-row");
}

//保存
function saveCWR(){
    var cwr = EMPTY;
    for(var i=0;i<cwrCount;i++){
        if(cwr != EMPTY){
            cwr += SYMBOL_STAND;
        }
        if(trim($("#cwr_" + (i+1)).val()) == EMPTY){
            showAttention("不能输入空字串");
            return;
        }
        cwr += trim($("#cwr_" + (i+1)).val());
    }
    $("#save_cwr").val(cwr);
    document.forms["saveCWRForm"].submit();
}