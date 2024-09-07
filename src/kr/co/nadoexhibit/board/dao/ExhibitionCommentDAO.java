package kr.co.nadoexhibit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.board.dto.ExhibitionCommentDTO;
import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.member.dto.MemberDTO;

public class ExhibitionCommentDAO {

	// 댓글 기능
	public void addComment(ExhibitionCommentDTO comment, int boardNo, List<MemberDTO> loginMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		Scanner sc = new Scanner(System.in);

		try {
			conn = DBManager.getConnection();
			System.out.print("댓글 내용> ");
			comment.setContent(sc.nextLine());
			

			String sql = "INSERT INTO exhibitioncomment(comment_id, boardno, memid, content, created_at) "+
			" VALUES (seq_comment_id.NEXTVAL, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, loginMember.get(0).getMemId());
			pstmt.setString(3, comment.getContent());

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상 처리되었습니다.");
			} else {
				System.out.println("요청이 실패하였습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시글 댓글 목록 조회
	public List<ExhibitionCommentDTO> getCommentsByBoardNo(int boardNo) {
		List<ExhibitionCommentDTO> comments = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT comment_id, boardno, memid, content, created_at, modidate "
					+ "FROM exhibitioncomment WHERE boardno = ? " + "ORDER BY comment_id ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExhibitionCommentDTO comment = new ExhibitionCommentDTO();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setBoardNo(rs.getInt("boardno"));
				comment.setMemId(rs.getString("memid"));
				comment.setContent(rs.getString("content"));
				comment.setCreatedAt(rs.getString("created_at"));
				comment.setModidate(rs.getString("modidate"));
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return comments;
	}

	// 댓글 삭제
	public void deleteComment(int commentId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM exhibitioncomment WHERE comment_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentId);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상 처리되었습니다.");
			} else {
				System.out.println("요청이 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 댓글 수정
	public void updateComment(int commentId, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE exhibitioncomment SET content = ?, modidate = SYSDATE WHERE comment_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, commentId);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("댓글이 수정되었습니다.");
			} else {
				System.out.println("댓글 수정에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 댓글 전체 목록 조회(전시리뷰)
		public void getAllCommentsByBoard() {
			System.out.println("전시리뷰 메소드가 실행됨");
			List<ExhibitionCommentDTO> comments = new ArrayList<>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Scanner sc = new Scanner(System.in);

			try {
				conn = DBManager.getConnection();
				String sql = "SELECT comment_id, boardno, memid, content, created_at, modidate "
						+ "FROM exhibitioncomment " + "ORDER BY comment_id ASC";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				System.out.println("-------------------------------------------------------------------------");
				System.out.println("-                       아마추어 전시관 전시 리뷰 모드                         -");
				System.out.println("-------------------------------------------------------------------------");
				System.out.printf("%-6s %-6s %-12s %-20s %-16s \n", "댓글no", "게시글no", "게시자", "제목", "작성일");
				System.out.println("-------------------------------------------------------------------------");
				while (rs.next()) {
					ExhibitionCommentDTO comment = new ExhibitionCommentDTO();
					comment.setCommentId(rs.getInt("comment_id"));
					comment.setBoardNo(rs.getInt("boardno"));
					comment.setMemId(rs.getString("memid"));
					comment.setContent(rs.getString("content"));
					comment.setCreatedAt(rs.getString("created_at"));
					comment.setModidate(rs.getString("modidate"));
					comments.add(comment);
					System.out.printf("%-6s %-12s %-15s %-10s %-10s\n",
							comment.getCommentId(),
							comment.getBoardNo(),
							comment.getMemId(),
							comment.getContent(),
							comment.getCreatedAt()
							);
				}
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("계속하려면 엔터를 누르세요");
				sc.nextLine();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
}
