package net.pmosoft.subtitle.parse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseSmi {

	public String pathFileNm = "";

	ParseSmi(){}
	ParseSmi(String pathFileNm){
		this.pathFileNm = pathFileNm;
	}

	private static Logger logger = LoggerFactory.getLogger(ParseSmi.class);

    public static void main(String[] args) throws Exception {
        //String s1 = "<i>Metal </i>";
        //logger.debug(s1.matches("<i>")
    	//String content = "&nbsp;";
    	//System.out.println(content.contentEquals("nbsp"));
    	//System.out.println(content..contentEquals("nbsp"));

        ParseSmi parseSmi = new ParseSmi();
    	String filePathName="d:/Downloads/익스팬스/익스팬스 시즌1 01-10화 완 한글자막 720p The Expanse/The.Expanse.S01E03.INTERNAL.720p.HDTV.x264-KILLERS.smi ";
    	parseSmi.excute(filePathName);
    }

    /*
     * 메인 : Smi 파싱하여 Vo로 리턴
     */
    public ArrayList<SmiVo> excute(String filePathName) throws Exception {

    	logger.info("parseSmiFile=="+filePathName);
        ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
    	ArrayList<SmiVo> smiList2 = new ArrayList<SmiVo>();

        Pattern p; Matcher m; String parseRule;

        BufferedReader br = null;
        try {

            File file = new File(filePathName);

            if (file.isFile()) {
                //br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),"EUC-KR"));
                //br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName)));
                DetectEncoding detectEncoding = new DetectEncoding();
                String encoding = detectEncoding.execute(filePathName);

            	br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),encoding));
                int num = 0;
                int bnum = 0;
                int rnum = 0;
                int stime = 0;
                int etime = 0;

                while (true) {
                    String str = br.readLine();
                    logger.info(str);
                    //if(str.contains("br")){
                    //	logger.debug(str);
                    //}
                    //str = str.replace("<br>"," ");
                    //logger.debug(str);
                    if (str != null) {
                        if(str.matches("(?i)<sync.*=[0-9]+.*")) {
                            logger.debug("1111");
                        	num++;

                            /*******************************
                             * stime 추출
                             *******************************/
                        	parseRule = "[0-9]+";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            stime = Integer.parseInt(m.group());
                            //logger.debug("1111 content="+str+":"+stime);
                            /*************************************************
                             *  content가 존재하는 경우 추출
                             *  예> <SYNC Start=90224><P Class=KRCC>킬 커맨드
                             ************************************************/
                        	parseRule = "(?i)<p class=.*>.*";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            String content = m.group();

                            logger.debug("1111 content="+content);
                            logger.debug("1111 content="+content.substring(content.indexOf('>')+1,content.length()));
                            content = content.substring(content.indexOf('>')+1,content.length());
                            content = content.replace("&nbsp;", "");
                            if(content.contentEquals("nbsp")) content = "";
                            if(content.trim().length()>0){
                            	logger.debug("11111111 content="+content);

                            	rnum++;
                                SmiVo smiVo = new SmiVo();
                                smiVo.num = rnum;
                                smiVo.stime = stime;
                                smiVo.etime = 0;
                                smiVo.content = content;
                                smiList.add(smiVo);
                            }

                        } else if(str.matches("(?i)<sync.*=>.*")) {
                        } else {
                        	logger.debug("2222");
                        	if(num>0){
                                // rnum별로 내용을 산출한다.
                            	//logger.debug("3333");
                            	String content = str;
                            	content = content.replace("&nbsp;", "");
                                if(content.trim().length()>0){
                                	logger.debug("22222222 content="+content);

                                	rnum++;
                                    SmiVo smiVo = new SmiVo();
                                    smiVo.num = rnum;
                                    smiVo.stime = stime;
                                    smiVo.etime = 0;
                                    smiVo.content = content;
                                    smiList.add(smiVo);
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
                br.close();

                logger.info("smiList.size()=="+smiList.size());

                // etime를 세팅한다
                for (int i = 0; i < smiList.size(); i++) {
                    if(i < smiList.size()-1) {
                        SmiVo tvo = new SmiVo();
                        tvo.setNum(smiList.get(i).getNum());
                        tvo.setStime(smiList.get(i).getStime());
                        tvo.setEtime(smiList.get(i+1).getStime());
                        tvo.setContent(smiList.get(i).getContent());
                        smiList.set(i, tvo);
                        //smiList.get(i).print();
                    }
                }


                num = 0;
                // stime별로 content를 합친다
                String content2 = "";
                for (int i = 0; i < smiList.size()-1; i++) {
                	//logger.debug(smiList.get(i).getContent());
                    if(smiList.get(i).getStime() == smiList.get(i+1).getStime()) {
                        content2 += smiList.get(i).getContent();
                    } else {
                        SmiVo tvo = new SmiVo();
                        tvo.setNum(++num);
                        tvo.setStime(smiList.get(i).getStime());
                        tvo.setEtime(smiList.get(i+1).getStime());
                        tvo.setContent(content2+smiList.get(i).getContent());
                        content2 = "";
                        //tvo.print();
                        smiList2.add(tvo);
                    }

                }

            }
        } catch(Exception e) { e.printStackTrace(); throw e; }


        //String src2 = src.replaceAll("<br>\n", " ");
        //logger.debug(src2.substring(328+7,src2.length()));
        return smiList2;
    }

}
