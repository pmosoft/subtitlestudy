package net.pmosoft.subtitle.parse;

import java.util.ArrayList;

public class SmiSrtSubtitleVo {

	String category  = ""; // Foreign or Mother
	String extention = ""; // smi or srt
	String filePathNm = "";  
	ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
    ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public ArrayList<SrtVo> getSrtList() {
		return srtList;
	}
	public void setSrtList(ArrayList<SrtVo> srtList) {
		this.srtList = srtList;
	}
	public ArrayList<SmiVo> getSmiList() {
		return smiList;
	}
	public void setSmiList(ArrayList<SmiVo> smiList) {
		this.smiList = smiList;
	}
	public String getFilePathNm() {
		return filePathNm;
	}
	public void setFilePathNm(String filePathNm) {
		this.filePathNm = filePathNm;
	}

}
