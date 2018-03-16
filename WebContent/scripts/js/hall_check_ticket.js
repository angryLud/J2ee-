function check() {
	
	 var result = {};
	 result.orderid = $('#orderid').val();
	 result.telephone = $('#telephone').val();
	 $.ajax({
		url : 'http://localhost:8080/Tickets/hall/check',
		type : 'POST',
		dataType : 'json',
		data : result,
		success : function(data) {
			if (data.result == true) {
				alert(data.reason);
				top.location="http://localhost:8080/Tickets/page/hall_check_ticket.html";
			} else {
				alert(data.reason);
			}
		},
		error : function(XMLHttpRequest,textStatus,errorThrown) {
			alert("服务器连接失败！");
		}
	});

}