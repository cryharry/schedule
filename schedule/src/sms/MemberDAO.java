package sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private Connection dbConn() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/kcm");
		conn = ds.getConnection();
		return conn;
	}
	
	public MemberBean getMember(String m_name) {
		MemberBean memberBean = null;
		try {
			conn = dbConn();
			sql = "SELECT id, pass FROM MEMBER WHERE m_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberBean = new MemberBean();
				memberBean.setId(rs.getString("id"));
				memberBean.setPass(rs.getString("pass"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(Exception e){}
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();}catch(Exception e){}
		}
		return memberBean;
	}
}
