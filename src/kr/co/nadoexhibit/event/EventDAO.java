package kr.co.nadoexhibit.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import kr.co.nadoexhibit.board.dto.PrintBoardAllCommnetDTO;
import kr.co.nadoexhibit.common.DBManager;

public class EventDAO {

	// 전체 게시글 조회(댓글 없는 게시글 포함 / LEFT JOIN)
	public void printBoardAllComment() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			printExhibitionPage();
			String sql = "SELECT "
					+ "b.boardno, b.memid AS board_memid, b.title, "
					+ "c.memid AS comment_memid, c.content "
					+ "FROM exhibitionboard b "
					+ "LEFT JOIN exhibitioncomment c "
					+ "ON b.boardno = c.boardno "
					+ "ORDER BY b.boardno, c.created_at";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			PrintBoardAllCommnetDTO printDTO = new PrintBoardAllCommnetDTO();
			
			printDTO.setBoardNo(rs.getInt("boardno"));
			printDTO.setBoardMemId(rs.getString("board_memid"));;
			printDTO.setTitle(rs.getString("title"));
			printDTO.setCommentMemId(rs.getString("comment_memid"));
			printDTO.setCommentContent(rs.getString("content"));
			
			System.out.printf("%-6s %-12s %-20s %-16s %-16s\n",
					printDTO.getBoardNo(),
					printDTO.getBoardMemId(),
					printDTO.getTitle(),
					printDTO.getCommentMemId(),
					printDTO.getCommentContent()
					);
			}
			System.out.println("계속 하려면 엔터를 누르세요");
			sc.nextLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 전체 게시글 조회(댓글 있는 게시글 / INNER JOIN)
	public void printBoardExistComment() {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			printExhibitionPage();
			
			String sql = "SELECT "
					+ "b.boardno, b.memid AS board_memid, b.title, "
					+ "c.memid AS comment_memid, c.content "
					+ "FROM exhibitionboard b "
					+ "INNER JOIN exhibitioncomment c "
					+ "ON b.boardno = c.boardno "
					+ "ORDER BY b.boardno, c.created_at";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			PrintBoardAllCommnetDTO printDTO = new PrintBoardAllCommnetDTO();
			
			printDTO.setBoardNo(rs.getInt("boardno"));
			printDTO.setBoardMemId(rs.getString("board_memid"));;
			printDTO.setTitle(rs.getString("title"));
			printDTO.setCommentMemId(rs.getString("comment_memid"));
			printDTO.setCommentContent(rs.getString("content"));
			
			System.out.printf("%-6s %-12s %-20s %-16s %-16s\n",
					printDTO.getBoardNo(),
					printDTO.getBoardMemId(),
					printDTO.getTitle(),
					printDTO.getCommentMemId(),
					printDTO.getCommentContent()
					);
			}
			System.out.println("계속 하려면 엔터를 누르세요");
			sc.nextLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
	}
	public void printExhibitionPage() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-                     게시판 읽기 모드                                      -");
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("%-6s %-12s %-20s %-16s %-16s\n", "bNo", "게시자", "제목", "댓글ID","댓글내용");
		System.out.println("-------------------------------------------------------------------------");

	}
}
