//�û�Json����
var userArray = new Array();

/**
 * ��ʼ��
 */
$(document).ready(function() {
    //�����û�json��
    processUserWithJson();

    //չʾ��ĸ�������û�
    var letterDivValue = EMPTY;
    var detailDivValue = EMPTY;
    for(var i=0;i<BIG_LETTERS.length;i++){
        var letter = BIG_LETTERS[i];
        var hasUser = false;//��ǰ��ĸ�ж�Ӧ�û�
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
                letterValue += "<a href='artistUser.jsp?name=" + userArray[j]["name"] + "'>" + userArray[j]["certName"] + "</a>";
            }
        }
        if(hasUser){
            if(letterDivValue != EMPTY){
                letterDivValue += "&nbsp;&nbsp;&nbsp;"
            }
            letterDivValue += "<a href='#L_" + letter + "'>" + letter + "</a>";
            detailDivValue += "<hr/>" + letterValue;
        }
    }
    $("#letter_div").html(letterDivValue);
    $("#detail_div").html(detailDivValue);
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