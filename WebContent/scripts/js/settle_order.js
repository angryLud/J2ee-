init();
function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/unsettled',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				$('#orderBody').append('<tr class="unread">'+
						'<td class="cell-status hidden-phone hidden-tablet" id="hallNo">'+result[i].hallNo+'</td>'+
						'<td class="cell-author">'+result[i].hallName+'</td>'+
						'<td class="cell-icon" id="unsettledNum">'+result[i].unsettledNum+'</td>'+
						'<td class="cell-icon hidden-phone hidden-tablet">'+
						'<button name="settle" type="button" class="btn btn-primary" onclick="settle()">结算</button>'+
						'</td></tr>');
			}
			}else{
				alert("获取场馆未结算失败");
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}

function settle(){
	var hallNo = $('#hallNo').html();
	$.ajax({
		url : 'http://localhost:8080/Tickets/manager/settle_order',
		type : 'post',
		dataType : 'json',
		data :{
			hallNo:hallNo
		},
		success:function(data){
			if(data.result == true){
				alert(data.reason);
				top.location = "http://localhost:8080/Tickets/page/settle_order.html";
			}else{
				alert(data.reason);
			}
			
		},
		error:function(){
			alert("服务器连接失败");
		}
	});
}