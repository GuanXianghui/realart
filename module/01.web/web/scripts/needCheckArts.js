//艺术品Json数组
var artArray = new Array();

/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
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
    $("#name").html("<input type=\"text\" name=\"name\" value=\"" + art["name"] + "\">");
    $("#state").html(art["stateDesc"]);
    $("#photo").html("<img src=\"/" + art["photo"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo\">");
    $("#photo0").html("<img src=\"/" + art["photo0"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo0\">");
    $("#photo1").html("<img src=\"/" + art["photo1"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo1\">");
    $("#photo2").html("<img src=\"/" + art["photo2"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo2\">");
    $("#photo3").html("<img src=\"/" + art["photo3"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo3\">");
    $("#photo4").html("<img src=\"/" + art["photo4"] + "\" height=\"100\">" + "<input type=\"file\" name=\"photo4\">");
    $("#gongyi").html("<input type=\"text\" name=\"gongyi\" value=\"" + art["gongyi"] + "\">");
    $("#type").html("<input type=\"text\" name=\"type\" value=\"" + art["type"] + "\">");
    $("#length").html("<input type=\"text\" name=\"length\" value=\"" + art["length"] + "\">");
    $("#width").html("<input type=\"text\" name=\"width\" value=\"" + art["width"] + "\">");
    $("#height").html("<input type=\"text\" name=\"height\" value=\"" + art["height"] + "\">");
    $("#build_date").html("<input type=\"text\" name=\"buildDate\" value=\"" + art["buildDate"] + "\">");
    $("#title").html("<input type=\"text\" name=\"title\" value=\"" + art["title"] + "\">");
    $("#introduction").html("<input type=\"text\" name=\"introduction\" value=\"" + art["introduction"] + "\">");
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
 * 修改艺术品
 */
function updateArt(){
    $("#updateArtId").val(chooseArtId);
    document.forms["updateArtForm"].submit();
}