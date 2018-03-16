init();

function init() {
	var option = $('#option').val();
	if (option == 0) {
		$('#button').html("进行选座");
		$('#tobehided').hide();
		$('#button').attr("onclick", "choose()");
	} else if (option == 1) {
		$('#ticketNum').attr("max", "20");
		$('#tobehided').show();
		$('#button').html("提交订单");
		$('#button').attr("onclick", "order()");
	}
	var planNo = $.getUrlParam('planNo');
		$.ajax({
			url : 'http://localhost:8080/Tickets/plan/info',
			type : 'POST',
			dataType : 'json',
			data : {
				planNo : planNo
			},
			success : function(data) {
				var object = data.object;
				if (data.result == true) {
					$('#showDate').val(object.showDate);
					$('#showType').val(object.showType);
					if ($('#ticketType').val() == 0) {
						$('#ticketPrice').val(object.juniorPrice);
					} else {
						$('#ticketPrice').val(object.seniorPrice);
					}
					$('#banner').html(
							"Welcome, " + object.hallName + "(" + object.hallNo
									+ ")");
				} else {
					alert(data.reason);
				}
			},
			error : function() {
				alert("服务器连接失败");
			}
		});
}


function choose() {

	top.location = "http://localhost:8080/Tickets/page/hall_choose_seat.html?email="+$('#email').val()+"&showType="+$('#showType').val()+"&showDate="+$('#showDate').val()+"&ticketType="+$('#ticketType').val()+"&ticketPrice="+$('#ticketPrice').val()+"&planNo="+$.getUrlParam('planNo');
}
