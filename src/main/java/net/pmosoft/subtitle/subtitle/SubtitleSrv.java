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
import net.pmosoft.subtitle.parse.SmiSrtSubtitleVo;


@Service
public class SubtitleSrv {

	private static Logger logger = LoggerFactory.getLogger(ParseSubtitle.class);
	
    @Autowired
    private SubtitleDao subtitleDao;

    @Autowired
    private SubtitleValidatorSrv subtitleValidatorSrv;

    public Map<String, Object> saveUsrSubtitles(String usrId, MultipartFile foreignSubtitleFile, MultipartFile motherSubtitleFile) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
		
	        /************************************************
             * 자막파일들 저장
             ************************************************/
            Map<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            FileSave fileSave = new FileSave();
            map = fileSave.saveUsrSubtitles(usrId, foreignSubtitleFile, motherSubtitleFile);
           
            /************************************************
             * 파싱 자막 Vo
             ************************************************/
            ParseSubtitle parseSubtitle1 = new ParseSubtitle();
            ParseSubtitle parseSubtitle2 = new ParseSubtitle();
        	SmiSrtSubtitleVo foreignSubtitleVo = parseSubtitle1.getSubtitleVo(map.get("subtitleFilePathList").get(0));
        	SmiSrtSubtitleVo motherSubtitleVo = parseSubtitle2.getSubtitleVo(map.get("subtitleFilePathList").get(1));

//        	System.out.println(map.get("subtitleFilePathList").get(0));
//        	System.out.println(map.get("subtitleFilePathList").get(1));
//        	System.out.println("11="+foreignSubtitleVo.getSmiList().size());
//        	System.out.println("11="+foreignSubtitleVo.getSrtList().size());
//        	System.out.println("11="+motherSubtitleVo.getSmiList().size());
//        	System.out.println("22="+motherSubtitleVo.getSrtList().size());
        	
        	
            /************************************************
             * 자막 저장
             ************************************************/
        	UsrSttlVo usrSttlVo = new UsrSttlVo();
        	
        	// 유저 자막 정보 저장
        	usrSttlVo.setUsrId(usrId);
        	usrSttlVo.setSttlNm(foreignSubtitleFile.getOriginalFilename());
        	subtitleDao.deleteUsrSttlMstr(usrSttlVo);
        	subtitleDao.insertUsrSttlMstr(usrSttlVo);
        	
        	// 유저 외국어 자막 내용  저장
        	subtitleDao.deleteUsrSttlDtl(usrSttlVo);
        	String foreignSubtitle = insertUsrSttlDtlBulk(usrId, foreignSubtitleFile.getOriginalFilename(), "1", foreignSubtitleVo);
        	String motherSubtitle = insertUsrSttlDtlBulk(usrId, foreignSubtitleFile.getOriginalFilename(), "2", motherSubtitleVo);

        	//String foreignSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "1", foreignSubtitleVo);
        	//String motherSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "2", motherSubtitleVo);
        	
