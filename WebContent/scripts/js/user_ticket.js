init();
function init() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/halls',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
			var result = data.object;
			for(var i=0;i<result.length;i++){
				$('#hallBody').append('<tr class="unread">'+
						'<td class="cell-status hidden-phone hidden-tablet" id="hallNo'+i+'">'+result[i].hallNo+'</td>'+
						'<td class="cell-status hidden-phone hidden-tablet">'+result[i].hallName+'</td>'+
						'<td class="cell-status">'+result[i].address+'</td>'+
						'<td class="cell-icon">'+result[i].juniorNum+'</td>'+
						'<td class="cell-icon">'+result[i].seniorNum+'</td>'+
						'<td class="cell-icon hidden-phone hidden-tablet">'+
						'<button name="view" type="button" class="btn btn-primary" onclick="viewplan('+i+')">查看计划</button>'+
						'</td></tr>');
			}
			$('#banner').html("Welcome, " + result[0].email);
			}else{
				alert("获取场馆统计失败");
			}
		},
		error : function() {

			alert('服务器连接失败');
		}
	});
}

function viewplan(i){
	top.location = "http://localhost:8080/Tickets/page/user_plans.html?hallNo="+$('#hallNo'+i).html();
}

function back(){
	top.location = "http://localhost:8080/Tickets/page/user_ticket.html";
}

function search(){
	var search = $('#searchCode').val();
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/searchHall',
		type : 'post',
		dataType : 'json',
		data:{
			hallNo:search
		},
		success: function(data){
			if(data.result == true){
				$('#hallBody').empty();
				var result = data.object;
				$('#hallBody').append('<tr class="heading">'+
                '<td class="cell-status hidden-phone hidden-tablet">场馆编号</td>'+
                '<td class="cell-status hidden-phone hidden-tablet">场馆名称</td>'+
                '<td class="cell-status">场馆地点</td>'+
                '<td class="cell-status">普通座数量</td>'+
                '<td class="cell-status hidden-phone hidden-tablet">高等座数量</td>'+
                '<td class="cell-status hidden-phone hidden-tablet" id="toBeFixed">操作</td></tr>');
				for(var i=0;i<result.length;i++){
					$('#hallBody').append('<tr class="unread">'+
							'<td class="cell-status hidden-phone hidden-tablet" id="hallNo'+i+'">'+result[i].hallNo+'</td>'+
							'<td class="cell-status hidden-phone hidden-tablet">'+result[i].hallName+'</td>'+
							'<td class="cell-status">'+result[i].address+'</td>'+
							'<td class="cell-icon">'+result[i].juniorNum+'</td>'+
							'<td class="cell-icon">'+result[i].seniorNum+'</td>'+
							'<td class="cell-icon hidden-phone hidden-tablet">'+
							'<button name="view" type="button" class="btn btn-primary" onclick="viewplan('+i+')">查看计划</button>'+
							'</td></tr>');
				}
				
				$('#forBack').append('<button type="button" id="backwards" class="btn btn-primary pull-right" onclick="back()">返回</button>');
			}
			
		},
		error: function(){
			alert("服务器连接失败");
		}
	});
}