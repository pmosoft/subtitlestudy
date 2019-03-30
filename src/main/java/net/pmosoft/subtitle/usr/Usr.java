package net.pmosoft.subtitle.usr;

public class Usr {

	private String usrId   ;
	private String usrEmail;
	private String usrPw   ;
	private String usrPw2  ;
	private String usrNm   ;
	private int    usrAge  ;
	private String useYn   ;
	private String regUsrId;
	private String updUsrId;

	// 엔티티
  	private String flangCd  ; // 외국어코드
  	private String mlangCd  ; // 모국어코드


	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getUsrPw() {
		return usrPw;
	}
	public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	}
	public String getUsrPw2() {
		return usrPw2;
	}
	public void setUsrPw2(String usrPw2) {
		this.usrPw2 = usrPw2;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public int getUsrAge() {
		return usrAge;
	}
	public void setUsrAge(int usrAge) {
		this.usrAge = usrAge;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegUsrId() {
		return regUsrId;
	}
	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}
	public String getUpdUsrId() {
		return updUsrId;
	}
	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}

	public String getFlangCd() {
		return flangCd;
	}

	public void setFlangCd(String flangCd) {
		this.flangCd = flangCd;
	}

	public String getMlangCd() {
		return mlangCd;
	}

	public void setMlangCd(String mlangCd) {
		this.mlangCd = mlangCd;
	}


}
