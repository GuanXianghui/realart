//����ƷJson����
var artArray = new Array();

/**
 * ��ʼ��
 */
$(document).ready(function() {
    if(message != EMPTY){
        alert(message);
    }

    //��������Ʒjson��
    processArtWithJson();
});

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
 * �޸�����Ʒ
 */
function updateArt(){
    $("#updateArtId").val(chooseArtId);
    document.forms["updateArtForm"].submit();
}