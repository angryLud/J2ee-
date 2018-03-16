
init();

function init() {
	var orderid = $.getUrlParam('orderid');
	$.ajax({
		url : 'http://localhost:8080/Tickets/order/info',
		type : 'post',
		dataType : 'json',
		data:{
			orderid:orderid
		},
		success : function(data) {
			if (data.result == true) {
				var result = data.object;
				$('#orderid').html(result.orderid);
				$('#orderMethod').html(result.orderMethod==0?"线上预订":"线下购买");
				$('#option').html(result.toption==0?"选座购买":"立即购买");
				$('#ticketNum').html(result.ticketNum);
				$('#ticketType').html(result.ticketType==0?"普通座":"高等座");
				$('#totalPrice').html(result.totalPrice);
				$('#orderDate').html(result.orderDate);
				$('#payState').html(result.payState==0?"未支付":"已支付");
				$('#allocState').html(result.allocState==0?"尚未配票":"配票成功");
				$('#showDate').html(result.showDate);
				$('#hallNo').html(result.hallNo);
				$('#hallName').html(result.hallName);
				$('#showType').html(result.showType);
				$('#banner').html("Welcome, "+result.email);
			} else {
				alert(data.reason);
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}