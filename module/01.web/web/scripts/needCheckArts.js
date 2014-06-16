//艺术品Json数组
var artArray = new Array();

/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }

    //处理艺术品json串
    processArtWithJson();
});

/**
 * 处理用户json串
 */
function processArtWithJson() {
    //json串转json数组
    if(artJsonStr != EMPTY) {
        var array = artJsonStr.split(SYMBOL_BIT_AND);
        for(var i=0;i<array.length;i++) {
            artArray[artArray.length] = eval("(" + array[i] + ")");
        }
    }
}

/**
 * 选择艺术品
 * @param artId
 */
function chooseArt(artId){
    //选择艺术品Id
    chooseArtId = artId;
    //根据id查艺术品
    var art = getArtById(artId);
    $("#name").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"name\" value=\"" + art["name"] + "\">");
    $("#state").html(art["stateDesc"]);
    $("#kind").html(getKind(art["kind"]));
    var html = EMPTY;
    if(art["photo"] != EMPTY){
        html = "<img src=\"/" + art["photo"] + "\" height=\"100\">";
    }
    $("#photo").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo\">");
    html = EMPTY;
    if(art["photo0"] != EMPTY){
        html = "<img src=\"/" + art["photo0"] + "\" height=\"100\">";
    }
    $("#photo0").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo0\">");
    html = EMPTY;
    if(art["photo1"] != EMPTY){
        html = "<img src=\"/" + art["photo1"] + "\" height=\"100\">";
    }
    $("#photo1").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo1\">");
    html = EMPTY;
    if(art["photo2"] != EMPTY){
        html = "<img src=\"/" + art["photo2"] + "\" height=\"100\">";
    }
    $("#photo2").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo2\">");
    html = EMPTY;
    if(art["photo3"] != EMPTY){
        html = "<img src=\"/" + art["photo3"] + "\" height=\"100\">";
    }
    $("#photo3").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo3\">");
    html = EMPTY;
    if(art["photo4"] != EMPTY){
        html = "<img src=\"/" + art["photo4"] + "\" height=\"100\">";
    }
    $("#photo4").html(html + "<input class=\"text-input between-medium-large-input\" type=\"file\" name=\"photo4\">");
    $("#gongyi").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"gongyi\" value=\"" + art["gongyi"] + "\">");
    $("#type").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"type\" value=\"" + art["type"] + "\">");
    $("#length").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"length\" value=\"" + art["length"] + "\">");
    $("#width").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"width\" value=\"" + art["width"] + "\">");
    $("#height").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"height\" value=\"" + art["height"] + "\">");
    $("#build_date").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"buildDate\" value=\"" + art["buildDate"] + "\">");
    $("#title").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"title\" value=\"" + art["title"] + "\">");
    $("#introduction").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"introduction\" value=\"" + art["introduction"] + "\">");
}

/**
 * 根据id查艺术品
 * @param userId
 */
function getArtById(artId){
    for(var i=0;i<artArray.length;i++){
        if(artArray[i]["id"] == artId){
            return artArray[i];
        }
    }
    return null;
}

/**
 * 返回分类
 * @param kind
 */
function getKind(kind){
    var html = EMPTY;
    html += "<select type='text-input between-medium-large-input' name='kind'>" +
        "<option value=''";
    if(kind == EMPTY){
        html += " SELECTED";
    }
    html += ">暂不分类</option>";
    if(artKinds != EMPTY){
        var kindArray = artKinds.split(SYMBOL_COMMA);
        for(var i=0;i<kindArray.length;i++){
            html +=  "<option value='" + kindArray[i] + "'";
            if(kind == kindArray[i]){
                html += " SELECTED";
            }
            html +=  ">" + kindArray[i] + "</option>";
        }
    }
    html += "</select>";
    return html;
}

/**
 * 修改艺术品
 */
function updateArt(){
    $("#updateArtId").val(chooseArtId);
    document.forms["updateArtForm"].submit();
}