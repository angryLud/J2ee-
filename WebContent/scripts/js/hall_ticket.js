init();
function init() {
	
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/plan2',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
						'<td class="cell-author hidden-phone hidden-tablet" id="planNo'+i+'">'+result[i].planNo+'</td>'+
						'<td class="cell-author hidden-phone hidden-tablet">'+result[i].showDate+'</td>'+
						'<td class="cell-author">'+result[i].showType+'</td>'+
						'<td class="cell-icon">'+result[i].juniorNum+'</td>'+
						'<td class="cell-icon">'+result[i].seniorNum+'</td>'+
						'<td class="cell-icon hidden-phone hidden-tablet">'+
						'<button name="view" type="button" class="btn btn-primary" onclick="order('+i+')">购票</button>'+
						'</td></tr>');
			}
			$('#banner').html("Welcome, " + result[0].hallName + "(" + result[0].hallNo + ")");
			}else{
				alert("获取场馆统计失败");
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}

function order(i){
	top.location = "http://localhost:8080/Tickets/page/hall_buy_ticket.html?planNo="+$('#planNo'+i).html();
}

function back(){
	top.location = "http://localhost:8080/Tickets/page/hall_ticket.html";
}

function search(){
	var search = $('#searchCode').val();
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/searchPlan2',
		type : 'post',
		dataType : 'json',
		data:{
			planNo:search
		},
		success: function(data){
			if(data.result == true){
				$('#planBody').empty();
				var result = data.object;
				$('#planBody').append('<tr class="heading">'+
                '<td class="cell-icon hidden-phone hidden-tablet"></td>'+
                '<td class="cell-author hidden-phone hidden-tablet">计划编号</td>'+
                '<td class="cell-time hidden-phone hidden-tablet">演出时间</td>'+
                '<td class="cell-author">演出类型</td>'+
                '<td class="cell-author">普通座数量</td>'+
                '<td class="cell-author hidden-phone hidden-tablet">高等座数量</td>'+
                '<td class="cell-author hidden-phone hidden-tablet" id="toBeFixed">操作</td></tr>');
				for(var i=0;i<result.length;i++){
					$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
							'<td class="cell-author hidden-phone hidden-tablet" id="planNo'+i+'">'+result[i].planNo+'</td>'+
							'<td class="cell-author hidden-phone hidden-tablet">'+result[i].showDate+'</td>'+
							'<td class="cell-author">'+result[i].showType+'</td>'+
							'<td class="cell-icon">'+result[i].juniorNum+'</td>'+
							'<td class="cell-icon">'+result[i].seniorNum+'</td>'+
							'<td class="cell-icon hidden-phone hidden-tablet">'+
							'<button name="view" type="button" class="btn btn-primary" onclick="order('+i+')">购票</button>'+
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