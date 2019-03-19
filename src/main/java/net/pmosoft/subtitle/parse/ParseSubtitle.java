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

	public String pathFileNm = "";

	public ParseSubtitle(){}
	public ParseSubtitle(String pathFileNm){
		this.pathFileNm = pathFileNm;
	}

	private static Logger logger = LoggerFactory.getLogger(ParseSubtitle.class);

    public static void main(String[] args) throws Exception {
        //String s1 = "<i>Metal </i>";
        //logger.debug(s1.matches("<i>")
    	//String content = "&nbsp;";
    	//System.out.println(content.contentEquals("nbsp"));
    	//System.out.println(content..contentEquals("nbsp"));

        ParseSubtitle parseSubtitle = new ParseSubtitle();
        parseSubtitle.test02();
        //File I = new File("/home/subtitle/files/kmjwhite@hanmail.net/Star.Wars.Episode.1.The.Phantom.Menace.1999.1080p.BluRay.xnHD.x264-NhaNc3.srt");
        //I.delete();
    }

    /*
     * 테스트
     */
    void test02() throws Exception{
    	String filePathName="d:\\Downloads\\익스팬스\\[ HD ] 익스팬스 시즌2 01-13화 완 한글자막 720p The Expanse\\The.Expanse.S01E02.720p.HDTV.x264-0SEC.smi";
    	//String filePathName="d:\\Downloads\\subtitle-sample\\Sinbad.Legend.Of.The.Seven.Seas.2003.720p.BluRay.x264-[YTS.AM].srt";
    	//String filePathName="d:\\Downloads\\subtitle-sample\\Sinbad.Legend.Of.The.Seven.Seas.2003.720p.BluRay.x264-[YTS.AM].cht.srt";

    	SubtitlesVo subtitlesVo = execute(filePathName);
    	logger.debug(subtitlesVo.getFilePathNm());
    	//System.out.println(SubtitlesVo);
    }

    public SubtitlesVo execute(String pathFileNm)  throws Exception {
    	SubtitlesVo subtitlesVo = new SubtitlesVo();

    	ParseSrt parseSrt = new ParseSrt();
    	ParseSmi parseSmi = new ParseSmi();

    	try {
        	logger.info(pathFileNm +":"+ verifySrtSmi(pathFileNm));
        	subtitlesVo.setExtention(verifySrtSmi(pathFileNm));
        	if(subtitlesVo.getExtention().equals("smi")) {
        		logger.info("smi");
        		subtitlesVo.setSmiList(parseSmi.excute(pathFileNm));
                for (int i = 0; i < subtitlesVo.getSmiList().size(); i++) {
                	subtitlesVo.getSmiList().get(i).print();
        		}
        	} else {
        		logger.info("srt");
        		subtitlesVo.setSrtList(parseSrt.excute(pathFileNm));
                for (int i = 0; i < subtitlesVo.getSrtList().size(); i++) {
                	subtitlesVo.getSrtList().get(i).print();
        		}
        	}
        	return subtitlesVo;

		} catch(Exception e) { e.printStackTrace(); throw e; }
    }


    /*
     * 유틸 : 샘파일의 확장자(리턴:smi, srt)
     */
    String verifySrtSmi(String pathFileNm) {
    	//logger.debug("pathFileNm=="+pathFileNm);
    	//logger.debug("pathFileNm=="+pathFileNm.substring(pathFileNm.length()-3,pathFileNm.length()));
    	//logger.debug("pathFileNm=="+pathFileNm.substring(pathFileNm.length()-3,pathFileNm.length()).toLowerCase());
    	return pathFileNm.substring(pathFileNm.length()-3,pathFileNm.length()).toLowerCase();
    }


}
