package kr.co.nadoexhibit.member.dto;

public class MemberDTO {

	private int memno; //회원 번호
	private String memId; //회원 아이디
	private String memEmail; //회원 이메일주소
	private String memPass; //회원 비밀번호
	private boolean isCheckTerms; //회원 약관동의
	private String joinDate; //가입일
	private String modiDate; //탈퇴일
	
	public MemberDTO() {
	}

	public MemberDTO(int memno, String memId, String memEmail, String memPass, boolean isCheckTerms, String joinDate,
			String modiDate) {
		super();
		this.memno = memno;
		this.memId = memId;
		this.memEmail = memEmail;
		this.memPass = memPass;
		this.isCheckTerms = isCheckTerms;
		this.joinDate = joinDate;
		this.modiDate = modiDate;
	}

	public int getMemno() {
		return memno;
	}

	public void setMemno(int memno) {
		this.memno = memno;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public boolean isCheckTerms() {
		return isCheckTerms;
	}

	public void setCheckTerms(boolean isCheckTerms) {
		this.isCheckTerms = isCheckTerms;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getModiDate() {
		return modiDate;
	}

	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [memno=" + memno + ", memId=" + memId + ", memEmail=" + memEmail + ", memPass=" + memPass
				+ ", isCheckTerms=" + isCheckTerms + ", joinDate=" + joinDate + ", modiDate=" + modiDate + "]";
	}

	

	
}
