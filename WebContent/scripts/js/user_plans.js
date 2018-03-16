var hallNo = $.getUrlParam('hallNo');
init();
function init() {
	for(var j=0;j<8;j++){
		$('#toBeFixed').html("&nbsp;"+$('#toBeFixed').html());
	}

	$.ajax({
		url : 'http://localhost:8080/Tickets/member/plan',
		type : 'POST',
		dataType : 'json',
		data:{
			hallNo:hallNo
		},
		success:function(data){
			if(data.result == true){
				var result = data.object;
				for(var i=0;i<result.length;i++){
					$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
							'<td class="cell-status hidden-phone hidden-tablet" id="planNo'+i+'">'+result[i].planNo+'</td>'+
							'<td class="cell-author hidden-phone hidden-tablet">'+result[i].showDate+'</td>'+
							'<td class="cell-author">'+result[i].showType+'</td>'+
							'<td class="cell-icon">'+result[i].juniorNum+'</td>'+
							'<td class="cell-icon">'+result[i].seniorNum+'</td>'+
							'<td class="cell-icon hidden-phone hidden-tablet">'+
							'<button name="view" type="button" class="btn btn-primary" onclick="order('+i+')">购票</button>'+
							'</td></tr>');
				}
				$('#banner').html("Welcome, " + result[0].email);
			}else{
				alert(data.reason);
			}
		},
		error:function(){
			alert("服务器连接失败");
		}
	});
}

function order(i){
	top.location = "http://localhost:8080/Tickets/page/user_order_ticket.html?planNo="+$('#planNo'+i).html()+"&hallNo="+hallNo;
}

function back(){
	var hallNo = $.getUrlParam("hallNo");
	top.location = "http://localhost:8080/Tickets/page/user_plans.html?hallNo="+hallNo;
}


function search(){
	var search = $('#searchCode').val();
	var hallNo = $.getUrlParam('hallNo');
	$.ajax({
		url : 'http://localhost:8080/Tickets/member/searchPlan',
		type : 'post',
		dataType : 'json',
		data:{
			planNo:search,
			hallNo:hallNo
		},
		success: function(data){
			if(data.result == true){
				$('#planBody').empty();
				
				var result = data.object;
				$('#planBody').append('<tr class="heading">'+
                '<td class="cell-icon hidden-phone hidden-tablet"></td>'+
                '<td class="cell-status hidden-phone hidden-tablet">计划编号</td>'+
                '<td class="cell-time hidden-phone hidden-tablet">演出时间</td>'+
                '<td class="cell-author">演出类型</td>'+
                '<td class="cell-author">普通座数量</td>'+
                '<td class="cell-author hidden-phone hidden-tablet">高等座数量</td>'+
                '<td class="cell-author hidden-phone hidden-tablet" id="toBeFixed">操作</td></tr>');
				for(var j=0;j<8;j++){
					$('#toBeFixed').html("&nbsp;"+$('#toBeFixed').html());
				}
				for(var i=0;i<result.length;i++){
					$('#planBody').append('<tr class="unread"><td class="cell-icon hidden-phone hidden-tablet"></td>'+
							'<td class="cell-status hidden-phone hidden-tablet" id="planNo'+i+'">'+result[i].planNo+'</td>'+
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