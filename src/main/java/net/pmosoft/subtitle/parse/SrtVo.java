package net.pmosoft.subtitle.parse;

public class SrtVo {

    int    num;
    String stime;
    String etime;
    String content;

    public void print() {
        System.out.println(num+" "+stime+" "+etime+" "+content);    
    }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}    
    
    

}
