function addMsgSystem(msg){
	const $h4 = $("<h4>").css("textAlign","center").text("===="+msg.msg+"====");
	$("#chattingcontainer").append($h4);
}

function printMsg(myId, msg){
	const $p = $("<p>")
	.css("textAlign",(myId==msg.sender?"left":"right"))
	.text(`${msg.sender} : ${msg.msg}`); 
	$("#chattingcontainer").append($p);
}