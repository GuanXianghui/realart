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
function deleteItemList(id, index){
    $("#delete_item_list_id").val(id);
    $("#delete_item_list_index").val(index);
    document.forms["deleteItemListForm"].submit();
}

//新增真艺展品
function addItemList(index){
    var size = getSizeByIndex(index);
    if(size >= maxCount){
        showAttention("八框内容最多" + maxCount + "个，不能再多了哦~");
        return;
    }

    //导航数量加1
    addSizeByIndex(index);
    size = getSizeByIndex(index);

    $("#size" + index).val(size);
    var html = $("#item_list_table" + index).html();

    var trHtml = "<tr>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"content" + size + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"title" + size + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"alt" + size + "\">" +
        "</td>" +
        "<td>" +
        "<input type=\"file\" name=\"imgUrl" + size + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"itemUrl" + size + "\">" +
        "</td>" +
        "<td>" +
        "<input class=\"text-input large-input\" type=\"text\" name=\"btnTitle" + size + "\">" +
        "</td>" +
        "<td>" +
        "请保存后删除" +
        "</td>" +
        "</tr>";
    html = html + trHtml;
    $("#item_list_table" + index).html(html);
    $('tbody tr:even').addClass("alt-row");
}

/**
 * 根据位置索引得到size
 * @param index
 * @return {*}
 */
function getSizeByIndex(index){
    if(index == 1){
        return size1;
    }
    if(index == 2){
        return size2;
    }
    if(index == 3){
        return size3;
    }
    if(index == 4){
        return size4;
    }
    if(index == 5){
        return size5;
    }
    if(index == 6){
        return size6;
    }
    if(index == 7){
        return size7;
    }
    if(index == 8){
        return size8;
    }
    return 0;
}

/**
 * 根据位置索引得到size 再加1
 * @param index
 * @return {*}
 */
function addSizeByIndex(index){
    if(index == 1){
        size1++;
    }
    if(index == 2){
        size2++;
    }
    if(index == 3){
        size3++;
    }
    if(index == 4){
        size4++;
    }
    if(index == 5){
        size5++;
    }
    if(index == 6){
        size6++;
    }
    if(index == 7){
        size7++;
    }
    if(index == 8){
        size8++;
    }
}