package net.pmosoft.subtitle.parse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.subtitle.file.FileUtil;


public class ParseSubtitle {

	private static Logger logger = LoggerFactory.getLogger(ParseSubtitle.class);
	 	
	
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        //String s1 = "<i>Metal </i>";
        
        //logger.debug(s1.matches("<i>")
    	//String content = "&nbsp;";
    	//System.out.println(content.contentEquals("nbsp"));
    	//System.out.println(content..contentEquals("nbsp"));
        
        ParseSubtitle parseSubtitle = new ParseSubtitle();
        parseSubtitle.test02();
    }

    /*
     * 테스트     
     */
    void test02() throws Exception{
        //String filePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy@gmail.com/Star.Trek.Beyond.2016.1080p.BluRay.x264-SPARKS-complete.smi";
    	String filePathName="d:\\Downloads\\moive\\Kill Command\\Kill Command 2016 1080p BluRay x264 DTS-JYK - ENGLISH.smi";
    	SmiSrtSubtitleVo smiSrtSubtitleVo = getSubtitleVo(filePathName);
    	logger.debug(smiSrtSubtitleVo.getFilePathNm());
    } 
    
    
    /*
     * 메인 : 샘파일를 파싱하여 VO로 리턴
     */
    public SmiSrtSubtitleVo getSubtitleVo(String subtitleFilePath) throws Exception {
    	SmiSrtSubtitleVo smiSrtSubtitleVo = new SmiSrtSubtitleVo(); 
    	try {
        	logger.info(subtitleFilePath +":"+ verifySrtSmi(subtitleFilePath));
        	smiSrtSubtitleVo.setExtention(verifySrtSmi(subtitleFilePath));
        	if(smiSrtSubtitleVo.getExtention().equals("smi")) {
        		logger.info("smi");
        		smiSrtSubtitleVo.setSmiList(parseSmiFile(subtitleFilePath));
                for (int i = 0; i < smiSrtSubtitleVo.getSmiList().size(); i++) {
                	//smiSrtSubtitleVo.getSmiList().get(i).print();
        		}
        	} else {
        		logger.info("srt");
        		smiSrtSubtitleVo.setSrtList(parseSrtFile(subtitleFilePath));
                for (int i = 0; i < smiSrtSubtitleVo.getSrtList().size(); i++) {
                	//smiSrtSubtitleVo.getSrtList().get(i).print();
        		}
        	}
        	return smiSrtSubtitleVo;
			
		} catch(Exception e) { e.printStackTrace(); throw e; }
    	
    }	


    /*
     * 메인 : Srt 파싱하여 Vo로 리턴
     */
    public ArrayList<SrtVo> parseSrtFile(String filePathName) throws Exception {

    	ArrayList<SrtVo> srtList2 = new ArrayList<SrtVo>();
            	
        Pattern p; Matcher m;
        String parseRule;
        
        BufferedReader br = null;
        String src = "";
        ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();

        try {

            File file = new File(filePathName);
            
            if (file.isFile()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),detectEncoding(filePathName)));
                
                int num = 0;
                int bnum = 0;
                int rnum = 0;
                String stime = "";
                String etime = "";
                
                String timeParseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                
                while (true) {
                    String str = br.readLine();
                    logger.debug("str="+str);
                    if (str != null) {
                        if(str.matches("[0-9]+$")) {
                        } else if(str.matches(timeParseRule)) {
                        	//logger.debug("str="+str);
                        	num++;
                            parseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            stime = m.group();
                            //logger.debug(stime);

                            parseRule = "--> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            etime = m.group().substring(4);
                            //logger.debug(num + " "+ stime + " " + etime);
                        } else {
                            if(num>0){
                                // rnum별로 내용을 산출한다.
                                rnum++;
                                SrtVo srtVo = new SrtVo();
                                srtVo.num = rnum;
                                srtVo.stime = stime;
                                srtVo.etime = etime;
                                srtVo.content = str;
                                //srtVo.print();
                                srtList.add(srtVo);
                            }
                        }
                    } else {
                        break;
                    }    
                }
                br.close();
                
                num = 0;
                // stime별로 content를 합친다
                String content2 = "";
                for (int i = 0; i < srtList.size()-1; i++) {
                	//logger.debug(srtList.size() +":"+ i);
                	//logger.debug(srtList.get(i).getContent());
                	if(srtList.get(i).getStime() == srtList.get(i+1).getStime()) {
                        //logger.debug(srtList.get(i).getContent());
                    	content2 += srtList.get(i).getContent();
                    } else {
                        SrtVo tvo = new SrtVo(); 
                        tvo.setNum(++num);
                        tvo.setStime(srtList.get(i).getStime());
                        tvo.setEtime(srtList.get(i+1).getStime());
                        tvo.setContent(content2+srtList.get(i).getContent());
                        content2 = "";
                        //tvo.print();
                        srtList2.add(tvo);
                    }
                }                     
            }
        } catch(Exception e) { e.printStackTrace(); throw e; }
    	
        
        return srtList;
        //String src2 = src.replaceAll("<br>\n", " ");
        //logger.debug(src2.substring(328+7,src2.length()));
        
    }

    
    /*
     * 메인 : Smi 파싱하여 Vo로 리턴
     */    
    public ArrayList<SmiVo> parseSmiFile(String filePathName) throws Exception {

    	logger.info("parseSmiFile=="+filePathName);
        ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
    	ArrayList<SmiVo> smiList2 = new ArrayList<SmiVo>();
    	
        Pattern p; Matcher m;
        String parseRule;
        
        BufferedReader br = null;
        String src = "";
        try {

            File file = new File(filePathName);
            if (file.isFile()) {
                //br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),"EUC-KR"));
                //br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName)));
            	br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),detectEncoding(filePathName)));
                int num = 0;
                int bnum = 0;
                int rnum = 0;
                int stime = 0;
                int etime = 0;
                
                while (true) {
                    String str = br.readLine();
                    logger.debug(str);
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
        return smiList;
    }
    
    
    /*
     * 유틸 : 샘파일의 확장자(리턴:smi, srt)
     */
    String verifySrtSmi(String subtitleFilePath) {
    	//logger.debug("subtitleFilePath=="+subtitleFilePath);
    	//logger.debug("subtitleFilePath=="+subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()));
    	//logger.debug("subtitleFilePath=="+subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()).toLowerCase());
    	return subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()).toLowerCase();
    }   
        
    
    /*
     * 유틸 : 샘파일의 인코딩을 판단(리턴:UTF-8, EUC-KR...)
     */
    @SuppressWarnings("resource")
	String detectEncoding(String filePathName) throws Exception {
    	
		try {
			byte[] buf = new byte[4096];
			//String filePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy@gmail.com/Star.Trek.Beyond.2016.1080p.BluRay.x264-SPARKS-complete.smi";
	    	java.io.FileInputStream fis;
	    	fis = new FileInputStream(filePathName);
	    	UniversalDetector detector = new UniversalDetector(null);
	    	int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
	    		detector.handleData(buf, 0, nread);
			}
			detector.dataEnd();
			String encoding = detector.getDetectedCharset();
			if (encoding != null) {
				logger.info("Detected encoding = " + encoding);
			} else {
				logger.info("No encoding detected.");
				encoding = "EUC-KR";
			}
			detector.reset();
			
			return encoding;
		} catch(Exception e) { e.printStackTrace(); throw e; }
    	
	}	
    
    
    /*
     * 테스트     
     */
    public void test3(){
        logger.debug("test3");
        ArrayList<SmiVo> sl = new ArrayList<SmiVo>();
        SmiVo svo01 = new SmiVo(); svo01.setStime(111); sl.add(svo01);
        SmiVo svo02 = new SmiVo(); svo02.setStime(222); sl.add(svo02);
        
        sl.get(0).print();
        sl.get(1).print();
        
    }    
           
    /*
     * 테스트     
     */
    public void test2(){
        logger.debug("test2");
        Pattern p; Matcher m;
        String parseRule; boolean a;
        
        String src = "<SYNC Start=98679><P Class=KRCC>";
        parseRule = "[0-9]+";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
        m.find();
        logger.debug(m.group());
    }    

    /*
     * 테스트     
     */
    public void test(){
        String src = "1\n00:00:34,720 --> 00:00:36,860\n<i>Metal screams.</i>\n\n2\n00:00:38,600 --> 00:00:41,341\n<i>Something hits me\nsquare in the chest.</i>";
        
        Pattern p; Matcher m;
        String parseRule; boolean a;

        parseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;

        List<HashMap<String, String>> parseList = new ArrayList<HashMap<String, String>>();
                
        while (a = m.find()) {
            HashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("timeStart",String.valueOf(m.start()));
            map.put("timeEnd"  ,String.valueOf(m.end()));
            map.put("time"     ,m.group());
  
            parseList.add(map);
            logger.debug(String.valueOf(m.start()));
            logger.debug(String.valueOf(m.end()));
            logger.debug(m.group());
        }
        
        
        for (int i = 0; i < parseList.size(); i++) {
            logger.debug(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,src.length()));
                
            }
        }
        
    }


    
    /*
     * 테스트     
     */
    void test01() throws Exception{
        //String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Valerian.and.the.City.of.a.Thousand.Planets.smi";
        String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/[VIP] Demon City Shinjuku.smi";
        //String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Star Wars Rebels S02E01 - The Siege of Lothal.smi";
        //String srtfilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Star Wars Rebels S02E01 - The Siege of Lothal.srt";
        
        ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
        ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();

        //srtList = parseSubtitle.parseSrtFile3(srtfilePathName);
        smiList = parseSmiFile(smifilePathName);
        
        for (int i = 0; i < smiList.size(); i++) {
        	logger.debug(smiList.get(i).getContent());
		}

        for (int i = 0; i < srtList.size(); i++) {
        	//logger.debug(srtList.get(i).getNum());
        	//logger.debug(srtList.get(i).getContent());
        	logger.debug(smiList.get(i).getContent());
		}
        //parseSubtitle.test3();
    } 


    /*
     * 테스트     
     */
    public void parseSrtFile2(String filePathName) {
        //logger.debug(parseSrt.readFile(filePathName));
        String src = FileUtil.readFile(filePathName);
        
        Pattern p; Matcher m;
        String parseRule; boolean a;

        parseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;

        List<HashMap<String, String>> parseList = new ArrayList<HashMap<String, String>>();
                
        while (a = m.find()) {
            HashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("timeStart",String.valueOf(m.start()));
            map.put("timeEnd"  ,String.valueOf(m.end()));
            map.put("time"     ,m.group());
  
            parseList.add(map);
            //logger.debug(String.valueOf(m.start()));
            //logger.debug(String.valueOf(m.end()));
            //logger.debug(m.group());
        }
        
        //logger.debug(parseList);
        
        for (int i = 0; i < parseList.size(); i++) {
            logger.debug(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,src.length()));
                
            }
        }

    }
    

    /*
     * 테스트     
     */
    public void parseSmiFile3(String filePathName) {

        List<SmiVo> smiList = new ArrayList<SmiVo>();
        
        //String src = FileUtil.readFile(filePathName);
        //logger.debug("src===\n"+src);
        String src = FileUtil.readFileEucKr(filePathName);
        
        Pattern p; Matcher m;
        String parseRule; boolean a;
        parseRule = "<SYNC Start=[0-9]*>[<P Class=KRCC>]+";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;

        List<HashMap<String, String>> parseList = new ArrayList<HashMap<String, String>>();
                
        while (a = m.find()) {
            HashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("timeStart",String.valueOf(m.start()));
            map.put("timeEnd"  ,String.valueOf(m.end()));
            map.put("time"     ,m.group());
  
            parseList.add(map);
            //logger.debug(String.valueOf(m.start()));
            //logger.debug(String.valueOf(m.end()));
            //logger.debug(m.group());
        }
        
        for (int i = 0; i < parseList.size(); i++) {
            //logger.debug(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                //logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                //        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                //logger.debug(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                //        ,src.length()));
            }
        }

    }
    
    /*
     * 테스트     
     */
    public void parseSmiFile2(String filePathName) {
        
        BufferedReader br = null;
        String src = "";
        try {

            File file = new File(filePathName);
            if (file.isFile()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),"EUC-KR"));
                while (true) {
                    String str = br.readLine();
                    if (str != null) {
                        if(str.matches("<SYNC.*")) str=""; 
                        else src += str + "\n";
                    } else {
                        break;
                    }    
                }
                br.close();
            }
        } catch (Exception e) {
            logger.debug("e=" + e.getMessage());
        }
        
        //logger.debug(src);
        //logger.debug(src.replaceAll("<br>\n", " "));
        String src2 = src.replaceAll("<br>\n", " ");
        //String src3 = src2.substring(src2.indexOf("<body>"),src2.length()-1);
        //logger.debug(src3);
        //logger.debug(src2.indexOf("<body>"));
        logger.debug(src2.substring(328+7,src2.length()));
        
        
        //String src2 = src.replaceAll("<br>\n", " ");        
        //String src2 = src.replaceAll("<br>\n", " ");
        String aa = "123456778";
        logger.debug(aa.substring(2, 4));
        
        //        //logger.debug(parseSrt.readFile(filePathName));
