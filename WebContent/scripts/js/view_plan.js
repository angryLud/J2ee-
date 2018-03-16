init();
function init() {
	var planNo = $.getUrlParam("planNo");
	$.ajax({
		url : 'http://localhost:8080/Tickets/plan/info',
		type : 'post',
		dataType : 'json',
		data:{
			planNo:planNo
		},
		success : function(data) {
			if (data.result == true) {
				var result = data.object;
				$('#planNo').html(result.planNo);
				$('#hallNo').html(result.hallNo);
				$('#hallName').html(result.hallName);
				$('#address').html(result.address);
				$('#juniorNum').html(result.juniorNum);
				$('#seniorNum').html(result.seniorNum);
				$('#juniorPrice').html(result.juniorPrice);
				$('#seniorPrice').html(result.seniorPrice);
				$('#showDate').html(result.showDate);
				$('#showType').html(result.showType);
				$('#description').html(result.description);
				$('#banner').html(
						"Welcome, " + result.hallName + "(" + result.hallNo
								+ ")");
			} else {
				alert(data.reason);
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}