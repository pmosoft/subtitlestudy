//package net.pmosoft.subtitle;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import net.pmosoft.fframe.gens.pgm.copy.GensSpringByCopy;
//
//public class GensSubtitlestudySpringByCopy {
//
//    public static void main(String[] args) {
//        GensSpringByCopy gensSpringByCopy = new GensSpringByCopy();
//        gensSpringByCopy.setPrjNm("subtitlestudy"); //프로젝트명
//        
//        
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("srcPackNm", "net.pmosoft.subtitle.parse");
//        params.put("srcPgmNm" , "Parse");
//        params.put("tarPackNm", "net.pmosoft.subtitle.subtitle");
//        params.put("tarPgmNm" , "Subtitle");
//        
//        try {
//            gensSpringByCopy.createPgmFile(params);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//    }
//    
//}
