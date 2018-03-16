init();

function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/info',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.result == true) {
				var result = data.object;
				$('#hallNo').html(result.hallNo);
				$('#password').html(result.password);
				$('#hallName').html(result.hallName);
				$('#address').html(result.address);
				$('#juniorNum').html(result.juniorNum);
				$('#seniorNum').html(result.seniorNum);
				$('#income').html(result.income);
				$('#percent').html(result.percent);
				$('#banner').html(
						"Welcome, " + result.hallName + "(" + result.hallNo
								+ ")");
			} else {
				alert("获取场馆信息失败，原因：" + data.reason);
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}