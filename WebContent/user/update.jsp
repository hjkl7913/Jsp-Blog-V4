<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="../include/nav.jsp"%>


<div class="container">
	<form action="/blog/user?cmd=updateProc" method="POST" class="was-validated">
		
		<input type="hidden" id="id" name="id" value="${sessionScope.principal.id}">
		
		<div class="form-group">
			<label for="username">Username:</label>
			 <input value="${sessionScope.principal.username}" class="form-control" id="username"
				name="username" required readonly>
			 
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		
		
		<div class="form-group">
			<label for="email">Email:</label> <input value="${sessionScope.principal.email}" type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		
		<div class="form-group">
			<label for="address">Address:</label>
			
			<!-- 버튼타입으로 만들거면 button 이라고 명시해야 안헷갈림 , inline block 이므로 float로 위치 조정 -->
			<button type="button" class="btn btn-warning float-right" onclick="goPopup()">주소검색</button> 
			<input type="text" value="${sessionScope.principal.address}" class="form-control" id="address" placeholder="Enter address"
				name="address" required readonly>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
	
		<button type="submit" class="btn btn-primary">회원정보 수정</button>
	</form>
</div>


<script src="/blog/js/join.js"></script>

<%@ include file="../include/footer.jsp"%>


