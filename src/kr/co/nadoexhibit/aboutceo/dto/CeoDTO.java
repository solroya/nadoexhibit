package kr.co.nadoexhibit.aboutceo.dto;

public class CeoDTO {

	private String aboutCEO; //대표 소개
	private String CEOPicture; //대표 사진
	private String instroductionCEO; //대표 소개
	
	
	public CeoDTO() {
	}


	public CeoDTO(String aboutCEO, String cEOPicture, String instroductionCEO) {
		super();
		this.aboutCEO = aboutCEO;
		CEOPicture = cEOPicture;
		this.instroductionCEO = instroductionCEO;
	}


	public String getAboutCEO() {
		return aboutCEO;
	}


	public void setAboutCEO(String aboutCEO) {
		this.aboutCEO = aboutCEO;
	}


	public String getCEOPicture() {
		return CEOPicture;
	}


	public void setCEOPicture(String cEOPicture) {
		CEOPicture = cEOPicture;
	}


	public String getInstroductionCEO() {
		return instroductionCEO;
	}


	public void setInstroductionCEO(String instroductionCEO) {
		this.instroductionCEO = instroductionCEO;
	}
	
	
}
