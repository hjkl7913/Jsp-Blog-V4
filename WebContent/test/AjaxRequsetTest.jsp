<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	div {
		border: 1px solid black;
		margin: 5px;
		padding: 5px;
	}
</style>
</head>
<body>

<div id="reply-box">
	<div id="reply-1">
		첫번째 댓글입니다. 
	</div> 
</div>	

<input type="text" id="tf-reply" /> <br/>
<button onclick="start()">댓글쓰기</button>

<!-- <div id="demo"> 
 </div> 

<input type="text" id="demo" /> -->

<script>
var num = 1;
function start() {
// 	$('#demo').val("hello");
	num++;
	var a = $('#tf-reply').val();

	var data = {
		username: "ssar",
		password:a //작성할글
	};
	
	// 통신이 성공하면 아래 로직 실행
	$.ajax({  //dom 을 찾는것이 아니므로 .이 나옴
		type: 'POST', //디폴트는 get
		url: 'AjaxResponseTest.jsp',   //필수값 나머지는 생략가능함
		data: JSON.stringify(data), //생략가능  , 보낼 데이터  //json타입으로 변경
		contentType: 'application/json; charset=utf-8', //data 값이 html형태면 text/html , json 형태면  application/json
		dataType: 'json' //받을 데이터를 어떻게 파싱할까를 정의 , text 나 json 만 가능
	}).done(function(result){ //통신이 성공하면
		console.log(result);
		$('#reply-box').prepend("<div id='reply-"+num+"'>"+a+"</div>"); //prepend 맨위로 감  //append 맨밑으로감
	}).fail(function(error){ //통신이 실패하면
		console.log('에러났어');
		console.log(error); //정확하게 알고싶으면 console.log 사용
	}); 
	
	
}
</script>
</body>
</html>