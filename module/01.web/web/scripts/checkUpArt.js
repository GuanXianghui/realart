//用户Json数组
var userArray = new Array();
//艺术品Json数组
var artArray = new Array();

/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
    //处理用户json串
    processUserWithJson();
    //处理艺术品json串
    processArtWithJson();
});

/**
 * 处理用户json串
 */
function processUserWithJson() {
    //json串转json数组
    if(userJsonStr != EMPTY) {
        var array = userJsonStr.split(SYMBOL_BIT_AND);
        for(var i=0;i<array.length;i++) {
            userArray[userArray.length] = eval("(" + array[i] + ")");
        }
    }
}

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
    //根据id查用户
    var user = getUserById(art["userId"]);
    $("#userName").html(user["name"]);
    $("#name").html(art["name"]);
    $("#state").html(art["stateDesc"]);
    if(art["photo"] != EMPTY){
        $("#photo").html("<img src=\"/" + art["photo"] + "\" height=\"100\">");
    }
    if(art["photo0"] != EMPTY){
        $("#photo0").html("<img src=\"/" + art["photo0"] + "\" height=\"100\">");
    }
    if(art["photo1"] != EMPTY){
        $("#photo1").html("<img src=\"/" + art["photo1"] + "\" height=\"100\">");
    }
    if(art["photo2"] != EMPTY){
        $("#photo2").html("<img src=\"/" + art["photo2"] + "\" height=\"100\">");
    }
    if(art["photo3"] != EMPTY){
        $("#photo3").html("<img src=\"/" + art["photo3"] + "\" height=\"100\">");
    }
    if(art["photo4"] != EMPTY){
        $("#photo4").html("<img src=\"/" + art["photo4"] + "\" height=\"100\">");
    }
    $("#gongyi").html(art["gongyi"]);
    $("#type").html(art["type"]);
    $("#length").html(art["length"]);
    $("#width").html(art["width"]);
    $("#height").html(art["height"]);
    $("#build_date").html(art["buildDate"]);
    $("#title").html(art["title"]);
    $("#introduction").html(art["introduction"]);
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
 * 根据id查用户
 * @param userId
 */
function getUserById(userId){
    for(var i=0;i<userArray.length;i++){
        if(userArray[i]["id"] == userId){
            return userArray[i];
        }
    }
    return null;
}

/**
 * 审核通过
 */
function checkSuccess(){
    if(chooseArtId == 0){
        showAttention("请选择艺术品");
        return;
    }
    location.href = "checkUpArt.jsp?artId=" + chooseArtId + "&state=" + 1;
}

/**
 * 审核不通过
 */
function checkFailed(){
    if(chooseArtId == 0){
        showAttention("请选择艺术品");
        return;
    }
    location.href = "checkUpArt.jsp?artId=" + chooseArtId + "&state=" + 2 + "&reason=" + $("#reason").val();
}