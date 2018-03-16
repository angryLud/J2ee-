init();
function init() {
//	for(var i =0;i<8;i++){
//	$('#toBeFixed').html("&nbsp;"+$('#toBeFixed').html());
//	}
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/orders',
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
						'<td class="cell-status hidden-phone hidden-tablet">'+
						'<button name="view" type="button" class="btn btn-primary" onclick="view('+i+')">查看</button>'+
						'</td><tr/>');
			}
			$('#banner').html("Welcome, "+result[0].email);
			}else{
				alert("获取个人订单失败");
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

function back(){
	top.location = "http://localhost:8080/Tickets/page/user_order.html";
}

function search(){
	var search = $('#searchCode').val();

	$.ajax({
		url : 'http://localhost:8080/Tickets/order/search',
		type : 'post',
		dataType : 'json',
		data:{
			orderid:search
		},
		success: function(data){
			if(data.result == true){
				$('#orderBody').empty();
				var result = data.object;
				$('#orderBody').append('<tr class="heading">'+
                '<td class="cell-icon hidden-phone hidden-tablet"></td>'+
                '<td class="cell-status hidden-phone hidden-tablet">订单编号</td>'+
                '<td class="cell-status hidden-phone hidden-tablet">场馆名称</td>'+
                '<td class="cell-status">演出类型</td>'+
                '<td class="cell-status">购票种类</td>'+
                '<td class="cell-status hidden-phone hidden-tablet">购票数量</td>'+
                '<td class="cell-status hidden-phone hidden-tablet">订单金额</td>'+
                '<td class="cell-status hidden-phone hidden-tablet" id="toBeFixed">操作</td></tr>');
				for(var i=0;i<result.length;i++){
					var ticketType = result[i].ticketType==0?"普通座":"高等座";
					$('#orderBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
							'<td class="cell-status hidden-phone hidden-tablet" id="orderid'+i+'">'+result[i].orderid+'</td>'+
							'<td class="cell-status hidden-phone hidden-tablet">'+result[i].hallName+'</td>'+
							'<td class="cell-status">'+result[i].showType+'</td>'+
							'<td class="cell-status">'+ticketType+'</td>'+
							'<td class="cell-status">'+result[i].ticketNum+'</td>'+
							'<td class="cell-status">'+result[i].totalPrice+'</td>'+
							'<td class="cell-status hidden-phone hidden-tablet">'+
							'<button name="view" type="button" class="btn btn-primary" onclick="view('+i+')">查看</button>'+
							'</td><tr/>');
				}
				
				$('#forBack').append('<button type="button" id="backwards" class="btn btn-primary pull-right" onclick="back()">返回</button>');
			}
			
		},
		error: function(){
			alert("服务器连接失败");
		}
	});
}