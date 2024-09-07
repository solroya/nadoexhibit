package kr.co.nadoexhibit.aboutceo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.aboutceo.dto.CeoDTO;
import kr.co.nadoexhibit.aboutcompany.dto.CompanyDTO;
import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.consoleview.ConsoleView;

public class CeoDAO {
	ConsoleView cv = new ConsoleView();
	Scanner sc = new Scanner(System.in);

	// 관리자- 게시판 대표 소개 글쓰기
	public void createCeoInstroduction(CeoDTO ceoDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                  관리자 기능 | 대표 소개 글쓰기                       -");
			System.out.print("-대표 이름 > ");
			String UserInput = sc.nextLine();
			ceoDto.setAboutCEO(UserInput);
			System.out.print("-대표 사진 주소 > ");
			UserInput = sc.nextLine();
			ceoDto.setCEOPicture(UserInput);
			System.out.print("-대표 소개 내용 > ");
			UserInput = sc.nextLine();
			ceoDto.setInstroductionCEO(UserInput);

			String sql = "INSERT INTO ceo VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "master");
			pstmt.setString(2, ceoDto.getAboutCEO());
			pstmt.setString(3, ceoDto.getCEOPicture());
			pstmt.setString(4, ceoDto.getInstroductionCEO());
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
	
	// 관리자- 게시판 대표 수정
	public void updateCeoInstroduction(CeoDTO ceoDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("*******            관리자 기능 | 대표 소개 수정                    ******");
			System.out.print("-대표 이름 > ");
			String UserInput = sc.nextLine();
			ceoDto.setAboutCEO(UserInput);
			System.out.print("-대표 사진 > ");
			UserInput = sc.nextLine();
			ceoDto.setCEOPicture(UserInput);
			System.out.print("-대표 소개 내용 > ");
			UserInput = sc.nextLine();
			ceoDto.setInstroductionCEO(UserInput);
			
			String sql = "UPDATE ceo " +
						 "SET aboutceo=?, ceopicture=?, instroductionceo=?" +
						 "WHERE master = master";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ceoDto.getAboutCEO());
			pstmt.setString(2, ceoDto.getCEOPicture());
			pstmt.setString(3, ceoDto.getInstroductionCEO());
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
	// 대표 소개 출력
		public void instoductionCeo() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CeoDTO> listCeo = new ArrayList<CeoDTO>();
			try {
				conn = DBManager.getConnection();
				
				String sql = "SELECT * FROM ceo WHERE master= master";

				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					CeoDTO ceoDto = new CeoDTO();
					ceoDto.setAboutCEO(rs.getString("aboutceo"));
					ceoDto.setCEOPicture(rs.getString("ceopicture"));
					ceoDto.setInstroductionCEO(rs.getString("instroductionceo"));
					listCeo.add(ceoDto);
				}
				if(listCeo != null && !listCeo.isEmpty()) {
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("1.CEO: " + listCeo.get(0).getAboutCEO());
				System.out.println("2.PICTURE: " + listCeo.get(0).getCEOPicture());
				System.out.println("3.INSTRODUCTION: " + listCeo.get(0).getInstroductionCEO());
				System.out.println("-------------------------------------------------------------------------");
				} else {
					System.out.println("CEO소개 메시지가 등록되지 않았습니다.");
					
				}
				System.out.println("다음 창으로 넘어가려면, 엔터를 누르세요");
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
}
