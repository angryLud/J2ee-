init();

function init() {
	var email = $.getUrlParam('email');
	var token = $.getUrlParam('token');
	$.ajax({
				url : 'http://localhost:8080/Tickets/register/check_reg',
				type : 'POST',
				dataType : 'json',
				data : {
					email : email,
					token : token
				},
				success : function(data) {
					if (data.result == true) {
						alert("恭喜您！会员验证已通过！");
						top.location = "http://localhost:8080/Tickets/page/user_login.html";
					} else {
						alert("会员验证未通过，请重新注册！");
					}

				},
				error : function() {
					alert('服务器连接失败');
				}

			});
}