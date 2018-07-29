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
		Map<String, String> errors = new HashMap<String, String>();

		try {
		
			//errors = usrValidatorSrv.validateInsertUsr(usr);
			System.out.println(errors);
			if(errors.size()>0){
				result.put("isSuccess", false);
				result.put("errUsrMsg", errors.get("errUsrMsg"));
				return result;
			}
		
	        /************************************************
             * 자막파일들 저장
             ************************************************/
            Map<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            FileSave fileSave = new FileSave();
            map = fileSave.saveUsrSubtitles(usrId, foreignSubtitleFile, motherSubtitleFile);
           
            /************************************************
             * 외국어 자막 Vo
             ************************************************/
            ParseSubtitle parseSubtitle = new ParseSubtitle();
        	SmiSrtSubtitleVo foreignSubtitleVo = parseSubtitle.getSubtitleVo(map.get("subtitleFilePathList").get(0));
        	SmiSrtSubtitleVo motherSubtitleVo = parseSubtitle.getSubtitleVo(map.get("subtitleFilePathList").get(1));

            /************************************************
             * 외국어 자막 저장
             ************************************************/
        	UsrSttlVo usrSttlVo = new UsrSttlVo();
        	
        	// 유저 자막 정보 저장
        	usrSttlVo.setUsrId(usrId);
        	usrSttlVo.setSttlNm(foreignSubtitleFile.getOriginalFilename());
        	subtitleDao.deleteUsrSttlMstr(usrSttlVo);
        	subtitleDao.insertUsrSttlMstr(usrSttlVo);
        	
        	// 유저 외국어 자막 내용  저장
        	subtitleDao.deleteUsrSttlDtl(usrSttlVo);
        	if(foreignSubtitleVo.getExtention().equals("smi")) {
        		for (int i = 0; i < foreignSubtitleVo.getSmiList().size(); i++) {
        			UsrSttlVo usrForiegnSttlVo = new UsrSttlVo();
                	usrForiegnSttlVo.setUsrId(usrId);
                	usrForiegnSttlVo.setSttlNm(foreignSubtitleFile.getOriginalFilename());
                	usrForiegnSttlVo.setSttlCd("1");
              		usrForiegnSttlVo.setSttlStm(foreignSubtitleVo.getSmiList().get(i).getStime()+"");
              		usrForiegnSttlVo.setSttlEtm(foreignSubtitleVo.getSmiList().get(i).getEtime()+"");
              		usrForiegnSttlVo.setSttlDesc(foreignSubtitleVo.getSmiList().get(i).getContent());
              		subtitleDao.insertUsrSttlDtl(usrForiegnSttlVo);
				}
        	} else {
        		for (int i = 0; i < foreignSubtitleVo.getSrtList().size(); i++) {
        			UsrSttlVo usrForiegnSttlVo = new UsrSttlVo();
                	usrForiegnSttlVo.setUsrId(usrId);
                	usrForiegnSttlVo.setSttlNm(foreignSubtitleFile.getOriginalFilename());
                	usrForiegnSttlVo.setSttlCd("1");
              		usrForiegnSttlVo.setSttlStm(foreignSubtitleVo.getSrtList().get(i).getStime()+"");
              		usrForiegnSttlVo.setSttlEtm(foreignSubtitleVo.getSrtList().get(i).getEtime()+"");
              		usrForiegnSttlVo.setSttlDesc(foreignSubtitleVo.getSrtList().get(i).getContent());
              		subtitleDao.insertUsrSttlDtl(usrForiegnSttlVo);
				}
        	}	
//          // parse foreignSubtitleList
//          // parse motherSubtitleList
//          // sync subtitles
//          // verify
//          // insert foreignSubtitleList
//          // insert motherSubtitleList
//          // insert syncSubtitleList
//          // return syncSubtitleList
        	
        	
           	result.put("isSuccess", true);
   	       	result.put("usrMsg", "정상 처리 되었습니다");
		} catch (Exception e){
			e.printStackTrace();
			result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
			//result.put("errSysMsg", e.toString());
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
