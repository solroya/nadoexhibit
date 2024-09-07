package kr.co.nadoexhibit.board.dto;

public class PrintBoardAllCommnetDTO {

	private int boardNo; // 게시판 글 번호
	private String boardMemId; // 게시판 작성자
	private String title; // 게시판 글제목
	private String commentMemId; // 댓글 작성자
	private String commentContent; //댓글 내용
	
	public PrintBoardAllCommnetDTO() {
	}

	public PrintBoardAllCommnetDTO(int boardNo, String boardMemId, String title, String commentMemId,
			String commentContent) {
		super();
		this.boardNo = boardNo;
		this.boardMemId = boardMemId;
		this.title = title;
		this.commentMemId = commentMemId;
		this.commentContent = commentContent;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardMemId() {
		return boardMemId;
	}

	public void setBoardMemId(String boardMemId) {
		this.boardMemId = boardMemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentMemId() {
		return commentMemId;
	}

	public void setCommentMemId(String commentMemId) {
		this.commentMemId = commentMemId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Override
	public String toString() {
		return "PrintBoardAllCommnetDTO [boardNo=" + boardNo + ", boardMemId=" + boardMemId + ", title=" + title
				+ ", commentMemId=" + commentMemId + ", commentContent=" + commentContent + "]";
	}
	
	
}
