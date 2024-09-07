package kr.co.nadoexhibit.board.dto;

public class ExhibitionBoardDTO {
	private int boardNo; //게시판 번호
	private String memId; //회원 아이디
	private String memEmail; //회원 이메일 주소(PK)
	private String title;  // 게시판 제목
	private String picture; // 게시판 사진
	private String detail; // 게시판 내용
	private String isLike; // 게시판 좋아요
	private String joinDate; //작성일
	private String modiDate; //수정일
	private int likesCount; //좋아요 게시글 숫자
	
	public ExhibitionBoardDTO() {
	}

	public ExhibitionBoardDTO(int boardNo, String memId, String memEmail, String title, String picture, String detail,
			String like, String joinDate, String modiDate) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.memEmail = memEmail;
		this.title = title;
		this.picture = picture;
		this.detail = detail;
		this.isLike = like;
		this.joinDate = joinDate;
		this.modiDate = modiDate;
	}
	
	public ExhibitionBoardDTO(int boardNo, String memId, String memEmail, String title, String picture, String detail,
			String like, String joinDate, String modiDate, int likes) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.memEmail = memEmail;
		this.title = title;
		this.picture = picture;
		this.detail = detail;
		this.isLike = like;
		this.joinDate = joinDate;
		this.modiDate = modiDate;
		this.likesCount = likes;
	}

	public String getIsLike() {
		return isLike;
	}

	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLike() {
		return isLike;
	}

	public void setLike(String like) {
		this.isLike = like;
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
		return "ExhibitionBoardDTO [boardNo=" + boardNo + ", memId=" + memId + ", memEmail=" + memEmail + ", title="
				+ title + ", picture=" + picture + ", detail=" + detail + ", isLike=" + isLike + ", joinDate="
				+ joinDate + ", modiDate=" + modiDate + ", likes=" + likesCount + "]";
	}


	
	
}
