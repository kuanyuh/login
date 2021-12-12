function update() {
    //创建异步对象
    var xmlHttp = new XMLHttpRequest();
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var age = document.getElementById("age").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var password = document.getElementById("password").value;
    var param = "name=" + name + "&email=" + email + "&age=" + age + "&phoneNumber=" + phoneNumber + "&password=" + password;
    //绑定事件
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4 && xmlHttp.status==200){

            if (confirm("确认修改？")==true){
                var jsonData = xmlHttp.responseText;
                //将json格式字符串转为json object对象
                var jsonObj = eval("(" + jsonData + ")")
                //处理结果函数
                callback(jsonObj);
                alert("修改成功");
            }
        }
    }

    xmlHttp.open("POST","EditServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}

function callback(jsonObj){
    document.getElementById("name").setAttribute("placeholder", jsonObj.name);
    document.getElementById("name").value="";

    document.getElementById("email").setAttribute("placeholder", jsonObj.email);
    document.getElementById("email").value="";

    document.getElementById("age").setAttribute("placeholder", jsonObj.age);
    document.getElementById("age").value="";

    document.getElementById("phoneNumber").setAttribute("placeholder", jsonObj.phoneNumber);
    document.getElementById("phoneNumber").value="";

    // document.getElementById("password").setAttribute("placeholder", jsonObj.password);
    document.getElementById("password").value="";
}