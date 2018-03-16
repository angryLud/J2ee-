
init();

function init() {
	var orderid = $.getUrlParam('orderid');
	var totalPrice = $.getUrlParam('totalPrice')
	$('#totalPrice').val(totalPrice)
	$('#orderid').val(orderid);
}

function pay() {
	var telephone = $('#telephone').val();
	var password = $('#password').val();
	$
			.ajax({
				url : 'http://localhost:8080/Tickets/member/pay',
				type : 'post',
				dataType : 'json',
				data : {
					orderid : $.getUrlParam("orderid"),
					telephone : telephone,
					password : password,
					totalPrice : $.getUrlParam("totalPrice")
				},
				success : function(data) {
					if (data.result == true) {
						alert("支付成功");
						top.location = "http://localhost:8080/Tickets/page/paid_order.html";
					} else {
						alert(data.reason);
						top.location = "http://localhost:8080/Tickets/page/unpaid_order.html";
					}
				},
				error : function() {
					alert("服务器连接失败");
				}
			});
}

function afterwards() {
	top.location = "http://localhost:8080/Tickets/page/unpaid_order.html";
}