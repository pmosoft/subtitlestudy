package net.pmosoft.subtitle.subtitle;

import java.sql.Date;

public class UsrSttlVo {

  	private String usrId    ; // 사용자아이디    
	private String sttlNm   ; // 자막명      
	private String fsttlNm  ; // 외국어자막명      
	private String msttlNm  ; // 모국어자막명
	
	private String sttlCd   ; //자막구분(1:외국어,2:모국어) 
	private String sttlStm  ; //자막시작시각      	 
	private String sttlEtm  ; //자막종료시각      	 
	private String sttlDesc ; //자막문장내용	 
	
	private Date   regDtm   ; // 등록일시
	private String regUsrId ; // 등록자
	private Date   updDtm   ; // 변경일시
	private String updUsrId ; // 변경자
	
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getSttlNm() {
		return sttlNm;
	}
	public void setSttlNm(String sttlNm) {
		this.sttlNm = sttlNm;
	}
	public String getFsttlNm() {
		return fsttlNm;
	}
	public void setFsttlNm(String fsttlNm) {
		this.fsttlNm = fsttlNm;
	}
	public String getMsttlNm() {
		return msttlNm;
	}
	public void setMsttlNm(String msttlNm) {
		this.msttlNm = msttlNm;
	}
	public String getSttlCd() {
		return sttlCd;
	}
	public void setSttlCd(String sttlCd) {
		this.sttlCd = sttlCd;
	}
	public String getSttlStm() {
		return sttlStm;
	}
	public void setSttlStm(String sttlStm) {
		this.sttlStm = sttlStm;
	}
	public String getSttlEtm() {
		return sttlEtm;
	}
	public void setSttlEtm(String sttlEtm) {
		this.sttlEtm = sttlEtm;
	}
	public String getSttlDesc() {
		return sttlDesc;
	}
	public void setSttlDesc(String sttlDesc) {
		this.sttlDesc = sttlDesc;
	}
	public Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}
	public String getRegUsrId() {
		return regUsrId;
	}
	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}
	public Date getUpdDtm() {
		return updDtm;
	}
	public void setUpdDtm(Date updDtm) {
		this.updDtm = updDtm;
	}
	public String getUpdUsrId() {
		return updUsrId;
	}
	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}
}
