package kr.co.nadoexhibit.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.member.dto.MemberDTO;

public class CustomerCenterDAO {

	public void findMemberEamil() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String userInput;
		Scanner sc = new Scanner(System.in);
		try {
			conn = DBManager.getConnection();

			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-                                 고객센터                                -");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("-1. 아이디 찾기 | 2. 비밀번호 찾기");
			System.out.print(">");
			userInput = sc.nextLine();
			MemberDTO mdto = new MemberDTO();
			if(userInput.equals("1")) {
				System.out.print("이메일 입력: ");
				userInput = sc.nextLine();
				
				String sql = "SELECT * FROM member WHERE mememail=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userInput);
				result = pstmt.executeUpdate();
				
				if (result > 0) {
					sql = "SELECT * FROM member WHERE mememail=? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userInput);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						mdto.setMemId(rs.getString("memid")); 
					}
					System.out.println("아이디는 "+ mdto.getMemId() + "입니다.");
					System.out.println("계속하려면 엔터를 누르세요");
					sc.nextLine();
					
				} else {
					System.out.println("일치하는 회원이 없습니다.");
					System.out.println("계속하려면 엔터를 누르세요");
					sc.nextLine();
				}
			} else if(userInput.equals("2")) {
				System.out.print("아이디> ");
				String userId = sc.nextLine();
				System.out.print("이메일> ");
				String userEmail = sc.nextLine();
				
				String sql = "SELECT * FROM member WHERE memid=? AND mememail=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setString(2, userEmail);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					mdto.setMemPass(rs.getString("mempass"));
				}
				if(mdto.getMemPass() == null) {
					System.out.println("일치하는 회원을 찾을 수 없습니다.");
				} else {
					System.out.println("비밀번호는 " + mdto.getMemPass()+ "입니다.");
				}
				System.out.println("계속하려면 엔터를 누르세요");
				sc.nextLine();
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
