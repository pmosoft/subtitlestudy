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

    // 언어코드에 해당하는 자막 리스트
    public ArrayList<String> parseFileList = new ArrayList<String>();

    // 원자막 리스트
    public ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
    // 스타트 시간별로 합쳐진 리스트
    public ArrayList<SmiVo> smiList2 = new ArrayList<SmiVo>();
    // 콤마별로 합쳐진 리스트
    public ArrayList<SmiVo> smiList3 = new ArrayList<SmiVo>();

    ParseSmi(){}
    ParseSmi(String pathFileNm,String langCd){
        this.pathFileNm = pathFileNm;
        this.langCd = langCd;
    }

    public static void main(String[] args) throws Exception {
        //String pathFileNm="d:/Downloads/익스팬스/익스팬스 시즌1 01-10화 완 한글자막 720p The Expanse/The.Expanse.S01E03.INTERNAL.720p.HDTV.x264-KILLERS.smi ";
        String pathFileNm="d:\\Downloads\\위기의주부들 시즌4 완결(총17화) - 한영통합 (코믹추천미드)\\Desperate.Housewives.4x04.if.theres.anything.i.cant.stand.hdtv.xvid-xor.smi";
        ParseSmi parseSmi = new ParseSmi(pathFileNm, "ko");
        parseSmi.execute();
        //parseSmi.test01();

    }

    void test01(){
        String line = " aa bbb. ccc?";
        if(line.matches(".*[.?!]")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
        } else {
            System.out.println("no match");
        }

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
        logger.info("");
        fileList = FileUtil.fileToList(pathFileNm, encoding);
    }

    /*
     * 3단계 : 자막파일내 언어팩 확인후 리스트 반환
     */
    void detectLanguage(){
        String line = "";
        String langTag = "";
        logger.info(langCd);

        for (int i = 0; i < fileList.size(); i++) {
            line = fileList.get(i);
            //.KOR { Name:Korean; lang:ko-KR; SAMIType:CC; }
            if(line.matches("^[.][a-zA-Z]* [{].*")){
                // 예외처리(lang:en 이 아닌 데이터 오류)
                //.ENCC { Name:ENCC; lang:kr-KR; SAMIType:CC; }
                //.EGCC { Name:EGCC; lang:kr-KR; SAMIType:CC; }
                if(langCd.matches("en") && line.matches("[.][eE][a-zA-Z][cC][cC].*")){
                    langTag = StringUtil.patternMatch(line,"[.][a-zA-Z]*").replace(".", "");
                    sttlLangCd = langTag;
                    logger.info(sttlLangCd);
                } else if(langCd.matches("ko") && line.matches("[.][kK][rR][cC][cC].*")){
                    langTag = StringUtil.patternMatch(line,"[.][a-zA-Z]*").replace(".", "");
                    sttlLangCd = langTag;
                    logger.info(sttlLangCd);
                }
                //} else if(line.contains(langCd)){
                //    langTag = StringUtil.patternMatch(line,"[.][a-zA-Z]*").replace(".", "");
                //    sttlLangCd = langTag;
                //    logger.info(sttlLangCd);
                // }
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
    ArrayList<SmiVo> parsing() {

        String line = "";
        int rnum = 0;
        int stime = 0;
        String content = "";

        String ruleSyncTag = "(?i)<sync.*=[0-9]+.*";
        //String ruleContent = "(?i)[^<sync].*";
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
                //logger.info(line);

                content = line;
                content = replaceContent(content);
                //logger.info(line);

                if(content.length()>0) smiList.add(new SmiVo(rnum++,stime,0,content));
                //logger.info(smiList.size());
                //logger.info(line);

            }

        }

        //-------------------------------
        logger.info("동시간별로 내용을 합친다.");
        //-------------------------------
        rnum = 0; int prevStime = 0; content = "";
        for (int i = 0; i < smiList.size(); i++) {
            if(prevStime == smiList.get(i).getStime()) {
                content += smiList.get(i).getContent()+" ";
            } else {
               if(content.trim().length() != 0) {
                   smiList2.add(new SmiVo(rnum++,prevStime,0,content.trim()));
               }
               if(i>0) prevStime = smiList.get(i-1).getStime();
               content = smiList.get(i).getContent();
            }
            //logger.info(content);
        }
        logger.info("동시간별 건수="+smiList2.size());

        //------------------------------------------
        logger.info("콤바별로 내용을 합친다.");
        //------------------------------------------
        int commaCnt = 0;
        for (int i = 0; i < smiList2.size(); i++) {
            if(smiList2.get(i).getContent().matches(".*[.?!\"]")){
                commaCnt++;
            }
        }
        //logger.info("commaCnt========="+commaCnt);
        if(langCd.equals("en") && commaCnt > smiList2.size()/2 ){
            logger.info("comma progress");

            content = ""; rnum = 0;
            for (int i = 0; i < smiList2.size(); i++) {
                content += smiList2.get(i).getContent()+" ";
                if(smiList2.get(i).getContent().matches(".*[.?!\"]")){
                    smiList3.add(new SmiVo(rnum++,0,0,content));
                    content = "";
                }
            }

            for (int i = 0; i < smiList3.size(); i++) {
                System.out.println(smiList3.get(i).getContent());
            }
            logger.info("콤바별 건수="+smiList3.size());

            return smiList3;

        } else {
            logger.info("콤바별 건수="+smiList2.size());
            for (int i = 0; i < smiList2.size(); i++) {
                System.out.println(smiList2.get(i).getContent());
            }
            return smiList2;
        }
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
