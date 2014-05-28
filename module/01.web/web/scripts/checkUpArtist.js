//用户Json数组
var userArray = new Array();

/**
 * 初始化
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }

    //处理用户json串
    processUserWithJson();

    //展示字母和所有用户
    var letterDivValue = EMPTY;
    var detailDivValue = EMPTY;
    for(var i=0;i<BIG_LETTERS.length;i++){
        var letter = BIG_LETTERS[i];
        var hasUser = false;//当前字母有对应用户
        var letterValue = EMPTY;
        for(var j=0;j<userArray.length;j++){
            var certName = userArray[j]["certName"];
            var firstLetter = getFirstPinYin(certName);
            if(letter == firstLetter){
                hasUser = true;
                if(letterValue == EMPTY){
                    letterValue += "<a name='L_" + letter + "'><span style='font-size: 20px'>" + letter + "</span></a>";
                }
                letterValue += "&nbsp;&nbsp;&nbsp;"
                letterValue += "<a href='javascript:chooseUser(" + userArray[j]["id"] + ")'>" + userArray[j]["certName"] + "</a>";
            }
        }
        if(hasUser){
            if(letterDivValue != EMPTY){
                letterDivValue += "&nbsp;&nbsp;&nbsp;"
            }
            letterDivValue += "<a href='#L_" + letter + "'>" + letter + "</a>";
            detailDivValue += "<tr><td>" + letterValue + "</td></tr>";
        }
    }
    if(letterDivValue == EMPTY){
        detailDivValue = "暂无待审核艺术家";
    } else {
        detailDivValue = "<table><tr><td>" + letterDivValue + "</td></tr>" + detailDivValue + "</table>"
    }
    $("#detail_div").html(detailDivValue);
    $('tbody tr:even').addClass("alt-row");
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
 * 选择用户
 * @param userId
 */
function chooseUser(userId){
    //选择用户Id
    chooseUserId = userId;
    //根据id查用户
    var user = getUserById(userId);
    $("#name").html(user["name"]);
    $("#state").html(user["stateDesc"]);
    $("#cert_name").html(user["certName"]);
    $("#title_photo").html("<img src=\"/" + user["titlePhoto"] + "\" height=\"100\">");
    $("#head_photo").html("<img src=\"/" + user["headPhoto"] + "\" height=\"100\">");
    var info = eval("(" + user["info"] + ")");
    for(var i=0;i<itemCount;i++){
        var type = $("#item_name" + (i+1)).attr("name");
        var html = EMPTY;
        if(type == 1){
            html = info[$("#item_name" + (i+1)).html()];
        } else if(type == 2){
            html = "<img src=\"/" + info[$("#item_name" + (i+1)).html()] + "\" height=\"100\">";
        }
        $("#item" + (i+1)).html(html);
    }
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
    if(chooseUserId == 0){
        showAttention("请选择用户");
        return;
    }
    location.href = "checkUpArtist.jsp?userId=" + chooseUserId + "&state=" + 1;
}

/**
 * 审核不通过
 */
function checkFailed(){
    if(chooseUserId == 0){
        showAttention("请选择用户");
        return;
    }
    location.href = "checkUpArtist.jsp?userId=" + chooseUserId + "&state=" + 2 + "&reason=" + $("#reason").val();
}