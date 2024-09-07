package kr.co.nadoexhibit.main;

import java.util.List;
import java.util.Scanner;

import kr.co.nadoexhibit.aboutceo.dao.CeoDAO;
import kr.co.nadoexhibit.aboutceo.dto.CeoDTO;
import kr.co.nadoexhibit.aboutcompany.dao.CompanyDAO;
import kr.co.nadoexhibit.aboutcompany.dto.CompanyDTO;
import kr.co.nadoexhibit.board.dao.ExhibitionBoardDAO;
import kr.co.nadoexhibit.board.dao.ExhibitionCommentDAO;
import kr.co.nadoexhibit.consoleview.ConsoleView;
import kr.co.nadoexhibit.customer.dao.CustomerCenterDAO;
import kr.co.nadoexhibit.event.EventDAO;
import kr.co.nadoexhibit.member.dao.MemberDAO;
import kr.co.nadoexhibit.member.dto.MemberDTO;
//2024년 9월 7일 마감
public class NadoMain {

	public static void main(String[] args) {
		List<MemberDTO> loginMember = null;
		Scanner sc = new Scanner(System.in);
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = new MemberDTO();
		CompanyDTO cDto = new CompanyDTO();
		CompanyDAO cDao = new CompanyDAO();
		CeoDTO ceoDto = new CeoDTO();
		CeoDAO ceoDAO = new CeoDAO();
		ExhibitionBoardDAO edao = new ExhibitionBoardDAO();
		ExhibitionCommentDAO ecDao = new ExhibitionCommentDAO();
		CustomerCenterDAO cdao = new CustomerCenterDAO();
		EventDAO eventDao = new EventDAO();
		ConsoleView cv = new ConsoleView();
		
		while (true) {
			cv.mainConsoleView(loginMember);
			String userInput = sc.nextLine();
			
			switch (userInput) {
			case "1":
				cv.instroduceCompanyAndCeo();
				userInput = sc.nextLine();
				if (userInput.equals("0")) {

				} else if (userInput.equals("1")) {
					cDao.instoductionComapny();

				} else if (userInput.equals("2")) {
					ceoDAO.instoductionCeo();


				} else if (userInput.equals("11")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						cDao.createCompanyInstroduction(cDto);

					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if (userInput.equals("12")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						cDao.updateCompanyInstroduction(cDto);
					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if (userInput.equals("13")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						ceoDAO.createCeoInstroduction(ceoDto);
					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if (userInput.equals("14")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						ceoDAO.updateCeoInstroduction(ceoDto);
					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				}
				break;
				
			case "2":
				cv.amatureExhibitPrintConsolView();
				
				userInput = sc.nextLine();
				if(userInput.equals("1")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						edao.createPost(loginMember);

					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if(userInput.equals("2")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						edao.boardListPrint(loginMember);

					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if(userInput.equals("3")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						edao.searchExhibitionBoard(loginMember);
					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				} else if(userInput.equals("4")) {
					if (loginMember != null && !loginMember.isEmpty()) {
						//상위 전시관 3개
						edao.topRankingboardListPrint(loginMember);
					} else {
						System.out.println("로그인되어 있지 않습니다.");
					}
				}
				
				break;
				
			case "3":
				if (loginMember != null && !loginMember.isEmpty()) {
					System.out.println("1.전체 댓글 조회 | 2. 내가좋아요 한 게시글 조회");
					userInput = sc.nextLine();
					if(userInput.equals("1")) {
						ecDao.getAllCommentsByBoard();
					} else if(userInput.equals("2")) {
						edao.findAllMyLikesByExhibitionBoard(loginMember);
					}

				} else {
					System.out.println("로그인되어 있지 않습니다.");
				}
				break;
			case "4":
				if (loginMember != null && !loginMember.isEmpty()) {
					cv.enterEventPageView(sc, userInput, eventDao);

				} else {
					System.out.println("로그인되어 있지 않습니다.");
				}
				break;
				
			case "5":
				cdao.findMemberEamil();
				break;
				
			case "11":
				loginMember = mDao.login(mDto);
				break;
				
			case "12":
				if (loginMember != null && !loginMember.isEmpty()) {
					loginMember = mDao.logOut(mDto);
				} else {
					System.out.println("로그인되어 있지 않습니다.");
				}
				break;
				
			case "13":
				if (loginMember != null && !loginMember.isEmpty()) {
					System.out.println("로그인이 되어 있을 경우 회원가입을 할 수 없습니다.");
				} else {
					mDao.joinMember(mDto);
				}
				break;
				
			case "14":
				System.out.println("종료 하려면 Q를 입력하세요.");
				System.out.print("> ");
				userInput = sc.nextLine();
				if (userInput.toUpperCase().equals("Q")) {
					System.out.println("프로그램을 종료 합니다.");
					System.exit(0);
				} else {
					System.out.println("프로그램을 종료 하려면 Q를 입력하세요.");
				}
			}
		}
	}
}
