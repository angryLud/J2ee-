init();
function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/manager/system_finance',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.result == true) {
				var result = data.object;
				$('#totalMember').html(result.totalMember);
				$('#totalHall').html(result.totalHall);
				$('#totalOrder').html(result.totalOrder);
				$('#lv0Member').html(result.lvlMember[0]);
				$('#lv1Member').html(result.lvlMember[1]);
				$('#lv2Member').html(result.lvlMember[2]);
				$('#lv3Member').html(result.lvlMember[3]);
				$('#lv4Member').html(result.lvlMember[4]);
				$('#totalConsumption').html(result.totalConsumption);
				$('#totalIncome').html(result.totalIncome);
			} else {
				
				alert("获取财务统计失败");
			}
		},
		error : function(XMLHttpRequest, textStauts) {
			alert('数据获取失败');
		}
	});
}