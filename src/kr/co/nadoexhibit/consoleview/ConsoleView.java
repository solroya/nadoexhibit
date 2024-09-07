package kr.co.nadoexhibit.consoleview;

import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.event.EventDAO;
import kr.co.nadoexhibit.member.dto.MemberDTO;

public class ConsoleView {

	//NadoMain
	public void instroduceCompanyAndCeo() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-                 1.회사 소개 | 2.대표 소개                             -");
		System.out.println("-*******관리자 기능: 11. 회사 정보 | 12. 회사 정보 수정          *******-");
		System.out.println("-*******관리자 기능: 13. 대표 정보 | 14. 대표 정보 수정          *******-");
		System.out.println("-------------------------------------------------------------------------");
		System.out.print("> ");
	}
	
	public void mainConsoleView(List<MemberDTO> loginMember) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-                           NadoExhibit                                 -");
		if (loginMember != null && !loginMember.isEmpty()) {
			System.out.println("-                                                   " + "             "
					+ loginMember.get(0).getMemId() + "님-");
		} else {
			System.out.println("-                                                                Guest님-");
		}
		System.out.println("-                                                                       -");
		System.out.println("-      1.회사소개 | 2.아마추어 전시관 | 3.전시리뷰 | 4.이벤트 | 5.고객센터          -");
		System.out.println("-                                                                       -");
		System.out.println("-           11.로그인 | 12.로그아웃 | 13.회원가입 | 14.종료                    -");
		System.out.println("-------------------------------------------------------------------------");
		System.out.print("> ");
	}
	
	public void amatureExhibitPrintConsolView() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-                      아마추어 전시관                                      -");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-1.전시관 만들기 | 2.전시관 구경하기 | 3.전시관 검색  | 4.인기 게시판               -");
		System.out.println("-------------------------------------------------------------------------");
		System.out.print("> ");
	}
	
	//4. 이벤트 페이지 뷰
	public void enterEventPageView(Scanner sc, String userInput, EventDAO eventDao) {
		System.out.println("1. 모든 게시글(댓글 포함) | 2. 댓글 있는 게시글");
		System.out.print("> ");
		userInput = sc.nextLine();
		if(userInput.equals("1")) {
			eventDao.printBoardAllComment();
		} else if(userInput.equals("2")) {
			eventDao.printBoardExistComment();
		}
		
	}
	
	
	// 공통 모듈
	public void completeMessageToEnter(Scanner sc) {
		if(sc.hasNext()) {
			sc.nextLine(); //버퍼에 남은 개행 문자 제거
		}
		System.out.println("다음 창으로 넘어가려면, 엔터를 누르세요");
		sc.nextLine();
	}
	
	public void endQueryResultMessage(int result) {
		if (result > 0) {
			System.out.println("정상 처리되었습니다.");
		} else {
			System.out.println("요청이 실패하였습니다.");
		}
	}
	

}
