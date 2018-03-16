init();
function init() {
//	for(var i =0;i<8;i++){
//	$('#toBeFixed').html("&nbsp;"+$('#toBeFixed').html());
//	}
	$.ajax({
		url : 'http://localhost:8080/Tickets/manager/hall_statistic',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
						'<td class="cell-author hidden-phone hidden-tablet">'+result[i].hallNo+'</td>'+
						'<td class="cell-author hidden-phone hidden-tablet">'+result[i].hallName+'</td>'+
						'<td class="cell-author">'+result[i].totalOrder+'</td>'+
						'<td class="cell-icon">'+result[i].successOrder+'</td>'+
						'<td class="cell-icon">'+result[i].cancelledOrder+'</td>'+
						'<td class="cell-icon hidden-phone hidden-tablet">'+
						result[i].income+
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