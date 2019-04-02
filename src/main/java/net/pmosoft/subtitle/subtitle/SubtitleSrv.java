package net.pmosoft.subtitle.subtitle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.pmosoft.subtitle.file.FileSave;
import net.pmosoft.subtitle.parse.ParseSubtitle;
import net.pmosoft.subtitle.parse.SubtitlesVo;
import net.pmosoft.subtitle.usr.Usr;
import net.pmosoft.subtitle.usr.UsrDao;


@Service
public class SubtitleSrv {
    private static Logger logger = LoggerFactory.getLogger(SubtitleSrv.class);

    @Autowired
    private SubtitleDao subtitleDao;

    @Autowired
    private UsrDao usrDao;

    @Autowired
    private SubtitleValidatorSrv subtitleValidatorSrv;

    public Map<String, Object> saveUsrSubtitles(String usrId , MultipartFile foreignSubtitleFile, MultipartFile motherSubtitleFile) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {

            //------------------------------------------------
            logger.info("자막파일들 저장");
            //------------------------------------------------
            Map<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            FileSave fileSave = new FileSave();
            map = fileSave.saveUsrSubtitles(usrId, foreignSubtitleFile, motherSubtitleFile);

            //------------------------------------------------
            logger.info("유저 언어코드 정보  Vo 리턴");
            //------------------------------------------------
            Usr usr = new Usr(); usr.setUsrId(usrId);
            Usr usrOutVo = usrDao.selectUsr(usr);

            //------------------------------------------------
            logger.info("외국어 파싱후 자막 Vo 리턴");
            //------------------------------------------------
            ParseSubtitle parseSubtitle1 = new ParseSubtitle();
            SubtitlesVo foreignSubtitleVo = parseSubtitle1.execute(map.get("subtitleFilePathList").get(0),usrOutVo.getFlangCd());

            //------------------------------------------------
            logger.info("모국어 파싱후 자막 Vo 리턴");
            //------------------------------------------------
            ParseSubtitle parseSubtitle2 = new ParseSubtitle();
            SubtitlesVo motherSubtitleVo = parseSubtitle2.execute(map.get("subtitleFilePathList").get(1),usrOutVo.getMlangCd());

            //------------------------------------------------
            logger.info("자막리스트들을 DB에 저장");
            //------------------------------------------------
            Subtitle subtitle = new Subtitle();

            // 유저 자막 정보 저장
            subtitle.setUsrId(usrId);
            subtitle.setSttlNm(foreignSubtitleFile.getOriginalFilename());
            subtitleDao.deleteUsrSttlMstr(subtitle);
            subtitleDao.insertUsrSttlMstr(subtitle);

            // 유저 자막 책갈피
            subtitleDao.deleteUsrSttlDtl(subtitle);
            subtitleDao.deleteSttlNum(subtitle);
            subtitle.setSttlCd("1");subtitleDao.insertSttlNum(subtitle);
            subtitle.setSttlCd("2");subtitleDao.insertSttlNum(subtitle);

            // 유저 외국어 자막 내용  저장
            String foreignSubtitle = "", motherSubtitle="";
            foreignSubtitle = insertUsrSttlDtlBulk(usrId, foreignSubtitleFile.getOriginalFilename(), "1", foreignSubtitleVo);
            motherSubtitle = insertUsrSttlDtlBulk(usrId, foreignSubtitleFile.getOriginalFilename(), "2", motherSubtitleVo);

            //String foreignSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "1", foreignSubtitleVo);
            //String motherSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "2", motherSubtitleVo);

            //------------------------------------------------
            logger.info("자막정보를 화면으로 리턴");
            //------------------------------------------------
            result.put("isSuccess", true);
            result.put("usrMsg", "정상 처리 되었습니다");
            result.put("sttlNm", foreignSubtitleFile.getOriginalFilename());
            result.put("foreignSubtitle", foreignSubtitle);
            result.put("motherSubtitle", motherSubtitle);
        } catch (Exception e){
            e.printStackTrace();
            result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
            result.put("errSysMsg", e.toString());
        }
        return result;
    }

    public Map<String, Object> saveSttlNum(Subtitle inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	System.out.println("inVo==="+inVo.getUsrId());
        	System.out.println("inVo==="+inVo.getSttlNm());
            subtitleDao.updateSttlNum(inVo);
            result.put("isSuccess", true);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> saveReviewSttl(Subtitle inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	System.out.println("inVo==="+inVo.getUsrId());
        	System.out.println("inVo==="+inVo.getSttlNm());
            subtitleDao.insertReviewSttl(inVo);
            result.put("isSuccess", true);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    private String insertUsrSttlDtl(String usrId, String fileName, String sttlCd, SubtitlesVo SubtitlesVo){

        String result = "";

        if(SubtitlesVo.getExtention().equals("smi")) {
            for (int i = 0; i < SubtitlesVo.getSmiList().size(); i++) {
                Subtitle subtitle = new Subtitle();
                subtitle.setUsrId(usrId);
                subtitle.setSttlNm(fileName);
                subtitle.setSttlCd(sttlCd);
                subtitle.setSttlNum(i);
                subtitle.setSttlStm(SubtitlesVo.getSmiList().get(i).getStime()+"");
                subtitle.setSttlEtm(SubtitlesVo.getSmiList().get(i).getEtime()+"");
                subtitle.setSttlDesc(SubtitlesVo.getSmiList().get(i).getContent());
                subtitleDao.insertUsrSttlDtl(subtitle);
                result += SubtitlesVo.getSmiList().get(i).getContent() + "\n";
            }
        } else {
            for (int i = 0; i < SubtitlesVo.getSrtList().size(); i++) {
                Subtitle subtitle = new Subtitle();
                subtitle.setUsrId(usrId);
                subtitle.setSttlNm(fileName);
                subtitle.setSttlCd(sttlCd);
                subtitle.setSttlNum(i);
                subtitle.setSttlStm(SubtitlesVo.getSrtList().get(i).getStime()+"");
                subtitle.setSttlEtm(SubtitlesVo.getSrtList().get(i).getEtime()+"");
                subtitle.setSttlDesc(SubtitlesVo.getSrtList().get(i).getContent());
                subtitleDao.insertUsrSttlDtl(subtitle);
                result += SubtitlesVo.getSrtList().get(i).getContent() + "\n";
            }
        }

        return result;
    }

    private String insertUsrSttlDtlBulk(String usrId, String fileName, String sttlCd, SubtitlesVo SubtitlesVo){

	    String result = "";
	    int rowCnt = 0;
	    int commitCnt = 500;
        if(SubtitlesVo.getExtention().equals("smi")) {
            logger.info("smi start cnt="+SubtitlesVo.getSmiList().size());
            List<Subtitle> usrSttlListVo = new ArrayList<Subtitle>();
            int sttlNum = 0;

        	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(0).getContent());
        	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(1).getContent());
        	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(2).getContent());
        	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(3).getContent());
        	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(4).getContent());

            for (int i = 0; i < SubtitlesVo.getSmiList().size(); i++) {
            	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(i).getContent());

            	if(SubtitlesVo.getSmiList().get(i).getContent().trim().length() != 0) {
                	logger.debug("getContent()=="+SubtitlesVo.getSmiList().get(i).getContent().trim().length()+":"+SubtitlesVo.getSmiList().get(i).getContent());
                	sttlNum++;
                    Subtitle subtitle = new Subtitle();
                    subtitle.setUsrId(usrId);
                    subtitle.setSttlNm(fileName);
                    subtitle.setSttlCd(sttlCd);
                    subtitle.setSttlNum(sttlNum);
                    subtitle.setSttlStm(SubtitlesVo.getSmiList().get(i).getStime()+"");
                    subtitle.setSttlEtm(SubtitlesVo.getSmiList().get(i).getEtime()+"");
                    subtitle.setSttlDesc(SubtitlesVo.getSmiList().get(i).getContent());
                    usrSttlListVo.add(subtitle);
                    result += SubtitlesVo.getSmiList().get(i).getContent() + "\n";
                    rowCnt++;
                }
                if(i%commitCnt == 0) {
                    logger.debug("rowCnt1=========="+rowCnt);
                   subtitleDao.insertUsrSttlDtlBulk(usrSttlListVo);
                   usrSttlListVo.clear();
                   rowCnt = 0;
                }
            }

            if(rowCnt < commitCnt) {
                logger.debug("rowCnt2=========="+rowCnt);
               subtitleDao.insertUsrSttlDtlBulk(usrSttlListVo);
            }

        } else {
            logger.info("srt start");
            List<Subtitle> usrSttlListVo = new ArrayList<Subtitle>();
            int sttlNum = 0;
            for (int i = 0; i < SubtitlesVo.getSrtList().size(); i++) {
            	if(SubtitlesVo.getSrtList().get(i).getContent().trim().length() != 0) {
	            	sttlNum++;
	            	Subtitle subtitle = new Subtitle();
	                subtitle.setUsrId(usrId);
	                subtitle.setSttlNm(fileName);
	                subtitle.setSttlCd(sttlCd);
	                subtitle.setSttlNum(sttlNum);
	                subtitle.setSttlStm(SubtitlesVo.getSrtList().get(i).getStime()+"");
	                subtitle.setSttlEtm(SubtitlesVo.getSrtList().get(i).getEtime()+"");
	                subtitle.setSttlDesc(SubtitlesVo.getSrtList().get(i).getContent());
	                usrSttlListVo.add(subtitle);
	                result += SubtitlesVo.getSrtList().get(i).getContent() + "\n";
	                rowCnt++;
            	}
                if(i%commitCnt == 0) {
                   logger.debug("rowCnt1=========="+rowCnt);
                   subtitleDao.insertUsrSttlDtlBulk(usrSttlListVo);
                    usrSttlListVo.clear();
                    rowCnt = 0;
                 }
            }

               if(rowCnt < commitCnt) {
                   logger.debug("rowCnt2=========="+rowCnt);
                   subtitleDao.insertUsrSttlDtlBulk(usrSttlListVo);
               }
           }
        return result;
    }


    public Map<String, Object> selectUsrRecentlySttl(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Subtitle> list = subtitleDao.selectUsrRecentlySttl(inVo);
            List<Subtitle> foreignSubtitle = new ArrayList<Subtitle>();
            List<Subtitle> motherSubtitle = new ArrayList<Subtitle>();

                  for (int i = 0; i < list.size(); i++) {
                      if(list.get(i).getSttlCd().equals("1"))
                          foreignSubtitle.add(list.get(i));
                      else motherSubtitle.add(list.get(i));
                  }

                  //System.out.println("foreignSubtitle="+foreignSubtitle);

            result.put("isSuccess", true);
            result.put("subtitleListVo", list);
            result.put("foreignSubtitle", foreignSubtitle);
            result.put("motherSubtitle", motherSubtitle);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectUsrSttl(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Subtitle> list = subtitleDao.selectUsrSttl(inVo);
            List<Subtitle> foreignSubtitle = new ArrayList<Subtitle>();
            List<Subtitle> motherSubtitle = new ArrayList<Subtitle>();

            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getSttlCd().equals("1"))
                    foreignSubtitle.add(list.get(i));
                else motherSubtitle.add(list.get(i));
            }

            //System.out.println("foreignSubtitle="+foreignSubtitle);

            result.put("isSuccess", true);
            result.put("subtitleListVo", list);
            result.put("foreignSubtitle", foreignSubtitle);
            result.put("motherSubtitle", motherSubtitle);

        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectUsrSttlMstrList(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Subtitle> subtitleList = null;
            subtitleList = subtitleDao.selectUsrSttlMstrList(inVo);
            result.put("isSuccess", true);
            result.put("subtitleList", subtitleList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectUsrSttlDtlList(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Subtitle> list = null;
            list = subtitleDao.selectUsrSttlDtlList(inVo);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectReviewSttlList(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Subtitle> list = null;
            list = subtitleDao.selectReviewSttlList(inVo);;
            result.put("isSuccess", true);
            result.put("reviewSubtitles", list);

        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> updateReviewCnt(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> errors = new HashMap<String, String>();
        //errors = subtitleValidatorSrv.validateDeleteUsr(usr);
        if(errors.size()>0){
            //model.addAttribute("tbUsr", tbUsr);
            result.put("isSuccess", false);
            result.put("errUsrMsg", errors.get("errUsrMsg"));
            System.out.println(result);
            return result;
        } else {
            subtitleDao.updateReviewCnt(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "갱신 되었습니다");
            return result;
        }
    }

    public Map<String, Object> deleteUsrSttl(Subtitle inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> errors = new HashMap<String, String>();
        //errors = subtitleValidatorSrv.validateDeleteUsr(usr);
        if(errors.size()>0){
            //model.addAttribute("tbUsr", tbUsr);
            result.put("isSuccess", false);
            result.put("errUsrMsg", errors.get("errUsrMsg"));
            System.out.println(result);
            return result;
        } else {
            subtitleDao.deleteUsrSttlMstr(inVo);
            subtitleDao.deleteUsrSttlDtl(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "삭제 되었습니다");
            return result;
        }
    }
}
