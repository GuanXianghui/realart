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
    $("#name").html("<input class=\"text-input between-medium-large-input\" type=\"text\" name=\"name\" value=\"" + art["name"] + "\">");
    $("#state").html(art["stateDesc"]);
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