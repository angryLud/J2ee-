init();
function init(){
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/cancelled_orders',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				var ticketType = result[i].ticketType==0?"普通座":"高等座";
				
				$('#orderBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
						'<td class="cell-status hidden-phone hidden-tablet" id="orderid'+i+'">'+result[i].orderid+'</td>'+
						'<td class="cell-status hidden-phone hidden-tablet">'+result[i].hallName+'</td>'+
						'<td class="cell-status">'+result[i].showType+'</td>'+
						'<td class="cell-status">'+ticketType+'</td>'+
						'<td class="cell-status">'+result[i].ticketNum+'</td>'+
						'<td class="cell-status">'+result[i].totalPrice+'</td>'+
						'<td class="cell-author hidden-phone hidden-tablet">'+
						'<button name="view" type="button" class="btn btn-primary" onclick="view('+i+')">查看</button>&nbsp;&nbsp;'+
						'</td><tr/>');
			}
			$('#banner').html("Welcome, "+result[0].email);
			}else{
				alert("获取已支付订单失败");
			}
		},
		error : function() {
			alert('服务器连接失败');
		}
	});
}

function view(i){
	top.location = "http://localhost:8080/Tickets/page/view_order.html?orderid="+$('#orderid'+i).html();
}
