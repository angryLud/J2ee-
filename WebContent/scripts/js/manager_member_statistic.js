init();
function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/manager/member_statistic',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
						'<td class="cell-time hidden-phone hidden-tablet">'+result[i].email+'</td>'+
						'<td class="cell-author">'+result[i].totalOrder+'</td>'+
						'<td class="cell-icon">'+result[i].paidOrder+'</td>'+
						'<td class="cell-icon">'+result[i].cancelledOrder+'</td>'+
						'<td class="cell-icon hidden-phone hidden-tablet">'+
						result[i].consumption+
						'</td><tr/>');
			}

			}else{
				alert("获取场馆统计失败");
			}
		},
		error : function(XMLHttpRequest, textStauts) {
			alert('数据获取失败');
		}
	});
}