           	result.put("isSuccess", true);
   	       	result.put("usrMsg", "정상 처리 되었습니다");
   	       	result.put("foreignSubtitle", foreignSubtitle);
   	       	result.put("motherSubtitle", motherSubtitle);
		} catch (Exception e){
			e.printStackTrace();
			result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
			//result.put("errSysMsg", e.toString());
		}
		return result;
    }  
    
    private String insertUsrSttlDtl(String usrId, String fileName, String sttlCd, SmiSrtSubtitleVo smiSrtSubtitleVo){
    	
    	String result = "";

    	if(smiSrtSubtitleVo.getExtention().equals("smi")) {
    		for (int i = 0; i < smiSrtSubtitleVo.getSmiList().size(); i++) {
    			UsrSttlVo usrSttlVo = new UsrSttlVo();
            	usrSttlVo.setUsrId(usrId);
            	usrSttlVo.setSttlNm(fileName);
            	usrSttlVo.setSttlCd(sttlCd);
          		usrSttlVo.setSttlStm(smiSrtSubtitleVo.getSmiList().get(i).getStime()+"");
          		usrSttlVo.setSttlEtm(smiSrtSubtitleVo.getSmiList().get(i).getEtime()+"");
          		usrSttlVo.setSttlDesc(smiSrtSubtitleVo.getSmiList().get(i).getContent());
           		subtitleDao.insertUsrSttlDtl(usrSttlVo);
           		result += smiSrtSubtitleVo.getSmiList().get(i).getContent() + "\n";
			}
    	} else {
    		for (int i = 0; i < smiSrtSubtitleVo.getSrtList().size(); i++) {
    			UsrSttlVo usrSttlVo = new UsrSttlVo();
            	usrSttlVo.setUsrId(usrId);
            	usrSttlVo.setSttlNm(fileName);
            	usrSttlVo.setSttlCd(sttlCd);
          		usrSttlVo.setSttlStm(smiSrtSubtitleVo.getSrtList().get(i).getStime()+"");
          		usrSttlVo.setSttlEtm(smiSrtSubtitleVo.getSrtList().get(i).getEtime()+"");
          		usrSttlVo.setSttlDesc(smiSrtSubtitleVo.getSrtList().get(i).getContent());
          		subtitleDao.insertUsrSttlDtl(usrSttlVo);
           		result += smiSrtSubtitleVo.getSrtList().get(i).getContent() + "\n";
			}
    	}
    	
    	return result;
    }

    private String insertUsrSttlDtlBulk(String usrId, String fileName, String sttlCd, SmiSrtSubtitleVo smiSrtSubtitleVo){
    	
    	String result = "";
    	int rowCnt = 0;
    	int commitCnt = 5000;
    	if(smiSrtSubtitleVo.getExtention().equals("smi")) {
   			logger.info("smi start");
			List<UsrSttlVo> usrSttlListVo = new ArrayList<UsrSttlVo>();
    		for (int i = 0; i < smiSrtSubtitleVo.getSmiList().size(); i++) {
    			UsrSttlVo usrSttlVo = new UsrSttlVo();
            	usrSttlVo.setUsrId(usrId);
            	usrSttlVo.setSttlNm(fileName);
            	usrSttlVo.setSttlCd(sttlCd);
          		usrSttlVo.setSttlStm(smiSrtSubtitleVo.getSmiList().get(i).getStime()+"");
          		usrSttlVo.setSttlEtm(smiSrtSubtitleVo.getSmiList().get(i).getEtime()+"");
          		usrSttlVo.setSttlDesc(smiSrtSubtitleVo.getSmiList().get(i).getContent());
          		usrSttlListVo.add(usrSttlVo);
           		result += smiSrtSubtitleVo.getSmiList().get(i).getContent() + "\n";
				
           		rowCnt++;	
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
			List<UsrSttlVo> usrSttlListVo = new ArrayList<UsrSttlVo>();
    		for (int i = 0; i < smiSrtSubtitleVo.getSrtList().size(); i++) {
    			UsrSttlVo usrSttlVo = new UsrSttlVo();
            	usrSttlVo.setUsrId(usrId);
            	usrSttlVo.setSttlNm(fileName);
            	usrSttlVo.setSttlCd(sttlCd);
          		usrSttlVo.setSttlStm(smiSrtSubtitleVo.getSrtList().get(i).getStime()+"");
          		usrSttlVo.setSttlEtm(smiSrtSubtitleVo.getSrtList().get(i).getEtime()+"");
          		usrSttlVo.setSttlDesc(smiSrtSubtitleVo.getSrtList().get(i).getContent());
          		usrSttlListVo.add(usrSttlVo);
          		result += smiSrtSubtitleVo.getSrtList().get(i).getContent() + "\n";
          		
           		rowCnt++;	
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
    
    
    public Map<String, Object> selectUsrRecentlySttl(String usrId){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<UsrSttlVo> list = null;
            list = subtitleDao.selectUsrRecentlySttl(usrId);
            
            String foreignSubtitle = "";
   	       	String motherSubtitle = "";
            
   	       	for (int i = 0; i < list.size(); i++) {
   	       		if(list.get(i).getSttlCd().equals("1"))
   	       			foreignSubtitle += list.get(i).getSttlDesc() + "\n"; 
   	       		else motherSubtitle += list.get(i).getSttlDesc() + "\n";
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
    
    
    public Map<String, Object> selectUsrSttlMstrList(UsrSttlVo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<UsrSttlVo> usrSttlVoList = null;
            usrSttlVoList = subtitleDao.selectUsrSttlMstrList(inVo);;
            result.put("isSuccess", true);
            result.put("usrSttlVoList", usrSttlVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    public Map<String, Object> selectUsrSttlDtlList(UsrSttlVo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<UsrSttlVo> list = null;
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

	public Map<String, Object> deleteUsrSttl(UsrSttlVo inVo){
		
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
