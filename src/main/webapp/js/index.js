var way = "passwd";

function dologin(){
	if (way=="passwd"){
		passwdLogin();
	}else if (way=="email"){
		emailLogin();
	}
}

function requestVerification() {
	//倒计时
	var i = 60;
	var tim = document.getElementsByClassName("get-code")[0];
	var timer = setInterval(function () {
		if (i == -1) {
			// window.location.href="http://blog.csdn.net/kill_bugs";
			clearInterval(timer);
			tim.innerHTML = "获取验证码"
		} else {
			tim.innerHTML = i+"秒后重发";
			--i;
		}
	}, 1000);

	//创建异步对象
	var xmlHttp = new XMLHttpRequest();
	var email = document.getElementById("email").value;
	var param = "email=" + email;

	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200){
			alert("验证码已发送");
		}
	}
	xmlHttp.open("POST","EmailServlet?"+param, true);
	//发送异步请求
	xmlHttp.send();
}

function passwdLogin() {
	//创建异步对象
	var xmlHttp = new XMLHttpRequest();
	var userid = document.getElementById("userid").value;
	var passwd = document.getElementById("passwd").value;
	var param = "way="+ way + "&userid=" + userid + "&passwd=" + passwd;
	//绑定事件
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200){
			var data = xmlHttp.responseText;
			if(data=="ERR_ID"){
				document.getElementById("err_id").innerHTML="账号不存在";
			}else if(data=="ERR_PW"){
				document.getElementById("err_pw").innerHTML="密码错误";
			}else if(data=="OK"){
				alert("登录成功");
				window.location.href="ProfileServlet?userid="+userid;
			}
		}
	}
	xmlHttp.open("POST","LoginServlet?"+param, true);
	//发送异步请求
	xmlHttp.send();
}

function emailLogin() {
	//创建异步对象
	var xmlHttp = new XMLHttpRequest();
	var email = document.getElementById("email").value;
	var verify = document.getElementById("verify").value;
	var param = "way="+ way+ "&email=" + email + "&verify=" + verify;
	//绑定事件
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState==4 && xmlHttp.status==200){
			var data = xmlHttp.responseText;
			if(data=="ERR_EMAIL"){
				document.getElementById("err_email").innerHTML="邮箱已改变，请检查输入";
			}else if(data=="ERR_VERIFY"){
				document.getElementById("err_verify").innerHTML="验证码错误";
			}else if(data=="OK"){
				alert("登录成功");
				window.location.href="ProfileServlet?";
				//TODO
			}
		}
	}
	xmlHttp.open("POST","LoginServlet?"+param, true);
	//发送异步请求
	xmlHttp.send();
}

function useridTab() {
	document.getElementById("emailVerify").style.display='none';
	document.getElementById("passwordVerify").style.display='block';
	document.getElementById("pwdLoginSpan").style.borderBottom='1px solid #000';
	document.getElementById("emailLoginSpan").style.borderBottom='1px solid #DEDEDE';
	way = "passwd";
}

function emailTab() {
	document.getElementById("passwordVerify").style.display='none';
	document.getElementById("emailVerify").style.display='block';
	document.getElementById("emailLoginSpan").style.borderBottom='1px solid #000';
	document.getElementById("pwdLoginSpan").style.borderBottom='1px solid #DEDEDE';
	way = "email";
}

function clearText() {
	document.getElementById("err_id").innerHTML="";
	document.getElementById("err_pw").innerHTML="";
	document.getElementById("err_email").innerHTML="";
	document.getElementById("err_verify").innerHTML="";
}