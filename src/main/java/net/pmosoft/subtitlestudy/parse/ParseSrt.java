package net.pmosoft.subtitlestudy.parse;
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

import net.pmosoft.fframe.comm.util.FileUtil;

public class ParseSrt {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String s1 = "<i>Metal </i>";
        
        //System.out.println(s1.matches("<i>")
        
        ParseSrt parseSrt = new ParseSrt();
        //String srtfilePathName = "d:/Videos/sincity.srt";
        //parseSrt.parseSrtFile(srtfilePathName);

        String smifilePathName = "d:/Videos/sincity.smi";
        parseSrt.parseSmiFile(smifilePathName);
        //parseSrt.test();
    }

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

    public void parseSrtFile(String filePathName) {
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

    public void parseSmiFile(String filePathName) {
        //System.out.println(parseSrt.readFile(filePathName));
        String src = FileUtil.readFileEucKr(filePathName);
        
        Pattern p; Matcher m;
        String parseRule; boolean a;
//<SYNC Start=41587><P Class=KRCC>
        parseRule = "<SYNC Start=[0-9]*>[<P Class=KRCC>]+";
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
    
    
    public void parseSrtFile2(String filePathName) {
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
    
    
}
