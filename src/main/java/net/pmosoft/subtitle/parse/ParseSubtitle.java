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

import net.pmosoft.fframe.comm.util.FileUtil;

public class ParseSubtitle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String s1 = "<i>Metal </i>";
        
        //System.out.println(s1.matches("<i>")
        
        ParseSubtitle parseSubtitle = new ParseSubtitle();
        parseSubtitle.test02();
    }

    /*
     * 테스트     
     */
    void test02(){
        //String filePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy@gmail.com/Star.Trek.Beyond.2016.1080p.BluRay.x264-SPARKS-complete.smi";
    	String filePathName="C:/fframe/workspace/subtitlestudy/file/lifedomy@gmail.com/Silicon.Valley.S01E01.720p.HDTV.x264-2HD.smi";
    	SmiSrtSubtitleVo smiSrtSubtitleVo = getSubtitleVo(filePathName);
    	
    	System.out.println(smiSrtSubtitleVo.getFilePathNm());
    } 
    
    
    /*
     * 메인 : 샘파일를 파싱하여 VO로 리턴
     */
    public SmiSrtSubtitleVo getSubtitleVo(String subtitleFilePath) {
    	SmiSrtSubtitleVo smiSrtSubtitleVo = new SmiSrtSubtitleVo();    	
    	smiSrtSubtitleVo.setExtention(verifySrtSmi(subtitleFilePath));
    	if(smiSrtSubtitleVo.getExtention().equals("smi")) {
    		smiSrtSubtitleVo.setSmiList(parseSmiFile(subtitleFilePath));
            for (int i = 0; i < smiSrtSubtitleVo.getSmiList().size(); i++) {
            	smiSrtSubtitleVo.getSmiList().get(i).print();
    		}
    	} else {
    		smiSrtSubtitleVo.setSrtList(parseSrtFile(subtitleFilePath));
            for (int i = 0; i < smiSrtSubtitleVo.getSrtList().size(); i++) {
            	smiSrtSubtitleVo.getSrtList().get(i).print();
    		}
    	}
    	
    	return smiSrtSubtitleVo;
    }	


    /*
     * 메인 : Srt 파싱하여 Vo로 리턴
     */
    public ArrayList<SrtVo> parseSrtFile(String filePathName) {

    	ArrayList<SrtVo> srtList2 = new ArrayList<SrtVo>();
            	
        Pattern p; Matcher m;
        String parseRule;
        
        BufferedReader br = null;
        String src = "";
        try {

            File file = new File(filePathName);
            if (file.isFile()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),detectEncoding(filePathName)));
                //br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName)));
                
                int num = 0;
                int bnum = 0;
                int rnum = 0;
                String stime = "";
                String etime = "";
                ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
                
                String timeParseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                
                while (true) {
                    String str = br.readLine();
                    //System.out.println("str="+str);
                    if (str != null) {
                        if(str.matches("[0-9]+$")) {
                        } else if(str.matches(timeParseRule)) {
                        	//System.out.println("str="+str);
                        	num++;
                            parseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            stime = m.group();
                            //System.out.println(stime);

                            parseRule = "--> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            etime = m.group().substring(4);
                            //System.out.println(num + " "+ stime + " " + etime);
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
                for (int i = 0; i < srtList.size(); i++) {
                    if(srtList.get(i).getStime() == srtList.get(i+1).getStime()) {
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
        } catch (Exception e) {
            System.out.println("e=" + e.getMessage());
        }
        
        return srtList2;
        //String src2 = src.replaceAll("<br>\n", " ");
        //System.out.println(src2.substring(328+7,src2.length()));
        
    }

    
    /*
     * 메인 : Smi 파싱하여 Vo로 리턴
     */    
    public ArrayList<SmiVo> parseSmiFile(String filePathName) {

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
                ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();
                
                while (true) {
                    String str = br.readLine();
                    if (str != null) {
                        if(str.matches("<SYNC.*")) {
                            // rnum의 기반을 산출한다.
                            num++;
                            parseRule = "[0-9]+";
                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
                            stime = Integer.parseInt(m.group());
                            //System.out.println(str+" "+stime);
                        } else {
                            if(num>0){
                                // rnum별로 내용을 산출한다.
                                rnum++;
                                SmiVo smiVo = new SmiVo();
                                smiVo.num = rnum;
                                smiVo.stime = stime;
                                smiVo.etime = 0;
                                //smiList.set(rnum-2, etime).get(rnum-2).stime:333;
                                smiVo.content = str;
                                //smiVo.print();
                                smiList.add(smiVo);
                            }   
                        }
                    } else {
                        break;
                    }    
                }
                br.close();
                
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
                for (int i = 0; i < smiList.size(); i++) {
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
        } catch (Exception e) {
            System.out.println("e=" + e.getMessage());
        }
        
        //String src2 = src.replaceAll("<br>\n", " ");
        //System.out.println(src2.substring(328+7,src2.length()));
        return smiList2;
    }
    
    
    /*
     * 유틸 : 샘파일의 확장자(리턴:smi, srt)
     */
    String verifySrtSmi(String subtitleFilePath) {
    	//System.out.println("subtitleFilePath=="+subtitleFilePath);
    	//System.out.println("subtitleFilePath=="+subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()));
    	//System.out.println("subtitleFilePath=="+subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()).toLowerCase());
    	return subtitleFilePath.substring(subtitleFilePath.length()-3,subtitleFilePath.length()).toLowerCase();
    }   
        
    
    /*
     * 유틸 : 샘파일의 인코딩을 판단(리턴:UTF-8, EUC-KR...)
     */
    @SuppressWarnings("resource")
	String detectEncoding(String filePathName) {
    	
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
				System.out.println("Detected encoding = " + encoding);
			} else {
				System.out.println("No encoding detected.");
			}
			detector.reset();
			
			return encoding;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
    
    
    /*
     * 테스트     
     */
    public void test3(){
        System.out.println("test3");
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
        System.out.println("test2");
        Pattern p; Matcher m;
        String parseRule; boolean a;
        
        String src = "<SYNC Start=98679><P Class=KRCC>";
        parseRule = "[0-9]+";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
        m.find();
        System.out.println(m.group());
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
            System.out.println(String.valueOf(m.start()));
            System.out.println(String.valueOf(m.end()));
            System.out.println(m.group());
        }
        
        System.out.println(parseList);
        
        for (int i = 0; i < parseList.size(); i++) {
            System.out.println(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,src.length()));
                
            }
        }
        
    }


    
    /*
     * 테스트     
     */
    void test01(){
        //String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Valerian.and.the.City.of.a.Thousand.Planets.smi";
        String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/[VIP] Demon City Shinjuku.smi";
        //String smifilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Star Wars Rebels S02E01 - The Siege of Lothal.smi";
        //String srtfilePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy/Star Wars Rebels S02E01 - The Siege of Lothal.srt";
        
        ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
        ArrayList<SmiVo> smiList = new ArrayList<SmiVo>();

        //srtList = parseSubtitle.parseSrtFile3(srtfilePathName);
        smiList = parseSmiFile(smifilePathName);
        
        for (int i = 0; i < smiList.size(); i++) {
        	System.out.println(smiList.get(i).getContent());
		}

        for (int i = 0; i < srtList.size(); i++) {
        	//System.out.println(srtList.get(i).getNum());
        	//System.out.println(srtList.get(i).getContent());
        	System.out.println(smiList.get(i).getContent());
		}
        //parseSubtitle.test3();
    } 


    /*
     * 테스트     
     */
    public void parseSrtFile2(String filePathName) {
        //System.out.println(parseSrt.readFile(filePathName));
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
            //System.out.println(String.valueOf(m.start()));
            //System.out.println(String.valueOf(m.end()));
            //System.out.println(m.group());
        }
        
        //System.out.println(parseList);
        
        for (int i = 0; i < parseList.size(); i++) {
            System.out.println(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
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
        //System.out.println("src===\n"+src);
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
            //System.out.println(String.valueOf(m.start()));
            //System.out.println(String.valueOf(m.end()));
            //System.out.println(m.group());
        }
        
        System.out.println(parseList);
        
        for (int i = 0; i < parseList.size(); i++) {
            //System.out.println(parseList.get(i).get("time"));
            if(i<parseList.size()-1) {
                //System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
                //        ,Integer.parseInt(parseList.get(i+1).get("timeStart"))));
            } else {
                //System.out.println(src.substring( Integer.parseInt(parseList.get(i).get("timeEnd")) 
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
            System.out.println("e=" + e.getMessage());
        }
        
        //System.out.println(src);
        //System.out.println(src.replaceAll("<br>\n", " "));
        String src2 = src.replaceAll("<br>\n", " ");
        //String src3 = src2.substring(src2.indexOf("<body>"),src2.length()-1);
        //System.out.println(src3);
        //System.out.println(src2.indexOf("<body>"));
        System.out.println(src2.substring(328+7,src2.length()));
        
        
        //String src2 = src.replaceAll("<br>\n", " ");        
        //String src2 = src.replaceAll("<br>\n", " ");
        String aa = "123456778";
        System.out.println(aa.substring(2, 4));
        
        //        //System.out.println(parseSrt.readFile(filePathName));
//        String src = FileUtil.readFileEucKr(filePathName);
//        System.out.println(src);
//        
//        src = src.replaceAll("<br>","\n");
//        System.out.println(src);
        
        //System.out.println(StringUtil.uniCodeConvertor(src));

//        
//        String originalStr = FileUtil.readFileEucKr(filePathName); // 테스트 
//        String [] charSet = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
//          
//        for (int i=0; i<charSet.length; i++) {
//         for (int j=0; j<charSet.length; j++) {
//          try {
//           System.out.println("[" + charSet[i] +"," + charSet[j] +"] = " + new String(originalStr.getBytes(charSet[i]), charSet[j]));
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
//            System.out.println(m.group().replace("<i>","").replace("</i>",""));
//        }
    }

    /*
     * 테스트     
     */
    public void parseSrtFile4(String filePathName) {
        //System.out.println(parseSrt.readFile(filePathName));
        String src = FileUtil.readFile(filePathName);
        
        Pattern p; Matcher m;
        String parseRule; boolean a;

        parseRule = "<i>.*";
        p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
        while (a = m.find()) { 
            System.out.println(m.group().replace("<i>","").replace("</i>",""));
        }
    }
    
 
}
