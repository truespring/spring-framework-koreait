package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUserLoginHistory(UserLoginHistoryVO ulhVO) {
		String sql = " INSERT INTO t_user_loginhistory "
				+ " (i_history, i_user, ip_addr, os, browser) "
				+ " VALUES (seq_userloginhistory.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, ulhVO.getI_user());
				ps.setNString(2, ulhVO.getIp_addr());
				ps.setNString(3, ulhVO.getOs());
				ps.setNString(4, ulhVO.getBrowser());
			}
			
		});
	}
	
	public static int join(UserVO param) {
		int result = 0;
		
		String sql = "INSERT INTO t_user "
				+ " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
				+ " (seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
//				콜백 함수를 사용한 경우이다. 이해하고 넘어가자.
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());
			} // 익명 클래스(객체화 한 것이 아니다) implements한 것
		});
	}
	
	public static int login(UserVO param) {
		int result = 0;
		
		String sql = " SELECT i_user, user_pw, nm "
				+ " FROM t_user "
				+ " WHERE user_id = ? ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
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
	
	public static UserVO selUser(int i_user) {
		String sql = " SELECT user_id, nm, profile_img, email, r_dt "
				+ " FROM t_user WHERE i_user = ? ";
		
		UserVO result = new UserVO();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface()  {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setUser_id(rs.getNString("user_id"));
					result.setNm(rs.getNString("nm"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setEmail(rs.getNString("email"));
					result.setR_dt(rs.getNString("r_dt"));
				}
				return 1;
			}
		});
		return result;
	}
	
	public static int updUser(UserVO param) {
		StringBuilder sb = new StringBuilder(" UPDATE t_user SET m_dt = sysdate ");
//		쿼리문을 합칠 때 퍼포먼스가 좋다.
		if(param.getUser_pw() != null) {
			sb.append(" , user_pw = '");
			sb.append(param.getUser_pw());
			sb.append("'");
			
			// sql += " , user_pw = '" + param.getI_user() + "'"; 위와 같음
		} // 수정정보를 찾아보고 선택형으로 수정되게 한다.
		if(param.getNm() != null) {
			sb.append(" , nm = '");
			sb.append(param.getNm());
			sb.append("'");
		}
		if(param.getEmail() != null) {
			sb.append(" , email = '");
			sb.append(param.getEmail());
			sb.append("'");
		}
		if(param.getProfile_img() != null) {
			sb.append(" , profile_img = '");
			sb.append(param.getProfile_img());
			sb.append("'");
		}
		
		sb.append(" WHERE i_user = ");
		sb.append(param.getI_user());
		
		return JdbcTemplate.executeUpdate(sb.toString(), new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
			}
		});
	}
}
