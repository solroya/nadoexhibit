package kr.co.nadoexhibit.board.dto;

public class ExhibitionCommentDTO {
	private int commentId; // 댓글 번호
	private int boardNo; // 게시판 번호
	private String memId; // 회원 아이디
	private String content; // 댓글 내용
	private String createdAt; // 작성일
	private String modidate; // 수정일
	
	public ExhibitionCommentDTO() {
	}

	public ExhibitionCommentDTO(int commentId, int boardNo, String memId, String content, String createdAt,
			String modidate) {
		super();
		this.commentId = commentId;
		this.boardNo = boardNo;
		this.memId = memId;
		this.content = content;
		this.createdAt = createdAt;
		this.modidate = modidate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModidate() {
		return modidate;
	}

	public void setModidate(String modidate) {
		this.modidate = modidate;
	}

	@Override
	public String toString() {
		return "ExhibitionCommentDTO [commentId=" + commentId + ", boardNo=" + boardNo + ", memId=" + memId
				+ ", content=" + content + ", createdAt=" + createdAt + ", modidate=" + modidate + "]";
	}
	
	
}
