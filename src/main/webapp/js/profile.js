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
        alert(xmlHttp.readyState +" "+ xmlHttp.status);
        if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var jsonData = xmlHttp.responseText;
            //将json格式字符串转为json object对象
            var jsonObj = eval("(" + jsonData + ")")
            //处理结果函数
            callback(jsonObj);
        }
    }

    xmlHttp.open("POST","EditServlet?"+param, true);
    //发送异步请求
    xmlHttp.send();
}

function callback(jsonObj){
    document.getElementById("name").setAttribute("placeholder", jsonObj.name);
    alert(jsonObj.name);
    document.getElementById("email").setAttribute("placeholder", jsonObj.email);
    alert(jsonObj.email);
    document.getElementById("age").setAttribute("placeholder", jsonObj.age);
    alert(jsonObj.age);
    document.getElementById("phoneNumber").setAttribute("placeholder", jsonObj.phoneNumber);
    alert(jsonObj.phoneNumber);
    document.getElementById("password").setAttribute("placeholder", jsonObj.password);
    alert(jsonObj.phoneNumber);
}