package com.koreait.pjt.db;

import java.sql.*;

import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUser(UserVO param) {
		int result = 0;
		
		String sql = "INSERT INTO t_user "
				+ " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
				+ " (seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public int update(PreparedStatement ps) throws SQLException {
//				콜백 함수를 사용한 경우이다. 이해하고 넘어가자.
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());
				return ps.executeUpdate();
			} // 익명 클래스(객체화 한 것이 아니다)
		});
	}
	
	public static int selUser(UserVO param) {
		int result = 0;
		
//		String 
		return result;
	}
}