//        String src = FileUtil.readFileEucKr(filePathName);
//        logger.debug(src);
//        
//        src = src.replaceAll("<br>","\n");
//        logger.debug(src);
        
        //logger.debug(StringUtil.uniCodeConvertor(src));

//        
//        String originalStr = FileUtil.readFileEucKr(filePathName); // 테스트 
//        String [] charSet = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
//          
//        for (int i=0; i<charSet.length; i++) {
//         for (int j=0; j<charSet.length; j++) {
//          try {
//           logger.debug("[" + charSet[i] +"," + charSet[j] +"] = " + new String(originalStr.getBytes(charSet[i]), charSet[j]));
//          } catch (UnsupportedEncodingException e) {
//           e.printStackTrace();
//          }
//         }
//        }
//        
        
        
        
//        Pattern p; Matcher m;
//        String parseRule; boolean a;
//
//        parseRule = "<br>.*";
//        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
//        while (a = m.find()) { 
//            logger.debug(m.group().replace("<i>","").replace("</i>",""));
//        }
    }

    /*
     * 테스트     
     */
    public void parseSrtFile4(String filePathName) {
        //logger.debug(parseSrt.readFile(filePathName));
        String src = FileUtil.readFile(filePathName);
        
        Pattern p; Matcher m;
        String parseRule; boolean a;

        parseRule = "<i>.*";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
        while (a = m.find()) { 
            logger.debug(m.group().replace("<i>","").replace("</i>",""));
        }
    }
    
 
}
