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

public class DetectEncoding {

	private static Logger logger = LoggerFactory.getLogger(DetectEncoding.class);

	public String pathFileNm = "";

	DetectEncoding(){}
	DetectEncoding(String pathFileNm) {
		this.pathFileNm = pathFileNm;
	}

    public static void main(String[] args) throws Exception {
        //String s1 = "<i>Metal </i>";
        //logger.debug(s1.matches("<i>")
    	//String content = "&nbsp;";
    	//System.out.println(content.contentEquals("nbsp"));
    	//System.out.println(content..contentEquals("nbsp"));

        DetectEncoding parseSubtitle = new DetectEncoding();
        //File I = new File("/home/subtitle/files/kmjwhite@hanmail.net/Star.Wars.Episode.1.The.Phantom.Menace.1999.1080p.BluRay.xnHD.x264-NhaNc3.srt");
        //I.delete();
    }

    /*
     * 유틸 : 샘파일의 인코딩을 판단(리턴:UTF-8, EUC-KR...)
     */
    @SuppressWarnings("resource")
	String execute(String filePathName) throws Exception {

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
			logger.info("encoding="+encoding);

			//encoding = encoding.replace("WINDOWS-1252", "EUC-KR");
			if (encoding != null) {
				logger.info("Detected encoding = " + encoding);
			} else {
				logger.info("No encoding detected.");
				encoding = "EUC-KR";
				//encoding = "UTF-8";
				logger.info("encoding="+encoding);
			}
			detector.reset();

			return encoding;
		} catch(Exception e) { e.printStackTrace(); throw e; }

	}

}
