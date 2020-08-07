package com.koreait.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList() {
		List<BoardVO> list = new ArrayList();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT i_board, title FROM t_board" 
				+ " ORDER BY i_board DESC ";

		try {
			con = DbCon.getCon(); // Connection을 더이상 쳐주지 않게 하기 위해서 만들어 주었다.
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");

				BoardVO vo = new BoardVO();

				vo.setI_board(i_board);
				vo.setTitle(title);

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		return list;
	}

	public static BoardVO selBoard(BoardVO param) {
		BoardVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT i_board, title, ctnt, i_student FROM t_board"
				+ " WHERE i_board = ? ";

		try {
			con = DbCon.getCon(); // Connection을 더이상 쳐주지 않게 하기 위해서 만들어 주었다.
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board()); // 커리문을 완성하기 전에 값을 넣어야 한다.
			rs = ps.executeQuery();
// 0줄이나 한 줄을 가져오기 때문에 초기화를 한 다음 if문 안에서 객체화를 한다. 
			if (rs.next()) {
				vo = new BoardVO();
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				int i_student = rs.getInt("i_student");

				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setI_student(i_student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps, rs);
		}
		return vo;
	}
	
		

}
