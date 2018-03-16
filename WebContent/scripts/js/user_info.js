
init();

function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/info',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.result == true) {
				var result = data.object;
				$('#username').html(result.email);
				$('#password').html(result.password);
				$('#name').html(result.name);
				$('#telephone').html(result.telephone);
				$('#sex').html(result.sex);
				$('#state').html(result.state);
				$('#balance').html(result.balance);
				$('#consumption').html(result.consumption);
				$('#level').html(result.level);
				$('#point').html(result.point);
				$('#banner').html("Welcome, "+result.email);

			} else {
				alert("获取用户个人信息失败，原因：" + data.reason);
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});

}