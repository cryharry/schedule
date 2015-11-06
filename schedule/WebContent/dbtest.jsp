<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, javax.naming.*" %>
<%
	Connection conn = null;
	try {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/kcm");
		conn = ds.getConnection();
		out.println("연결성공");
	} catch(Exception e) {
		out.println("연결실패");
		e.printStackTrace();
	}
	
%>