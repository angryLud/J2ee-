init();
function init(){
	var checking = $.getUrlParam('checking');
	if(checking == 1){
		alert('激活邮件已发送');
	}
}

function login() {

	if ($('#username').val().length === 0 || $('#password').val().length === 0) {
		alert("用户名或密码不能为空");
		return;
	}
	
	if($('#username').val().indexOf("@") == -1){
		alert("用户名须为邮箱格式");
		return;
	}
	



	 var result = {};
	 result.username = $('#username').val();
	 result.password = $('#password').val();
	 $.ajax({
		url : 'http://localhost:8080/Tickets/login/user_login',
		type : 'POST',
		dataType : 'json',
		data : result,
		success : function(data) {
			if (data.result == true) {
				top.location = 'http://localhost:8080/Tickets/page/user_info.html';
			} else {
				alert("登录失败，原因：" + data.reason);
			}
		},
		error : function(XMLHttpRequest,textStatus,errorThrown) {
			alert("服务器连接失败！");
		}
	});

}