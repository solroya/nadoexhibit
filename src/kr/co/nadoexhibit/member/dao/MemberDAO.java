package kr.co.nadoexhibit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.common.DBManager;
import kr.co.nadoexhibit.member.dto.MemberDTO;

public class MemberDAO {

	// 회원 가입
	public void joinMember(MemberDTO mDto) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			printAgreeTerms();
			String userInput = sc.nextLine();
			if (userInput.equals("Y") || userInput.equals("y")) {
				mDto.setCheckTerms(true);
				System.out.print("회원 아이디: ");
				mDto.setMemId(sc.nextLine());
				System.out.print("회원 이메일: ");
				mDto.setMemEmail(sc.nextLine());
				System.out.print("비밀 번호: ");
				mDto.setMemPass(sc.nextLine());
				System.out.print("비밀 번호를 다시입력하세요: ");
				String tempPass = sc.nextLine();
				if (checkEmail(mDto.getMemEmail())) {// 이메일 주소 중복여부 조회(DB)
					System.out.println("이메일 주소가 중복 됩니다.");
				} else if (!mDto.getMemPass().toString().equals(tempPass)) { // 비밀번호 일치 여부 검토
					System.out.println("비밀 번호가 일치 하지 않습니다.");
				} else if (tempPass.length() < 8 || tempPass.length() > 20) {
					System.err.println("비밀번호는 최소 8자부터 최대 20자 이내로 입력해주세요");
				} else if (PasswordValidator(tempPass)) {
					System.out.println("비밀번호는 특수문자를 포함하여야 합니다.");
				} else {
					System.out.println("비밀번호 무결성 통과"); // 비밀번호 무결성 통과
					String sql = "INSERT INTO member VALUES(SEQ_MEMNO.NEXTVAL, ?, ?, ?, ?, SYSDATE, NULL)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mDto.getMemId());
					pstmt.setString(2, mDto.getMemEmail());
					pstmt.setString(3, mDto.getMemPass());
					pstmt.setString(4, "Y");

					result = pstmt.executeUpdate();

					if (result > 0) {
						System.out.println("정상 처리되었습니다.");
					} else {
						System.out.println("요청이 실패하였습니다.");
					}
				}
			} else {
				System.out.println("약관에 동의해야 이용 가능합니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 로그인
	public List<MemberDTO> login(MemberDTO mDto) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MemberDTO> memList = new ArrayList<MemberDTO>();

		try {
			conn = DBManager.getConnection();
			
			System.out.print("회원 아이디: ");
			mDto.setMemId(sc.nextLine());
			System.out.print("회원 비밀번호: ");
			mDto.setMemPass(sc.nextLine());
			String sql = "SELECT * FROM member WHERE memid=? AND mempass =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getMemId());
			pstmt.setString(2, mDto.getMemPass());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int memno = rs.getInt("memno");
				String memId = rs.getString("memid");
				String memEmail = rs.getString("mememail");
				String memPass = rs.getString("mempass");
				boolean isCheckTerms = true;
				String joinDate = rs.getString("joindate");
				String modiDate = rs.getString("modidate");
				MemberDTO loginMember = new MemberDTO(memno, memId, memEmail, memPass, isCheckTerms, joinDate,
						modiDate);
				memList.add(loginMember);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
//		System.out.println(memList.toString());
		if (memList.isEmpty()) {
			System.out.println("로그인이 처리 되지 않았습니다.");
		}
		else {
			System.err.println(memList.get(0).getMemId() + "님 반갑습니다.");
		}
		return memList;
	}

	// 로그 아웃
	public List<MemberDTO> logOut(MemberDTO mDto) {
		List<MemberDTO> memList = new ArrayList<MemberDTO>();
		memList = null;
		System.err.println("로그아웃 되었습니다.");
		return memList;
	}

	// Email 주소 중복여부 검토
	public boolean checkEmail(String memEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM member WHERE mememail=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memEmail);
			result = pstmt.executeUpdate();

			if (result > 0) {
				check = true;
			} else {
				check = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return check;
	}

	// 비밀번호 정규식(구현 안됨 오류 있음)
	private boolean PasswordValidator (String pass) {
		final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";
	    
		if(!pass.matches("(?=.*[~!@#$%^&*+=()_-])")) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void printAgreeTerms() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("나도전시 회원 약관");
		System.out.println("나도전시는 개인이 무료로 온라인 전시전을 개최할 수 있는 서비스입니다.");
		System.out.println("표준약관을 준수 하세요.");
		System.out.println("나도전시 회원 약관에 동의해야 가입이 가능합니다.");
		System.out.print("약관에 동의 하시나요? (y/n): ");
	}

}

