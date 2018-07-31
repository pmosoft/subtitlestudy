package net.pmosoft.subtitle.subtitle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.subtitle.file.FileSave;
import net.pmosoft.subtitle.parse.ParseSubtitle;
import net.pmosoft.subtitle.parse.SmiSrtSubtitleVo;
import net.pmosoft.subtitle.usr.Usr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class SubtitleSrv {

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
        	String foreignSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "1", foreignSubtitleVo);
        	String motherSubtitle = insertUsrSttlDtl(usrId, foreignSubtitleFile.getOriginalFilename(), "2", motherSubtitleVo);
        	
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
    			UsrSttlVo usrForiegnSttlVo = new UsrSttlVo();
            	usrForiegnSttlVo.setUsrId(usrId);
            	usrForiegnSttlVo.setSttlNm(fileName);
            	usrForiegnSttlVo.setSttlCd(sttlCd);
          		usrForiegnSttlVo.setSttlStm(smiSrtSubtitleVo.getSmiList().get(i).getStime()+"");
          		usrForiegnSttlVo.setSttlEtm(smiSrtSubtitleVo.getSmiList().get(i).getEtime()+"");
          		usrForiegnSttlVo.setSttlDesc(smiSrtSubtitleVo.getSmiList().get(i).getContent());
           		subtitleDao.insertUsrSttlDtl(usrForiegnSttlVo);
           		result += smiSrtSubtitleVo.getSmiList().get(i).getContent() + "\n";
			}
    	} else {
    		for (int i = 0; i < smiSrtSubtitleVo.getSrtList().size(); i++) {
    			UsrSttlVo usrForiegnSttlVo = new UsrSttlVo();
            	usrForiegnSttlVo.setUsrId(usrId);
            	usrForiegnSttlVo.setSttlNm(fileName);
            	usrForiegnSttlVo.setSttlCd(sttlCd);
          		usrForiegnSttlVo.setSttlStm(smiSrtSubtitleVo.getSrtList().get(i).getStime()+"");
          		usrForiegnSttlVo.setSttlEtm(smiSrtSubtitleVo.getSrtList().get(i).getEtime()+"");
          		usrForiegnSttlVo.setSttlDesc(smiSrtSubtitleVo.getSrtList().get(i).getContent());
          		subtitleDao.insertUsrSttlDtl(usrForiegnSttlVo);
           		result += smiSrtSubtitleVo.getSrtList().get(i).getContent() + "\n";
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
   	       	
   	       	System.out.println("foreignSubtitle="+foreignSubtitle);
   	       	
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
            List<UsrSttlVo> list = null;
            list = subtitleDao.selectUsrSttlMstrList(inVo);;
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
