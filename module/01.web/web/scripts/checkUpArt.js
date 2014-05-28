//�û�Json����
var userArray = new Array();
//����ƷJson����
var artArray = new Array();

/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        showInformation(message);
    }
    //�����û�json��
    processUserWithJson();
    //��������Ʒjson��
    processArtWithJson();
});

/**
 * �����û�json��
 */
function processUserWithJson() {
    //json��תjson����
    if(userJsonStr != EMPTY) {
        var array = userJsonStr.split(SYMBOL_BIT_AND);
        for(var i=0;i<array.length;i++) {
            userArray[userArray.length] = eval("(" + array[i] + ")");
        }
    }
}

/**
 * �����û�json��
 */
function processArtWithJson() {
    //json��תjson����
    if(artJsonStr != EMPTY) {
        var array = artJsonStr.split(SYMBOL_BIT_AND);
        for(var i=0;i<array.length;i++) {
            artArray[artArray.length] = eval("(" + array[i] + ")");
        }
    }
}

/**
 * ѡ������Ʒ
 * @param artId
 */
function chooseArt(artId){
    //ѡ������ƷId
    chooseArtId = artId;
    //����id������Ʒ
    var art = getArtById(artId);
    //����id���û�
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
 * ����id������Ʒ
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
 * ����id���û�
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
 * ���ͨ��
 */
function checkSuccess(){
    if(chooseArtId == 0){
        showAttention("��ѡ������Ʒ");
        return;
    }
    location.href = "checkUpArt.jsp?artId=" + chooseArtId + "&state=" + 1;
}

/**
 * ��˲�ͨ��
 */
function checkFailed(){
    if(chooseArtId == 0){
        showAttention("��ѡ������Ʒ");
        return;
    }
    location.href = "checkUpArt.jsp?artId=" + chooseArtId + "&state=" + 2 + "&reason=" + $("#reason").val();
}