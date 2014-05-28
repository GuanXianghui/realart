/**
 * 修改艺术专栏图
 * @param value
 */
function yszlTop(value){
    $("#updateArtLocationBitArtId").val(artId);
    $("#updateArtLocationBitType").val("yszlTop");
    $("#updateArtLocationBitValue").val(value);
    document.forms["updateArtLocationBitForm"].submit();
}
