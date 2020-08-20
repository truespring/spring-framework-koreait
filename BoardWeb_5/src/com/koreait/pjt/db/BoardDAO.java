package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList() {
		final List<BoardVO> list = new ArrayList();
		// 주소값을 고정시키는 것이고 내부의 값들은 변경이 가능하다.
		
		String sql = " SELECT A.i_board, A.title, A.hits, A.i_user, A.r_dt, B.nm "
				+ " FROM t_board5 A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " ORDER BY i_board DESC " ;
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					String nm = rs.getNString("nm");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setNm(nm);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					
					list.add(vo);
				}
				return 1;
			}
		});
		return list;
	}
	
	public static int insBoard(BoardVO param) {
		
		String sql = " INSERT INTO t_board5 "
				+ " (i_board, title, ctnt, i_user) "
				+ " VALUES "
				+ " (seq_board5.nextval, ?, ?, ?) " ;
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}	
		});
	}
	
	public static BoardVO selBoard(BoardVO param) {
		BoardVO vo = new BoardVO();
		String sql = " SELECT A.title, A.i_user, A.ctnt, A.r_dt, A.hits, B.nm, A.i_board "
				+ " FROM t_board5 A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE i_board = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					String title = rs.getNString("title");
					int i_board = rs.getInt("i_board");
					int i_user = rs.getInt("i_user");
					String ctnt = rs.getNString("ctnt");
					String r_dt = rs.getNString("r_dt");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					
					vo.setTitle(title);
					vo.setI_board(i_board);
					vo.setI_user(i_user);
					vo.setCtnt(ctnt);
					vo.setR_dt(r_dt);
					vo.setHits(hits);
					vo.setNm(nm);
				}
				return 1;
			}
		});
		return vo;
	}
	
	public static void updBoard(BoardVO param) {
		String sql = " UPDATA SET title = ?, ctnt = ? "
				+ " WHERE i_board5 = ? ";
				
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
			}
		});
	}
	
	public static int delBoard(BoardVO param) {
		String sql = " DELETE FROM t_board5 "
				+ " WHERE i_board = ? AND i_user = ? ";
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user()); // 작성자가 작성자의 글만 지울 수 있게 하기 위해 필요하다.
			}
		});
		return 1;
	}
}
