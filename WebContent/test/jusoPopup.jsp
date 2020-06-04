<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String inputYn = request.getParameter("inputYn"); //null
	String roadFullAddr = request.getParameter("roadFullAddr"); //null
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Juso.kr 팝업</title>
</head>
<script>

function init(){
	var url = location.href; //자기자신주소  http://localhost:8000/blog/test/jusoPopup.jsp
	var confmKey = "devU01TX0FVVEgyMDIwMDYwMjA5NDkwMjEwOTgyMDE=";
	var resultType = "4";
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){ //null 이니까 Y아님
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do";
		document.form.submit();
	}else{
		opener.jusoCallBack("<%=roadFullAddr%>");
		window.close();
	}
}
</script>
<body onload="init()"> <!-- 바디가 실행되면 자동으로 로드 -->
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
	</form>
</body>
</html>



