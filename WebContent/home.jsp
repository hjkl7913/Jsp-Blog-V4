<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="include/nav.jsp"%>

<%--  <% --%>

<!--    	Users principal = (Users)session.getAttribute("principal"); -->
<%--  %> --%>

<!-- <h1> -->
<%-- 	<% if(principal != null) {%> --%>
<%-- 		<%= principal.getUsername()%>  --%>
<%-- 	<% } %> --%>
<!-- </h1> -->


<div class="container">
	<c:forEach var="board" items="${boards}">
		<div class="card m-2" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4><!-- el 표현식은 변수를 호출하면 getter를 호출함 -->
				<p class="card-text">${board.content}</p>
				<a href="#" class="btn btn-primary stretched-link">상세보기</a>
			</div>
		</div>
	</c:forEach>

</div>

<%@ include file="include/footer.jsp"%>


