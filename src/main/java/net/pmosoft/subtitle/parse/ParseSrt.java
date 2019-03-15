//package net.pmosoft.subtitle.parse;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.mozilla.universalchardet.UniversalDetector;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import net.pmosoft.subtitle.file.FileUtil;
//
//public class ParseSrt {
//
//	public String pathFileNm = "";
//
//	ParseSrt(){}
//	ParseSrt(String pathFileNm){
//		this.pathFileNm = pathFileNm;
//	}
//
//	private static Logger logger = LoggerFactory.getLogger(ParseSrt.class);
//
//    public static void main(String[] args) throws Exception {
//        //String s1 = "<i>Metal </i>";
//        //logger.debug(s1.matches("<i>")
//    	//String content = "&nbsp;";
//    	//System.out.println(content.contentEquals("nbsp"));
//    	//System.out.println(content..contentEquals("nbsp"));
//
//        ParseSrt parseSubtitle = new ParseSrt();
//        parseSubtitle.test02();
//        //File I = new File("/home/subtitle/files/kmjwhite@hanmail.net/Star.Wars.Episode.1.The.Phantom.Menace.1999.1080p.BluRay.xnHD.x264-NhaNc3.srt");
//        //I.delete();
//    }
//
//    /*
//     * 테스트
//     */
//    void test02() throws Exception{
//        //String filePathName = "C:/fframe/workspace/subtitlestudy/file/lifedomy@gmail.com/Star.Trek.Beyond.2016.1080p.BluRay.x264-SPARKS-complete.smi";
//    	//String filePathName="d:\\Downloads\\마블\\478져스 인피니티 워] x265코덱 8채널 사운드 Blu-Ray 1080P\\Avengers.Infinity.War.english.srt";
//    	//String filePathName="d:\\Downloads\\마블\\478져스 인피니티 워] x265코덱 8채널 사운드 Blu-Ray 1080P\\Avengers.Infinity.War.brazilian.srt";
//    	//String filePathName="d:\\Downloads\\마블\\478져스 인피니티 워] x265코덱 8채널 사운드 Blu-Ray 1080P\\Avengers.Infinity.War.german.srt";
//    	//String filePathName="d:\\Downloads\\익스팬스\\[ HD ] 익스팬스 시즌2 01-13화 완 한글자막 720p The Expanse\\The.Expanse.S02E04.720p.HDTV.x264-SVA.smi";
//    	String filePathName="d:\\Downloads\\subtitle-sample\\Sinbad.Legend.Of.The.Seven.Seas.2003.720p.BluRay.x264-[YTS.AM].srt";
//    	//String filePathName="d:\\Downloads\\subtitle-sample\\Sinbad.Legend.Of.The.Seven.Seas.2003.720p.BluRay.x264-[YTS.AM].cht.srt";
//
//    	SubtitlesVo subtitlesVo = getSubtitleVo(filePathName);
//    	logger.debug(subtitlesVo.getFilePathNm());
//    	//System.out.println(SubtitlesVo);
//    }
//
//
//    /*
//     * 메인 : Srt 파싱하여 Vo로 리턴
//     */
//    public ArrayList<SrtVo> parseSrtFile(String filePathName) throws Exception {
//
//        logger.debug("parseSrtFile");
//
//    	ArrayList<SrtVo> srtList2 = new ArrayList<SrtVo>();
//
//        Pattern p; Matcher m;
//        String parseRule;
//
//        BufferedReader br = null;
//        String src = "";
//        ArrayList<SrtVo> srtList = new ArrayList<SrtVo>();
//
//        try {
//
//            File file = new File(filePathName);
//
//            if (file.isFile()) {
//                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),detectEncoding(filePathName)));
//
//                int num = 0;
//                int bnum = 0;
//                int rnum = 0;
//                String stime = "";
//                String etime = "";
//
//                String timeParseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
//
//                while (true) {
//                    String str = br.readLine();
//                    //str = str.replace("<br>"," ");
//                    logger.debug("str="+str);
//                    if (str != null) {
//                        if(str.matches("[0-9]+$")) {
//                        } else if(str.matches(timeParseRule)) {
//                        	//logger.debug("str="+str);
//                        	num++;
//                            parseRule = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
//                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
//                            stime = m.group();
//                            //logger.debug(stime);
//
//                            parseRule = "--> [0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]";
//                            p = Pattern.compile(parseRule); m = p.matcher(str); m.find();
//                            etime = m.group().substring(4);
//                            //logger.debug(num + " "+ stime + " " + etime);
//                        } else {
//                            if(num>0){
//                                // rnum별로 내용을 산출한다.
//                                rnum++;
//                                SrtVo srtVo = new SrtVo();
//                                srtVo.num = rnum;
//                                srtVo.stime = stime;
//                                srtVo.etime = etime;
//                                srtVo.content = str;
//                                //srtVo.print();
//                                srtList.add(srtVo);
//                            }
//                        }
//                    } else {
//                        break;
//                    }
//                }
//                br.close();
//
//                num = 0;
//                // stime별로 content를 합친다
//                String content2 = "";
//                for (int i = 0; i < srtList.size()-1; i++) {
//                	logger.debug(srtList.size() +":"+ i);
//                	logger.debug(srtList.get(i).getContent());
//                	if(srtList.get(i).getStime() == srtList.get(i+1).getStime()) {
//                        //logger.debug(srtList.get(i).getContent());
//                    	content2 += srtList.get(i).getContent() + " ";
//                    } else {
//                        SrtVo tvo = new SrtVo();
//                        tvo.setNum(++num);
//                        tvo.setStime(srtList.get(i).getStime());
//                        tvo.setEtime(srtList.get(i+1).getStime());
//                        tvo.setContent(content2+srtList.get(i).getContent());
//                        content2 = "";
//                        //tvo.print();
//                        srtList2.add(tvo);
//                    }
//                }
//            }
//        } catch(Exception e) { e.printStackTrace(); throw e; }
//
//
//        return srtList2;
//        //String src2 = src.replaceAll("<br>\n", " ");
//        //logger.debug(src2.substring(328+7,src2.length()));
//
//    }
//
//}
