<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var t1 = this; //this 는 window
	console.log(t1);

	// 오브젝트 window.car 내부 function move() this
	//메서드 내부의 this 는  그 메서드를 감싸고 있는 오브젝트 
	var car = {
		name: '소나타',
		move: function(){
			console.log('car 오브젝트의 function');
			console.log(this.name);
			}
	};
	car.move();

	function a (){
		console.log(this);
	}

	var d = {}
	$('btn').on('click',function(){
		var useranme = 'ssar';
		console.log("on click의 내부");
		console.log(username);
		console.log('---------------');
		})
		

	console.log('-------------');
	console.log(car.name);
	car.move();
	car.send();
</script>
</head>
<body>

</body>
</html>