welcome();

function welcome() {
	$.ajax({
		url : 'http://localhost:8080/Tickets/hall/info',
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if(data.result == true){
				var result = data.object;
				$('#banner').html(
					"Welcome, " + result.hallName + "(" + result.hallNo
							+ ")");
			}
		}
	});
}