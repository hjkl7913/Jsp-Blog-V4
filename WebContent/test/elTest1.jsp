<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// page 1 -> requset 2 -> session 3-> application 4  (우선순위) 
	request.setAttribute("username", "ssar");
	session.setAttribute("username", "1234");	
	
	RequestDispatcher dis = request.getRequestDispatcher("elTest2.jsp");
	dis.forward(request, response);
	
	//response.sendRedirect("elTest2.jsp");
%>