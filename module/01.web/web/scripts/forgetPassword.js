/**
 * 初始化
 */
$(document).ready(function() {
    //不知道为毛不显示
});

/**
* 按钮监听
*/
function keyPress(e){
    if( 13 == e.keyCode){
        forgetPassword();
    }
}

/**
* 登陆
*/
function forgetPassword(){
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var securityCode = document.getElementById("securityCode").value;
    //判非空
    if (name == EMPTY) {
        showAttention("请输入用户名!");
        return;
    }
    if (email == EMPTY) {
        showAttention("请输入邮箱!");
        return;
    }
    if (securityCode == EMPTY) {
        showAttention("请输入验证码!");
        return;
    }

    //ajax
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "forgetPassword.do",
        data:"name=" + name + "&email=" + email + "&securityCode=" + securityCode + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    $("#init_div").css("display", "none");
                    $("#success_p").css("display", "block");
                    showSuccess(data["message"]);
                }
                if(true == data["hasNewToken"]){
                    token = data["token"];
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}
