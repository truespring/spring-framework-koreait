package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.board.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList(int i_board) {
		final List<BoardVO> list = new ArrayList();
		// 주소값을 고정시키는 것이고 내부의 값들은 변경이 가능하다.
		
//		String sql = " SELECT A.i_board, A.title, A.hits, A.i_user, A.r_dt, B.nm "
////				+ ", (SELECT count(*) FROM t_board5_cmt WHERE i_board = ? ) as cmt_cnt "
//				+ " FROM t_board5 A "
//				+ " INNER JOIN t_user B "
//				+ " ON A.i_user = B.i_user "
////				+ " LEFT JOIN ( SELECT count(i_board) as cmt_cnt "
////				+ " FROM t_board5_cmt GROUP BY i_board) C "
//				+ " ORDER BY i_board DESC " ;
		
		String sql = " SELECT A.i_board, A.title, A.hits, A.i_user, A.r_dt, B.nm, C.cmt_cnt " + 
				" FROM t_board5 A " + 
				" INNER JOIN t_user B " + 
				" ON A.i_user = B.i_user " + 
				" LEFT JOIN (" + 
				" SELECT i_board, count(i_board) as cmt_cnt " + 
				" FROM t_board5_cmt\r\n" + 
				" group by i_board " + 
				" )C " + 
				" ON A.i_board = C.i_board " + 
				" ORDER BY A.i_board DESC ";
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					String nm = rs.getNString("nm");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					int cmt_cnt = rs.getInt("cmt_cnt");
					
					BoardDomain vo = new BoardDomain();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setNm(nm);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					vo.setCmt_cnt(cmt_cnt);
					
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
	
	public static void addHits(int i_board) {
		BoardVO vo = new BoardVO();
		String sql = " UPDATE t_board5 SET hits = hits + 1 "
				+ " WHERE i_board = ? ";
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}
			
		});
	}
	
	public static BoardDomain selBoard(BoardVO param) {
		BoardDomain vo = new BoardDomain();
		String sql = " SELECT A.title, A.i_user, A.ctnt, A.r_dt, A.hits, B.nm, A.i_board, DECODE(C.i_user, null, 0, 1) as yn_like, "
				+ " (SELECT count(*) FROM t_board5_like WHERE i_board = ?) as like_cnt "
				+ " FROM t_board5 A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " LEFT JOIN t_board5_like C "
				+ " ON A.i_board = C.i_board "
				+ " AND C.i_user = ? "
				+ " WHERE A.i_board = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setInt(3, param.getI_board());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					String title = rs.getNString("title");
					int i_board = rs.getInt("i_board");
					int i_user = rs.getInt("i_user"); // 작성자 i_user
					String ctnt = rs.getNString("ctnt");
					String r_dt = rs.getNString("r_dt");
					int like_cnt = rs.getInt("like_cnt");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					int yn_like = rs.getInt("yn_like");
					
					vo.setTitle(title);
					vo.setI_board(i_board);
					vo.setI_user(i_user);
					vo.setCtnt(ctnt);
					vo.setR_dt(r_dt);
					vo.setLike_cnt(like_cnt);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setYn_like(yn_like);
				}
				return 1;
			}
		});
		return vo;
	}
	
	public static int updBoard(BoardVO param) {
		String sql = " UPDATE t_board5 SET title = ?, ctnt = ? "
				+ " WHERE i_board = ? AND i_user = ? ";
				
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
			}
		});
		return 1;
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
	// 좋아요 기능
	public static int likeBoard(BoardVO param) {
		String sql = " INSERT INTO t_board5_like " + 
				" (i_user, i_board) " + 
				" VALUES " + 
				" (?, ?) " ;
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());				
			}			
		});
	}
	// 좋아요 취소 기능
	public static int dislikeBoard(BoardVO param) {
		String sql = " DELETE FROM t_board5_like "
				+ " WHERE i_user = ? AND i_board = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
}
