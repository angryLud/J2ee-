$.ajax({
		url: 'http://localhost:8080/Tickets/hall/info',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data.result == true) {
                var object = data.object;
                $('#banner').html("Welcome, " + object.hallName + "(" + object.hallNo + ")");
                $('#juniorNum').attr("max", object.juniorNum);
                $('#seniorNum').attr("max", object.seniorNum);
            } else {
                alert('信息获取失败，原因：'+data.reason);
            }
        },
        error: function (data) {
            alert('服务器连接失败');
        }
	});

function make(){
	$.ajax({
		url: 'http://localhost:8080/Tickets/hall/makeplan',
        type: 'post',
        dataType: 'json',
        data:{
        	showDate:$('#showDate').val(),
        	showType:$('#showType').val(),
        	juniorNum:$('#juniorNum').val(),
        	seniorNum:$('#seniorNum').val(),
        	juniorPrice:$('#juniorPrice').val(),
        	seniorPrice:$('#seniorPrice').val(),
        	description:$('#description').val(),
        },
        success: function (data) {
            if (data.result == true) {
                top.location = "http://localhost:8080/Tickets/page/hall_plan.html";
            } else {
                alert(data.reason);
            }
        },
        error: function (data) {
            alert('服务器连接失败');
        }
	});
}
	
	
