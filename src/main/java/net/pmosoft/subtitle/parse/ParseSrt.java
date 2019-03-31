package net.pmosoft.subtitle.parse;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.subtitle.comm.util.FileUtil;
import net.pmosoft.subtitle.comm.util.StringUtil;

public class ParseSrt {
    private static Logger logger = LoggerFactory.getLogger(ParseSrt.class);

	public String pathFileNm = "";
    public ArrayList<String> fileList;
    public String langCd = "";
    public String encoding = "";

    // 원자막 리스트
    public ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
    // 스타트 시간별로 합쳐진 리스트
    public ArrayList<SrtVo> srtList2 = new ArrayList<SrtVo>();
    // 콤마별로 합쳐진 리스트
    public ArrayList<SrtVo> srtList3 = new ArrayList<SrtVo>();

	ParseSrt(){}
	ParseSrt(String pathFileNm, String langCd){
		this.pathFileNm = pathFileNm;
        this.langCd = langCd;
	}

    public static void main(String[] args) throws Exception {
        String pathFileNm="d:\\Downloads\\subtitle-sample\\Sinbad.Legend.Of.The.Seven.Seas.2003.720p.BluRay.x264-[YTS.AM].srt";

        ParseSrt parseSrt = new ParseSrt(pathFileNm,"en");
        parseSrt.execute();
    }

    /*
     * 메인 : Srt 파싱하여 Vo로 리턴
     */
    public ArrayList<SrtVo> execute() throws Exception {
        detectEncoding();
        fileToList();

        return parsing();
    }

    /*
     * 1단계 : 인코인을 확인. 미확인시 에러 리턴.
     */
    void detectEncoding(){
        logger.info("");
        try {
            encoding = FileUtil.detectEncoding(pathFileNm);
        } catch (Exception e) {e.printStackTrace();}
    }

    /*
     * 2단계 : 자막파일을 리스트로 반환.
     */
    void fileToList(){
        fileList = FileUtil.fileToList(pathFileNm, encoding);
        logger.info("size="+fileList.size());
    }

    /*
     * 3단계  : Srt 파싱하여 Vo로 리턴
     */
    public ArrayList<SrtVo> parsing() {

        String line = "";
        int rnum = 0;
        String stime = "";
        String etime = "";
        String content = "";

        String ruleStimeEtime = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
        String ruleStime = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
        String ruleEtime = "--> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";

        //-------------------------------
        logger.info("자막 1차 추출");
        //-------------------------------
        for (int i = 0; i < fileList.size(); i++) {
            line = fileList.get(i);
            //logger.info(line);
            if(skipLine(line)) continue;

            if(line.matches(ruleStimeEtime)) {
            	rnum++;
                stime = StringUtil.patternMatch(line,ruleStime);
                etime = StringUtil.patternMatch(line,ruleEtime);

            } else {
                if(rnum>0){
                    rnum++;
                    content = replaceContent(line);
                    srtList.add(new SrtVo(rnum,stime,etime,content));
                }
            }
        }
        //for (int i = 0; i < srtList.size(); i++) {
        //    System.out.println(srtList.get(i).getContent());
        //}

        //-------------------------------
        logger.info("동시간별로 내용을 합친다.");
        //-------------------------------
        rnum = 0; content = "";
        for (int i = 0; i < srtList.size()-1; i++) {
        	if(srtList.get(i).getStime() == srtList.get(i+1).getStime()) {
            	content += srtList.get(i).getContent() + " ";
            } else {
                if(content.trim().length() != 0) {
                    srtList2.add(new SrtVo(rnum++,srtList.get(i).getStime(),srtList.get(i+1).getStime(),content.trim()));
                }
                content = srtList.get(i).getContent();
            }
        }
        //for (int i = 0; i < srtList2.size(); i++) {
        //    System.out.println(srtList2.get(i).getContent());
        //}

        //------------------------------------------
        logger.info("콤바별로 내용을 합친다.");
        //------------------------------------------
        int commaCnt = 0;
        for (int i = 0; i < srtList2.size(); i++) {
            if(srtList2.get(i).getContent().matches(".*[.?!\"]")){
                commaCnt++;
            }
        }
        System.out.println("commaCnt========="+commaCnt);
        if(langCd.equals("en") && commaCnt > srtList2.size()/2 ){
            System.out.println("comma progress");
            content = ""; rnum = 0;
            for (int i = 0; i < srtList2.size(); i++) {
                content += srtList2.get(i).getContent()+" ";
                if(srtList2.get(i).getContent().matches(".*[.?!\"]")){
                    srtList3.add(new SrtVo(rnum++,"","",content));
                    content = "";
                }
            }

            for (int i = 0; i < srtList3.size(); i++) {
                //System.out.println(srtList3.get(i).getContent());
            }

            return srtList3;

        } else {
            return srtList2;
        }
    }

    /*
     * 3단계 : 파싱 - 삭제될 단어
     */
    String replaceContent(String content) {
        content = content.replace("&nbsp;", "");
        if(content.contentEquals("nbsp")) content = "";
        content = content.replace("<br>"," ");
        content = content.replaceAll("<([^>]+)>","");

        content = content.trim();
        return content;
    }

    /*
     * 3단계 : 파싱 - 스킵될 라인
     */
    boolean skipLine(String line) {
        boolean tf = false;
        if(line.matches("[0-9]+$")) tf = true;
        return tf;
    }


}
