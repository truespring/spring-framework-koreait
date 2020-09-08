package com.koreait.matzip.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.UserVO;

public class UserDAO {
	public static int join(UserVO param) {
		int result = 0;
		
		String sql = " INSERT INTO t_user "
				+ " (user_id, user_pw, nm, salt, profile_img) "
				+ " VALUES "
				+ " (?, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getSalt());
				ps.setNString(5, param.getProfile_img());
				
				}
		});
	}
	
	public UserVO selUser(UserVO param) {
		UserVO result = new UserVO();
		
		String sql = " SELECT i_user, user_id, user_pw, nm, profile_img, r_dt, m_dt "
				+ " FROM t_user WHERE ";
		
		if(param.getI_user() > 0) {
			sql += " i_user = " + param.getI_user();
		}
		if(param.getUser_id() != null && !"".equals(param.getUser_id())) {
			sql += " user_id = '" + param.getUser_id() + "'";
		}
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					String dbPw = rs.getNString("user_pw");
					
					if(dbPw.contentEquals(param.getUser_pw())) {
						result.setI_user(rs.getInt("i_user"));
						result.setUser_pw(null);
						result.setUser_id(rs.getNString("user_id"));
						result.setNm(rs.getNString("nm"));
						result.setProfile_img(rs.getNString("profile_img"));
						result.setR_dt(rs.getNString("r_dt"));
						result.setM_dt(rs.getNString("m_dt"));
						return 1;
					} else {
						return 2;
					}
				} else {
					return 3;
				}
			}
		});
		return result;
	}
}
