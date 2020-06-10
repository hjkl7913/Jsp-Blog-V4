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

	<div class="col-md-12 m-2">
		<form class="form-inline justify-content-end" action="/blog/board">
			<input type="hidden" name="cmd" value="search"> <input type="hidden" name="page" value="0"> <input class="form-control mr-sm-2" name="keyword" type="text" placeholder="Search">
			<button class="btn btn-danger" type="submit">검색</button>
		</form>
	</div>

	<c:forEach var="board" items="${boards}">
		<div class="card m-2" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<!-- el 표현식은 변수를 호출하면 getter를 호출함 -->
				<p class="card-text">${board.content}</p>
				<a href="/blog/board?cmd=detail&id=${board.id}" class="btn btn-primary stretched-link">상세보기</a>
			</div>
		</div>
	</c:forEach>

	<br />

	<c:choose>
		<c:when test="${empty param.keyword}">
			<c:set var="pageNext" value="/blog/board?cmd=home&page=${param.page+1}" />
			<c:set var="pagePrevious" value="/blog/board?cmd=home&page=${param.page-1}" />
			
		</c:when>
		<c:otherwise>
			<c:set var="pageNext" value="/blog/board?cmd=search&page=${param.page+1}&keyword=${param.keyword}" />
			<c:set var="pagePrevious" value="/blog/board?cmd=search&page=${param.page-1}&keyword=${param.keyword}" />
		</c:otherwise>
	</c:choose>


	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${param.page==0}">
				<li class="page-item disabled"><a class="page-link" href="${pageScope.pagePrevious}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${pageScope.pagePrevious}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${(param.page*3)+3 >= result}">
				<li class="page-item disabled"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:when>
			<c:when test="${(param.page*3)+3 >= result2}">
				<li class="page-item disabled"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>


</div>

<%@ include file="include/footer.jsp"%>


