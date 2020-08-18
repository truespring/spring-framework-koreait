package com.koreait.pjt.db;

import java.sql.*;

public class JdbcTemplate {
	// insert, update, delete에 사용할 메소드
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			result = jdbc.update(ps); // 달라지는 부분 처리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}

}
