package net.pmosoft.subtitle.parse;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.subtitle.comm.util.FileUtil;
import net.pmosoft.subtitle.comm.util.StringUtil;

public class ParseSmi {
    private static Logger logger = LoggerFactory.getLogger(ParseSmi.class);

    public String pathFileNm = "";
    public ArrayList<String> fileList;
    public String langCd = "";
    public String encoding = "";

    public String sttlLangCd = "";
    int sttlStartIdx = 0;
    int sttlEndIdx = 0;

    public ArrayList<String> parseFileList = new ArrayList<String>();

    // 원자막 리스트
    public ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
    // 스타트 시간별로 합쳐진 리스트
    public ArrayList<SmiVo> smiList2 = new ArrayList<SmiVo>();
    // .별로 합쳐진 리스트
    public ArrayList<SmiVo> smiList3 = new ArrayList<SmiVo>();

    ParseSmi(){}
    ParseSmi(String pathFileNm,String langCd){
        this.pathFileNm = pathFileNm;
        this.langCd = langCd;
    }

    public static void main(String[] args) throws Exception {
        String pathFileNm="d:/Downloads/익스팬스/익스팬스 시즌1 01-10화 완 한글자막 720p The Expanse/The.Expanse.S01E03.INTERNAL.720p.HDTV.x264-KILLERS.smi ";
        ParseSmi parseSmi = new ParseSmi(pathFileNm, "ko");
        parseSmi.execute();

    }

    void test01(){
        /*
        String line = "<SYNC Start=3069983><P Class=kor>&nbsp;";
        if(line.matches("(?i).*[ ]*class[ ]*= *"+"KOR.*")){
            logger.info("line==="+line);
        }
        */
        //String str = "<br>aa</br>";
        //logger.info(line.replaceAll("<([^>]+)>",""));
    }

    /*
     * 실행
     */
    public ArrayList<SmiVo> execute() {
        detectEncoding();
        fileToList();
        detectLanguage();
        filterFileList();
        parsing();

        return smiList2;
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
        logger.info("");
        fileList = FileUtil.fileToList(pathFileNm, encoding);
    }

    /*
     * 3단계 : 자막파일내 언어팩 확인후 리스트 반환
     */
    void detectLanguage(){
        String line = "";
        for (int i = 0; i < fileList.size(); i++) {
            line = fileList.get(i);
            //.KOR { Name:Korean; lang:ko-KR; SAMIType:CC; }
            if(line.matches("^[.][a-zA-Z]* [{].*")){
                if(line.contains(langCd)){
                    sttlLangCd = StringUtil.patternMatch(line,"[.][a-zA-Z]*").replace(".", "");
                    logger.info(sttlLangCd);
                }
            }
        }
    }

    /*
     * 4단계 : 자막파일리스트에서 내언어팩만 추출하여 재저장
     */
    void filterFileList(){
        boolean isFirst = false;
        for (int i = 0; i < fileList.size(); i++) {
            //<SYNC Start=3069983><P Class=KOR>&nbsp;
            if(fileList.get(i).matches("(?i).*[ ]*class[ ]*= *"+sttlLangCd+".*")){
                if(!isFirst) { sttlStartIdx = i; isFirst = true; }
                sttlEndIdx = i;
            }
        }
        logger.info(sttlStartIdx+"~"+sttlEndIdx);

        for (int i = sttlStartIdx; i <= sttlEndIdx+1; i++) {
            parseFileList.add(fileList.get(i));
        }
    }

    /*
     * 5단계 : 파싱 - 메인
     */
    void parsing() {

        String line = "";
        int rnum = 0;
        int stime = 0;
        int etime = 0;
        String content = "";

        String ruleSyncTag = "(?i)<sync.*=[0-9]+.*";
        String ruleContent = "(?i)[^<sync].*";
        String ruleStime= "[0-9]+";
        String ruleClassContent= "(?i)<p class=.*>.*";

        //-------------------------------
        logger.info("자막 1차 추출");
        //-------------------------------
        for (int i = 0; i < parseFileList.size(); i++) {
            line = parseFileList.get(i);
            //logger.info(line);

            if(skipLine(line)) continue;

            if((line.matches(ruleSyncTag))) {
                //logger.info(line);
                /*******************************
                 * stime 추출
                 *******************************/
                stime = Integer.parseInt(StringUtil.patternMatch(line,ruleStime));
                //logger.debug("1111 content="+str+":"+stime);
                /*************************************************
                 *  content가 존재하는 경우 추출
                 *  예> <SYNC Start=90224><P Class=KRCC>킬 커맨드
                 ************************************************/
                content = StringUtil.patternMatch(line,ruleClassContent);
                content = content.substring(content.indexOf('>')+1,content.length());
                content = replaceContent(content);
                if(content.length()>0) smiList.add(new SmiVo(rnum++,stime,0,content));

            //} else if(line.matches(ruleContent)) {
            } else {
                /*******************************
                 * content 추출
                 *******************************/
                content = line;
                //logger.info(content);
                content = replaceContent(content);
                //logger.info(content);

                if(content.length()>0) smiList.add(new SmiVo(rnum++,stime,0,content));
                //logger.info(smiList.size());
            }

            /*******************************
             * etime 세팅
             *******************************/
            for (int j = 0; j < smiList.size(); j++) {
                if(j < smiList.size()-1) {
                    //smiList.add(new SmiVo(smiList.get(j).getNum(),smiList.get(j).getStime(),smiList.get(j+1).getStime(),smiList.get(j).getContent()));
                }
            }
        }

        //-------------------------------
        logger.info("동시간별로 내용을 합친다.");
        //-------------------------------
        String content2 = ""; int prevStime = 0; int num2 = 0;
        for (int k = 0; k < smiList.size(); k++) {
            if(prevStime == smiList.get(k).getStime()) {
                content2 += smiList.get(k).getContent()+" ";
            } else {
               if(content2.trim().length() != 0) {
                   smiList2.add(new SmiVo(rnum++,prevStime,0,content2));
               }
               if(k>0) prevStime = smiList.get(k-1).getStime();
               content2 = smiList.get(k).getContent();
            }
        }
        for (int i = 0; i < smiList2.size(); i++) {
            //logger.info(smiList2.get(i).getStime() +":"+smiList2.get(i).getContent());
        }

        //-------------------------------
        //logger.info("콤바별 내용을 합친다.");
        //-------------------------------

    }

    /*
     * 5단계 : 파싱 - 삭제될 단어
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
     * 5단계 : 파싱 - 스킵될 라인
     */
    boolean skipLine(String line) {
        boolean tf = false;
        if(line.matches("(?i)<sync.*=>.*")) tf = true;
        return tf;
    }


}
