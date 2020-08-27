package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.board.BoardCmtDomain;
import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	public static int insCmt;
	public static List<BoardCmtDomain> selCmtList(int i_board) {
		final List<BoardCmtDomain> list = new ArrayList();
		
		String sql = " SELECT A.i_cmt, B.nm, A.cmt, A.r_dt, A.i_user "
				+ " FROM t_board5_cmt A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE A.i_board = ? "
				+ " ORDER BY i_cmt "; // r_dt로 하지 않은 이유는 String 타입은 느리기 때문
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					String nm = rs.getNString("nm");
					String cmt = rs.getNString("cmt");
					String r_dt = rs.getNString("r_dt");
					int i_cmt = rs.getInt("i_cmt");
					int i_user = rs.getInt("i_user"); // 수정과 삭제를 위해서 필요하다
					
					BoardCmtDomain vo = new BoardCmtDomain();
					vo.setI_cmt(i_cmt);
					vo.setNm(nm);
					vo.setCmt(cmt);
					vo.setR_dt(r_dt);
					vo.setI_user(i_user);
					
					list.add(vo);
				}
				return 1; // 쓰지 않는 값
			}
		});
		return list;
	}
	
	public static int insCmt(BoardCmtVO param) {
		String sql = " INSERT INTO t_board5_cmt "
				+ " (i_cmt, cmt, i_board, i_user) "
				+ " VALUES "
				+ " (seq_board5_cmt.nextval, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_user());
			}	
		});
	}
	
	public static int updCmt(BoardCmtVO param) {
		String sql = " UPDATE t_board5_cmt "
				+ " SET cmt = ? "
				+ " WHERE i_user = ? AND i_cmt = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_user());
				ps.setInt(3, param.getI_cmt());
			}
		});
	}
	
	public static int delCmt(BoardCmtVO param) {
		String sql = " DELETE FROM t_board5_cmt "
				+ " WHERE i_cmt = ? AND i_user = ? ";
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_cmt());
				ps.setInt(2, param.getI_user());
			}
		});	
		return 0;
	}
}
