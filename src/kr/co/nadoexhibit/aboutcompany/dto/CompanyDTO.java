package kr.co.nadoexhibit.aboutcompany.dto;

public class CompanyDTO {
	private String aboutCompanyTitle; //회사 소개 제목
	private String companyOverview; //회사 전경 사진
	private String instroductionCompany; //회사 소개
	private String companyHistory; // 회사 연혁

	
	public CompanyDTO() {
	}


	public CompanyDTO(String aboutCompanyTitle, String companyOverview, String instroductionCompany,
			String companyHistory) {
		super();
		this.aboutCompanyTitle = aboutCompanyTitle;
		this.companyOverview = companyOverview;
		this.instroductionCompany = instroductionCompany;
		this.companyHistory = companyHistory;
	}


	public String getAboutCompanyTitle() {
		return aboutCompanyTitle;
	}


	public void setAboutCompanyTitle(String aboutCompanyTitle) {
		this.aboutCompanyTitle = aboutCompanyTitle;
	}


	public String getCompanyOverview() {
		return companyOverview;
	}


	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}


	public String getInstroductionCompany() {
		return instroductionCompany;
	}


	public void setInstroductionCompany(String instroductionCompany) {
		this.instroductionCompany = instroductionCompany;
	}


	public String getCompanyHistory() {
		return companyHistory;
	}


	public void setCompanyHistory(String companyHistory) {
		this.companyHistory = companyHistory;
	}


	@Override
	public String toString() {
		return "CompanyDTO [aboutCompanyTitle=" + aboutCompanyTitle + ", companyOverview=" + companyOverview
				+ ", instroductionCompany=" + instroductionCompany + ", companyHistory=" + companyHistory + "]";
	}

	
	
}
