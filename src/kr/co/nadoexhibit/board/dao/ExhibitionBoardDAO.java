package kr.co.nadoexhibit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.board.dto.ExhibitionBoardDTO;
import kr.co.nadoexhibit.board.dto.ExhibitionCommentDTO;
import kr.co.nadoexhibit.board.dto.ResultofFindMyLikes;
import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.consoleview.ConsoleView;
import kr.co.nadoexhibit.member.dto.MemberDTO;

public class ExhibitionBoardDAO {

	// 전시관 만들기
	public void createPost(List<MemberDTO> loginMember) {
		ExhibitionBoardDTO eb = new ExhibitionBoardDTO();
		ConsoleView cv = new ConsoleView();
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();

			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                      아마추어 전시관                                  -");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("-제목>  ");
			eb.setTitle(sc.nextLine());
			System.out.print("-내용>  ");
			eb.setDetail(sc.nextLine());
			System.out.print("-사진>  ");
			eb.setPicture(sc.nextLine());
			if(eb.getTitle().isEmpty() || eb.getDetail().isEmpty() || eb.getPicture().isEmpty()) {
				System.out.println("등록을 위한 값을 입력해야 합니다.");
				System.out.println("엔터를 눌러주세요.");
				sc.nextLine();
				return;
			}
			String sql = "INSERT INTO exhibitionboard VALUES(SEQ_BOARDNO.NEXTVAL, ?, ?, ?, ?, ?, 'N', SYSDATE, NULL, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.get(0).getMemEmail());
			pstmt.setString(2, loginMember.get(0).getMemId());
			pstmt.setString(3, eb.getTitle());
			pstmt.setString(4, eb.getPicture());
			pstmt.setString(5, eb.getDetail());
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
	
	// 전시관 전체 목록 출력
	public void boardListPrint(List<MemberDTO> loginMember) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                     아마추어 전시관 읽기 모드                         -");
			System.out.println("-------------------------------------------------------------------------");
			System.out.printf("%-6s %-12s %-20s %-16s %-16s %-6s\n", "no", "게시자", "제목", "작성일","수정일", "좋아요");
			System.out.println("-------------------------------------------------------------------------");
			
			String sql = "SELECT boardno, memid, title, joindate, modidate, likes_count " +
					 	 "FROM exhibitionboard " +
						 "ORDER BY boardno DESC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExhibitionBoardDTO eb = new ExhibitionBoardDTO();
				eb.setBoardNo(rs.getInt("boardno"));
				eb.setMemId(rs.getString("memid"));
				eb.setTitle(rs.getString("title"));
				eb.setJoinDate(rs.getString("joindate"));
				eb.setModiDate(rs.getString("modidate"));
				eb.setLikesCount(rs.getInt("likes_count"));
				System.out.printf("%-6s %-12s %-20s %-16s %-16s %-6s\n",
						eb.getBoardNo(),
						eb.getMemId(),
						eb.getTitle(),
						eb.getJoinDate(),
						eb.getModiDate(),
						eb.getLikesCount()
						);
			}
			System.out.println("-------------------------------------------------------------------------");
			selectedExhibitionByboardNo(loginMember);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	// 전시관 세부 조회(로그인 객체 주입)
	public void selectedExhibitionByboardNo(List<MemberDTO> loginMember) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			
			conn = DBManager.getConnection();
			System.out.print("게시글 선택> ");
			String temp = sc.nextLine();
			if(temp.equals("")) {
				System.out.println("올바른 게시글을 선택해주세요.");
				return;
			}
			int boardNo = Integer.parseInt(temp);

