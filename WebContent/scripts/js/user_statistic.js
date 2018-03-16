init();

function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/statistic',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			var result = data.object;
			$('#username').html(result.email);
			$('#totalOrder').html(result.totalOrder);
			$('#paidOrder').html(result.paidOrder);
			$('#cancelledOrder').html(result.cancelledOrder);
			$('#consumption').html(result.consumption);
			$('#banner').html("Welcome, " + result.email);
		},
		error : function() {

			alert('数据获取失败');
		}
	});
}