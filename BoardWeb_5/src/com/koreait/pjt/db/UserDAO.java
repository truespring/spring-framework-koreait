package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		String sql = " SELECT i_user, user_pw, nm "
				+ " FROM t_user "
				+ " WHERE user_id = ? ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public ResultSet prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				return ps.executeQuery();
			}
//			0:에러발생, 1:잘되었을 때, 2:비밀번호가 잘못됨, 3:아이디가 없음
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) { // 아이디가 존재했을 때 레코드가 한 줄 넘어온다.
					String dbPw = rs.getNString("user_pw"); // DB에서 가져온 값
					
					if(dbPw.contentEquals(param.getUser_pw())) { // 로그인 성공
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");
						
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);
						return 1;
					} else { // 비밀번호 잘못됨
						return 2;
					}
				} else { // 아이디 없음
					return 3;
				}
			}
		});
	}
}