			String sql = "SELECT boardno, mememail, memid, title, picture, detail, joindate, modidate, likes_count " +
				 	 "FROM exhibitionboard " +
					 "WHERE boardno =?" +
					 "ORDER BY boardno DESC";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			String boardMemEmail = null;
			while (rs.next()) {
				boardMemEmail = rs.getString("mememail");
				System.out.println("#########################################################################");
				System.out.println("no : " + rs.getInt("boardno"));
				System.out.println("게시자 : " + rs.getString("memid"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("사진 : " + rs.getString("picture"));
				System.out.println("설명 : " + rs.getString("detail"));
				System.out.println("작성일 : " + rs.getString("joindate"));
				System.out.println("수정일 : " + rs.getString("modidate"));
				System.out.println("좋아요 : " + rs.getInt("likes_count"));
				System.out.println("#########################################################################");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("1.수정하기 | 2. 삭제하기 | 3.댓글달기 | 4.댓글조회 | 5.좋아요 | 6.좋아요 취소");
				String userInput = sc.nextLine();
				ExhibitionCommentDAO ecDao = new ExhibitionCommentDAO();
				ExhibitionCommentDTO ecDto = new ExhibitionCommentDTO();
				List<ExhibitionCommentDTO> comments = new ArrayList<>();
				
				if(userInput.equals("1")) {
						if(loginMember.get(0).getMemEmail().equals(boardMemEmail)) {
							ExhibitionBoardDTO edto = new ExhibitionBoardDTO();
							System.out.print("제목> ");
							edto.setTitle(sc.nextLine());
							System.out.print("사진> ");
							edto.setPicture(sc.nextLine());
							System.out.print("설명> ");
							edto.setDetail(sc.nextLine());
							
							sql = "UPDATE exhibitionboard SET title=?, picture=?, detail=?, modidate=SYSDATE "
									+ "WHERE boardno=?";
							
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, edto.getTitle());
							pstmt.setString(2, edto.getPicture());
							pstmt.setString(3, edto.getDetail());
							pstmt.setInt(4, boardNo);
							
							result = pstmt.executeUpdate();
							if (result > 0) {
								System.out.println("정상 처리되었습니다.");
							} else {
								System.out.println("요청이 실패하였습니다.");
							}
						} else {
							System.out.println("작성자만 수정할 수 있습니다.");
						}
						
					} else if(userInput.equals("2")) {
						if(loginMember.get(0).getMemEmail().equals(boardMemEmail)) {
							System.out.print("y를 누르면 삭제 됩니다.");
							userInput = sc.nextLine();
							if(userInput.equals("y") || userInput.equals("Y")) {
								sql = "DELETE FROM exhibitionboard WHERE boardno=?";
								
								pstmt = conn.prepareStatement(sql);
								pstmt.setInt(1, boardNo);
								
								result = pstmt.executeUpdate();
								
								if (result > 0) {
									System.out.println("정상 처리되었습니다.");
								} else {
									System.out.println("요청이 실패하였습니다.");
								}
							}
						} else {
							System.out.println("작성자만 삭제 할수 있습니다.");
						}
						
					} else if(userInput.equals("3")) {
						ecDao.addComment(ecDto, boardNo, loginMember);
					} else if(userInput.equals("4")) {
						comments = ecDao.getCommentsByBoardNo(boardNo);
						if(comments.isEmpty()) {
							System.out.println("댓글이 없습니다.");
							return;
						}
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("-                     아마추어 전시관 댓글 모드                         -");
						System.out.println("-------------------------------------------------------------------------");
						System.out.printf("%-6s %-12s %-20s %-16s %-16s\n", "no", "게시자", "제목", "작성일","수정일");
						System.out.println("-------------------------------------------------------------------------");
				
						for(int i = 0; i < comments.size(); i++) {
							System.out.printf("%-6s %-12s %-20s %-16s %-16s\n",
									comments.get(i).getCommentId(),
									comments.get(i).getMemId(),
									comments.get(i).getContent(),
									comments.get(i).getCreatedAt(),
									comments.get(i).getModidate()
									);
						}
						
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("-1. 댓글 수정 | 2. 댓글 삭제");
						System.out.print("> ");
						String commentInput = sc.nextLine();
						String verificationMemId = null;
						if(commentInput.equals("1")) {
							System.out.print("수정할 댓글 번호를 눌러주세요> ");
							int commentNo = sc.nextInt();
							sc.nextLine();

								System.out.print("변경 내용> ");
								String comment = sc.nextLine();
								if(comment.equals("")) {
									System.out.println("값을 입력하세요");
									return;
								}
								ecDao.updateComment(commentNo, comment);
								verificationMemId = null;

							
						} else if(commentInput.equals("2")) {
							System.out.print("삭제할 댓글 번호를 눌러주세요> ");
							int commentNo = sc.nextInt();
							sc.nextLine();

								ecDao.deleteComment(commentNo);
	
						}
					} else if(userInput.equals("5")) {
						addLikes(boardNo, loginMember);
					} else if(userInput.equals("6")) {
						deleteLikes(boardNo, loginMember);
					}
					
			}
			System.out.println("계속 진행하려면 ENTER를 눌러주세요");
			sc.nextLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	
	//전시관 검색 기능
	public void searchExhibitionBoard(List<MemberDTO> loginMember) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("전시관 검색 서비스 입니다.");
			System.out.println("-1. 회원명 조회 | 2. 전시관 제목 조회");
			System.out.println("입력> ");
			String userInput = sc.nextLine();
			if(userInput.equals("1")) {
				System.out.print("회원명> ");
				userInput = sc.nextLine();
				
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("-                     아마추어 전시관 검색 모드                         -");
				System.out.println("-------------------------------------------------------------------------");
				System.out.printf("%-6s %-12s %-20s %-16s %-16s\n", "no", "게시자", "제목", "작성일","수정일");
				System.out.println("-------------------------------------------------------------------------");
				
				String sql = "SELECT boardno, memid, title, joindate, modidate " +
						 	 "FROM exhibitionboard " +
						"WHERE memid like ? " +
							 "ORDER BY boardno DESC";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + userInput + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ExhibitionBoardDTO eb = new ExhibitionBoardDTO();
					eb.setBoardNo(rs.getInt("boardno"));
					eb.setMemId(rs.getString("memid"));
					eb.setTitle(rs.getString("title"));
					eb.setJoinDate(rs.getString("joindate"));
					eb.setModiDate(rs.getString("modidate"));
					System.out.printf("%-6s %-12s %-15s %-10s %-10s\n",
							eb.getBoardNo(),
							eb.getMemId(),
							eb.getTitle(),
							eb.getJoinDate(),
							eb.getModiDate()
							);
				}
				selectedExhibitionByboardNo(loginMember);
			} else if(userInput.equals("2")) {
				System.out.print("전시관 제목명> ");
				userInput = sc.nextLine();
				
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("-                     아마추어 전시관 검색 모드                         -");
				System.out.println("-------------------------------------------------------------------------");
				System.out.printf("%-6s %-12s %-20s %-16s %-16s\n", "no", "게시자", "제목", "작성일","수정일");
				System.out.println("-------------------------------------------------------------------------");
				
				String sql = "SELECT boardno, memid, title, joindate, modidate " +
						 	 "FROM exhibitionboard " +
						"WHERE title like ? " +
							 "ORDER BY boardno DESC";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + userInput + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ExhibitionBoardDTO eb = new ExhibitionBoardDTO();
					eb.setBoardNo(rs.getInt("boardno"));
					eb.setMemId(rs.getString("memid"));
					eb.setTitle(rs.getString("title"));
					eb.setJoinDate(rs.getString("joindate"));
					eb.setModiDate(rs.getString("modidate"));
					System.out.printf("%-6s %-12s %-15s %-10s %-10s\n",
							eb.getBoardNo(),
							eb.getMemId(),
							eb.getTitle(),
							eb.getJoinDate(),
							eb.getModiDate()
							);
				}
				selectedExhibitionByboardNo(loginMember);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	// 좋아요 기능 메서드 구현
	// 예외처리 기능 추가(같은 게시글 중복 좋아요 불가)
	public void addLikes(int boardNo, List<MemberDTO> loginMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		Scanner sc = new Scanner(System.in);
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM likes WHERE mememail=? AND boardno=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.get(0).getMemEmail());
			pstmt.setInt(2, boardNo);
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("이미 좋아요를 누른 게시글입니다.");
			} else {
				sql = 
						"INSERT INTO likes (like_id, mememail, boardno, like_date) " +
						"VALUES (seq_like_id.NEXTVAL, ?, ?, SYSDATE)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginMember.get(0).getMemEmail());
				pstmt.setInt(2, boardNo);
				
				result = pstmt.executeUpdate();
				
				if (result > 0) {
					System.out.println("정상 처리되었습니다.");
				} else {
					System.out.println("요청이 실패하였습니다.");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//랭킹 전시관 조희
	public void topRankingboardListPrint(List<MemberDTO> loginMember) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                     아마추어 전시관 읽기 모드                         -");
			System.out.println("-------------------------------------------------------------------------");
			System.out.printf("%-6s %-6s %-10s %-16s %-16s %-16s\n", "좋아요", "전시관no", "게시자", "제목", "작성일","수정일");
			System.out.println("-------------------------------------------------------------------------");
			
			String sql = "SELECT likes_count, boardno, memid, title, joindate, modidate " +
					 	 "FROM (SELECT likes_count, boardno, memid, title, joindate, modidate " + 
					 	 		"FROM exhibitionboard ORDER BY likes_count DESC) " +
						 "WHERE ROWNUM <= 3";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExhibitionBoardDTO eb = new ExhibitionBoardDTO();
				eb.setLikesCount(rs.getInt("likes_count"));
				eb.setBoardNo(rs.getInt("boardno"));
				eb.setMemId(rs.getString("memid"));
				eb.setTitle(rs.getString("title"));
				eb.setJoinDate(rs.getString("joindate"));
				eb.setModiDate(rs.getString("modidate"));
				System.out.printf("%-6s %-6s %-10s %-16s %-16s %-16s\n",
						eb.getLikesCount(),
						eb.getBoardNo(),
						eb.getMemId(),
						eb.getTitle(),
						eb.getJoinDate(),
						eb.getModiDate()
						);
			}
			System.out.println("-------------------------------------------------------------------------");
			selectedExhibitionByboardNo(loginMember);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	//내가 좋아요 한 게시글 조회
	public void findAllMyLikesByExhibitionBoard(List<MemberDTO> loginMember) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result;
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                          내가 좋아요 한 게시글                             -");
			System.out.println("-------------------------------------------------------------------------");
			System.out.printf("%-6s %6s %-16s %-10s %-10s\n", "bNo", "likeNo", "좋아요DAY", "게시자", "게시글");
			System.out.println("-------------------------------------------------------------------------");
			
			String sql = "SELECT l.boardno, l.like_id, l.like_date,  b.memid, b.title " +
					 	 "FROM likes l JOIN exhibitionboard b ON l.boardno = b.boardno " +
						 "WHERE l.mememail=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.get(0).getMemEmail());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResultofFindMyLikes resultLike = new ResultofFindMyLikes();
				resultLike.setBoardNo(rs.getInt("boardno"));
				resultLike.setLikeId(rs.getInt("like_id"));
				resultLike.setLikeDate(rs.getString("like_date"));
				resultLike.setBoardMemId(rs.getString("memid"));
				resultLike.setBoardTitle(rs.getString("title"));
				System.out.printf("%-6s %6s %-16s %-10s %-10s\n",
						resultLike.getBoardNo(),
						resultLike.getLikeId(),
						resultLike.getLikeDate(),
						resultLike.getBoardMemId(),
						resultLike.getBoardTitle()
						);
			}
			System.out.println("-------------------------------------------------------------------------");
			selectedExhibitionByboardNo(loginMember);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	//좋아요 삭제
	public void deleteLikes(int boardNo, List<MemberDTO> loginMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM likes WHERE mememail=? AND boardno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.get(0).getMemEmail());
			pstmt.setInt(2, boardNo);
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상 처리되었습니다.");
			} else {
				System.out.println("요청이 실패하였습니다.");
			}
//			System.out.println("계속하려면 엔터를 누르세요");
//			sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}
