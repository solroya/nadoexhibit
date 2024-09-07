package kr.co.nadoexhibit.board.dto;

public class ResultofFindMyLikes {
	int boardNo; //게시판 번호
	int likeId; //좋아요 번호
	String likeDate; //좋아요 날짜
	String boardMemId; //게시판 작성자
	String boardTitle; //게시판 타이틀
	
	public ResultofFindMyLikes() {
	}

	public ResultofFindMyLikes(int boardNo, int likeId, String likeDate, String boardMemId, String boardTitle) {
		super();
		this.boardNo = boardNo;
		this.likeId = likeId;
		this.likeDate = likeDate;
		this.boardMemId = boardMemId;
		this.boardTitle = boardTitle;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public String getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

	public String getBoardMemId() {
		return boardMemId;
	}

	public void setBoardMemId(String boardMemId) {
		this.boardMemId = boardMemId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	@Override
	public String toString() {
		return "ResultofFindMyLikes [boardNo=" + boardNo + ", likeId=" + likeId + ", likeDate=" + likeDate
				+ ", boardMemId=" + boardMemId + ", boardTitle=" + boardTitle + "]";
	}
	
	
}
