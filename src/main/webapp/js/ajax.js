$(document).ready(function() {

	//Delete user from admin
	$("[id^=del]").click(function() {
		
		if (confirm('Are you sure to remove user?')) {
			
			//get button value
			var data = $(this).val();
			
			//removing the row fromthe datatable
			var a = $('#example6').DataTable();
			a.row($(this).closest("tr")).remove();
			$(this).closest("tr").remove();
			
			$.ajax({
				type : "POST",
				url : "RegisterController",
				data : {
					operation : 'removeUser',
					userId : $(this).val()
				},
				success : function(data) {
					var a = $('#example6').DataTable();
					a.draw();
					alert(data);
				},
				error : function(data) {
					alert("error");
				}
			});
		}
		else {
			var a = $('#example6').DataTable();
			a.draw();
		}
		
	});
	
	//Frogot Password
	$("#forgotPassword").click(function() {
		
		$.ajax({
			type : "POST",
			url : "RegisterController",
			data : {
				operation : 'forgotPassword',
				userEmail : $("#userEmail").val()
			},
			success : function(data) {
				$('#password').text(data);
			},
			error : function(data) {
				$('#password').text("Some Thing Went Wrong Please Try Again...!!");
			}
		});
		return false;
	});	
	
});