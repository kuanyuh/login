function next() {
    document.getElementById("emailVerify").style.display='none';
    document.getElementById("passwordReset").style.display='block';
}

function emailConfirm() {
    //创建异步对象
    var xmlHttp = new XMLHttpRequest();
    var email = document.getElementById("email").value;
    var verify = document.getElementById("verify").value;

    var param = "way=nextStep" + "&email=" + email;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data = xmlHttp.responseText;
            if(data=="email_exist"){
                document.getElementById("err_verify").innerHTML="邮箱已存在";
            }else if(data=="next"){
                alert("成功");
                next();
            }else if (data=="err"){
                alert("错误");
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
    var param = "way=register" + "&passwd=" + passwd + "&confirm=" + confirm;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data = xmlHttp.responseText;
            if(data=="OK"){
                alert("注册成功");
                window.location.href="new_login.html"
            }
        }
    }
    xmlHttp.open("POST","RegisterServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}