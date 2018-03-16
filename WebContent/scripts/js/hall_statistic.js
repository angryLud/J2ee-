init();

function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/statistic',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			$('#hallNo').html(result.hallNo);
			$('#hallName').html(result.hallName);
			$('#totalOrder').html(result.totalOrder);
			$('#onlineOrder').html(result.onlineOrder);
			$('#successOrder').html(result.successOrder);
			$('#cancelledOrder').html(result.cancelledOrder);
			$('#income').html(result.income);
			$('#banner').html("Welcome, " + result.hallName + "(" + result.hallNo + ")");
			}else{
				alert("获取场馆统计失败，原因：" + data.reason);
			}
		},
		error : function() {

			alert('数据获取失败');
		}
	});
}