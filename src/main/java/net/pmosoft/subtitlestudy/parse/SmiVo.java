package net.pmosoft.subtitlestudy.parse;

public class SmiVo {

    int   num;
    int   stime;
    int   etime;
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
    public int getStime() {
        return stime;
    }
    public void setStime(int stime) {
        this.stime = stime;
    }
    public int getEtime() {
        return etime;
    }
    public void setEtime(int etime) {
        this.etime = etime;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}