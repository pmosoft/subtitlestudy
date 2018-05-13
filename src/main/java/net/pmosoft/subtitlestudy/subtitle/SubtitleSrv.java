package net.pmosoft.subtitlestudy.subtitle;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.fframe.comm.App;

import org.json.simple.JSONObject;
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

    public JSONObject saveUsrSubtitles(MultipartFile files) {
        String fileNm = files.getOriginalFilename();
        //System.out.println("fileNm="+fileNm);
        
        JSONObject jsonObj = new JSONObject();
        
        try {
//            for (int i = 0; i < files.size(); i++) {
//                byte[] bytes = files.get(i).getBytes();
//                Path path = Paths.get(App.excelPath + fileNm);
//                Files.write(path, bytes);
//            } 
            byte[] bytes = files.getBytes();
            Path path = Paths.get(App.excelPath + fileNm);
            System.out.println(App.excelPath + fileNm);
            Files.write(path, bytes);
             
            List<Map<String,String>> list = null; 

            jsonObj.put("success", true);
            jsonObj.put("isSuccess", true);
            jsonObj.put("successMsg", "업로드 되었습니다");
            jsonObj.put("data", list);
            
        } catch (Exception e) {
            //e.printStackTrace();
           
            System.out.println("ExcelSrv asdfasdfasdfasdf");
            jsonObj.put("isSuccess", false);
            jsonObj.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            jsonObj.put("errSysMsg", e.getMessage());
        } finally {
            return jsonObj;
        }
    }     
    
    public Map<String, Object> selectUsrSubtitleList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String,Object>> list = null;
            list = subtitleDao.selectSubtitleList(params);;
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

    public Map<String, Object> saveSubtitle(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    public Map<String, Object> deleteSubtitle(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
            
            Map<String, String> errors = new HashMap<String, String>();
            errors = subtitleValidatorSrv.validateDeleteSubtitle(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    subtitleDao.deleteSubtitle(listParams.get(i));
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "삭제 되었습니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
