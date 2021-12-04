function next() {
    document.getElementById("emailVerify").style.display='none';
    document.getElementById("passwordReset").style.display='block';
}

function emailConfirm() {
    //创建异步对象
    var xmlHttp = new XMLHttpRequest();
    var email = document.getElementById("email").value;
    var verify = document.getElementById("verify").value;

    var param = "way=email" + "&email=" + email + "&verify=" + verify;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data = xmlHttp.responseText;
            if(data=="ERR_EMAIL"){
                document.getElementById("err_email").innerHTML="邮箱不存在";
            }else if(data=="ERR_VERIFY"){
                document.getElementById("err_verify").innerHTML="验证码错误";
            }else if(data=="OK"){
                alert("okk1");
                next();
            }
        }
    }
    xmlHttp.open("POST","LoginServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}

function resetPw() {
    //创建异步对象
    var xmlHttp = new XMLHttpRequest();
    var passwd = document.getElementById("passwd").value;
    var confirm = document.getElementById("confirm").value;
    if (passwd!=confirm){
        alert("两次输入密码不一致："+"pw1:"+passwd+"pw2"+confirm);
        return;
    }
    var param = "way=email" + "&passwd=" + passwd + "&confirm=" + confirm;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data = xmlHttp.responseText;
            if(data=="OK"){
                alert("修改成功");
                window.location.href="new_login.html"
            }
        }
    }
    xmlHttp.open("POST","RegisterServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}

function register() {
    //创建异步对象
    var xmlHttp = new XMLHttpRequest();
    var passwd = document.getElementById("passwd").value;
    var confirm = document.getElementById("confirm").value;
    var param = "way=email" + "&passwd=" + passwd + "&confirm=" + confirm;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data = xmlHttp.responseText;
            if(data=="OK"){
                alert("注册");
                window.location.href="new_login.html"
            }
        }
    }
    xmlHttp.open("POST","RegisterServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}