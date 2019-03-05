package net.pmosoft.subtitle.subtitle;

import java.sql.Date;

public class UsrSttlVo {

	// 엔티티
  	private String usrId    ; // 사용자아이디
  	private String usrEmail ; // 사용자이메일
	private String sttlNm   ; // 자막명
	private String fsttlNm  ; // 외국어자막명
	private String msttlNm  ; // 모국어자막명
	private String sttlCd   ; //자막구분(1:외국어,2:모국어)
	private int    sttlNum  ; //자막순번
	private String sttlStm  ; //자막시작시각
	private String sttlEtm  ; //자막종료시각
	private String sttlDesc ; //자막문장내용
	private String regDtm   ; // 등록일시
	private String regUsrId ; // 등록자
	private String updDtm   ; // 변경일시
	private String updUsrId ; // 변경자

	// 체크
	boolean chk;

	// 조건
	private String condSttlNum ; // 조회조건:자막순번
	private String condSttlCd  ; // 조회조건:자막구분
	private String condBookmarkYn; // 북마크

    // 리뷰
	private String fsttlDesc; // 외국어자막문장내용
	private String msttlDesc; // 모국어자막문장내용
	private int reviewCnt;    // 리뷰횟수


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

	public int getSttlNum() {
		return sttlNum;
	}

	public void setSttlNum(int sttlNum) {
		this.sttlNum = sttlNum;
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

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public String getRegUsrId() {
		return regUsrId;
	}

	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}

	public String getUpdDtm() {
		return updDtm;
	}

	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}

	public String getUpdUsrId() {
		return updUsrId;
	}

	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}

	public String getCondSttlNum() {
		return condSttlNum;
	}

	public void setCondSttlNum(String condSttlNum) {
		this.condSttlNum = condSttlNum;
	}

	public String getCondSttlCd() {
		return condSttlCd;
	}

	public void setCondSttlCd(String condSttlCd) {
		this.condSttlCd = condSttlCd;
	}

	public String getCondBookmarkYn() {
		return condBookmarkYn;
	}

	public void setCondBookmarkYn(String condBookmarkYn) {
		this.condBookmarkYn = condBookmarkYn;
	}

	public String getFsttlDesc() {
		return fsttlDesc;
	}

	public void setFsttlDesc(String fsttlDesc) {
		this.fsttlDesc = fsttlDesc;
	}

	public String getMsttlDesc() {
		return msttlDesc;
	}

	public void setMsttlDesc(String msttlDesc) {
		this.msttlDesc = msttlDesc;
	}

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public int getReviewCnt() {
		return reviewCnt;
	}

	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}

}
