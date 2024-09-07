package kr.co.nadoexhibit.aboutcompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.aboutcompany.dto.CompanyDTO;
import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.consoleview.ConsoleView;

public class CompanyDAO {
	ConsoleView cv = new ConsoleView();
	Scanner sc = new Scanner(System.in);
	// 관리자-게시판 회사 소개 글쓰기
	public void createCompanyInstroduction(CompanyDTO cDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                  관리자 기능 | 회사소개 글쓰기                        -");
			System.out.print("-회사 타이틀 제목 > ");
			String UserInput = sc.nextLine();
			cDto.setAboutCompanyTitle(UserInput);
			System.out.print("-회사 사진 주소 > ");
			UserInput = sc.nextLine();
			cDto.setCompanyOverview(UserInput);
			System.out.print("-회사 소개 내용 > ");
			UserInput = sc.nextLine();
			cDto.setInstroductionCompany(UserInput);
			System.out.print("-회사 연혁 > ");
			UserInput = sc.nextLine();
			cDto.setCompanyHistory(UserInput);
			
			String sql = "INSERT INTO company VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "master");
			pstmt.setString(2, cDto.getAboutCompanyTitle());
			pstmt.setString(3, cDto.getCompanyOverview());
			pstmt.setString(4, cDto.getInstroductionCompany());
			pstmt.setString(5, cDto.getCompanyHistory());
			result = pstmt.executeUpdate();
			
			cv.endQueryResultMessage(result);
			cv.completeMessageToEnter(sc);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	// 회사 정보 수정
	
	public void updateCompanyInstroduction(CompanyDTO cDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("*******            관리자 기능 | 회사소개 수정                     ******");
			System.out.print("-회사 타이틀 제목 > ");
			String UserInput = sc.nextLine();
			cDto.setAboutCompanyTitle(UserInput);
			System.out.print("-회사 사진 주소 > ");
			UserInput = sc.nextLine();
			cDto.setCompanyOverview(UserInput);
			System.out.print("-회사 소개 내용 > ");
			UserInput = sc.nextLine();
			cDto.setInstroductionCompany(UserInput);
			System.out.print("-회사 연혁 > ");
			UserInput = sc.nextLine();
			cDto.setCompanyHistory(UserInput);
			
			String sql = "UPDATE company " +
						 "SET aboutcompanytitle=?, companyoverview=?, instroductioncompany=?, companyhistory=?" +
						 "WHERE master = master";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cDto.getAboutCompanyTitle());
			pstmt.setString(2, cDto.getCompanyOverview());
			pstmt.setString(3, cDto.getInstroductionCompany());
			pstmt.setString(4, cDto.getCompanyHistory());
			result = pstmt.executeUpdate();
			
			cv.endQueryResultMessage(result);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	// 회사 소개 출력
	public void instoductionComapny() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CompanyDTO> listCdto = new ArrayList<CompanyDTO>();
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM company WHERE master= master";

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CompanyDTO cDto = new CompanyDTO();
				cDto.setAboutCompanyTitle(rs.getString("aboutcompanytitle"));
				cDto.setCompanyOverview(rs.getString("companyoverview"));
				cDto.setInstroductionCompany(rs.getString("instroductioncompany"));
				cDto.setCompanyHistory(rs.getString("companyhistory"));
				listCdto.add(cDto);
			}
			if(listCdto != null && !listCdto.isEmpty()) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("1.TITLE: " + listCdto.get(0).getAboutCompanyTitle());
			System.out.println("2.PICTURE: " + listCdto.get(0).getCompanyOverview());
			System.out.println("3.INSTRODUCTION: " + listCdto.get(0).getInstroductionCompany());
			System.out.println("4.HISTORY: " + listCdto.get(0).getCompanyHistory());
			System.out.println("-------------------------------------------------------------------------");
			} else {
				System.out.println("회사 소개가 등록되지 않았네요");
			}
			System.out.println("종료하려면 엔터를 누르세요");
			sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

}
