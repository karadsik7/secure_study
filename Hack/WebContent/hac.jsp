<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println(request.getParameter("sessionid"));
	response.sendRedirect("http://localhost:9090/main");
%>