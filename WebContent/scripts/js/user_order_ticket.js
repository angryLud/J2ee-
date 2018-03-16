var hallNo = $.getUrlParam('hallNo');
var planNo = $.getUrlParam('planNo');
init();

function init() {
	var option = $('#option').val();
	if (option == 0) {
		$('#button').html("进行选座");
		$('#tobehided').hide();
		$('#button').attr("onclick", "choose()");
	} else if (option == 1) {
		$('#ticketNum').attr("max", "20");
		$('#tobehided').show();
		$('#button').html("提交订单");
		$('#button').attr("onclick", "order()");
	}

	$.ajax({
		url : 'http://localhost:8080/Tickets/plan/infoforuser',
		type : 'POST',
		dataType : 'json',
		data:{
			planNo:planNo
		},
		success:function(data){
			var object = data.object
			if(data.result == true){
				$('#email').val(object.email);
				$('#points').val(object.point);
				$('#hints').html("可优惠<b id='minused'>"+object.point / 100+"</b>元");
				$('#points').attr("max", object.point);
				$('#showDate').val(object.showDate);
				$('#showType').val(object.showType);
				if ($('#ticketType').val() == 0) {
					$('#ticketPrice').val(object.juniorPrice);
				} else {
					$('#ticketPrice').val(object.seniorPrice);
				}
				$('#banner').html("Welcome, " + object.email);
			}else{
				alert(data.reason);
			}
		},
		error:function(){
			alert("服务器连接失败");

		}
	});
}

function sale(){
	$('#hints').html("可优惠<b>"+$('#points').val() / 100+"</b>元");
}

function order() {
	var email = $('#email').val();
	var option = $('#option').val();
	var ticketNum = $('#ticketNum').val();
	var ticketType = $('#ticketType').val();
	var showDate = $('#showDate').val();
	var showType = $('#showType').val();
	var ticketPrice = $('#ticketPrice').val();

	$.ajax({
		url : 'http://localhost:8080/Tickets/member/instantBuyTicket',
		type : 'POST',
		dataType : 'json',
		data:{
			email:email,
			option:option,
			ticketNum:ticketNum,
			ticketType:ticketType,
			ticketPrice:ticketPrice,
			showDate:showDate,
			minused : parseFloat($('#points').val() / 100),
			showType:showType,
			hallNo:hallNo
		},
		success:function(data){
			if(data.result==true){
				var result = data.object;
				var orderid = result.orderid;
				var totalPrice = result.totalPrice ;
				top.location = 'http://localhost:8080/Tickets/page/pay_order.html?orderid='+orderid+"&totalPrice="+totalPrice;
			}else{
				alert(data.reason);
			}
		},
		error:function(){
			alert("服务器连接失败");
		}
	});

}

function choose(){
	top.location = "http://localhost:8080/Tickets/page/user_choose_seat.html?email="+$('#email').val()+"&showType="+$('#showType').val()+"&showDate="+$('#showDate').val()+"&ticketType="+$('#ticketType').val()+"&ticketPrice="+$('#ticketPrice').val()+"&hallNo="+hallNo+"&planNo="+planNo+"&minused="+parseFloat($('#points').val()/100);
}