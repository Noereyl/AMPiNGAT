//Ajax function 
function ajaxrequest(id){
	var req = new Request.HTML({
		method: 'get',
		url: 'display.php?id='+id,
		update: $('imageframe')				
	}).send();		
}