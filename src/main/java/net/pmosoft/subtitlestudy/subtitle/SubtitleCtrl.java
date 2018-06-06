package net.pmosoft.subtitlestudy.subtitle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
    
/**
 *
 */
@RestController
public class SubtitleCtrl {

    @Autowired
    private SubtitleSrv subtitleSrv;

    /**
     * 유저의 외국어 자막 및 모국어 자막을 합성하여 하나의 자막으로 합성 저장한다.
     * @param  
    */
    @RequestMapping(value = "/subtitle/saveUsrSubtitles")
    public void saveUsrSubtitles(@RequestPart("uploadFile") MultipartFile files, HttpServletResponse response){
        System.out.println("11111111111111111111111111111111111111111111");
        try {
             JSONObject jsonObj = subtitleSrv.saveUsrSubtitles(files);
             response.setContentType("text/plain; charset=UTF-8");
             PrintWriter pw = response.getWriter();
             pw.print(jsonObj);
             pw.flush();
             pw.close();
        } catch (IOException e) {}
            //return codeSrv.uploadCodeRegList(files);
    }        
    
    
    /**
     * 자막목록 조회
     */
    @RequestMapping(value = "/subtitle/selectUsrSubtitleList")
    public Map<String, Object> selectUsrSubtitleList(@RequestParam Map<String,String> params) {
        return subtitleSrv.selectUsrSubtitleList(params);
    }
   
    /**
     * 자막 저장(Multi:json)
     */
    @RequestMapping(value = "/subtitle/saveSubtitle")
    public Map<String, Object> saveSubtitle(@RequestParam Map<String,String> params) {
        return subtitleSrv.saveSubtitle(params);
    }

    /**
     * 자막 삭제(Multi:json)
     */
    @RequestMapping(value = "/subtitle/deleteSubtitle")
    public Map<String, Object> deleteSubtitle(@RequestParam Map<String,String> params) {
        return subtitleSrv.deleteSubtitle(params);
    }

    @RequestMapping(value = "/subtitle/test")
    public void test(){
        System.out.println("11111111111111111111111111111111111111111111");
    }        
    
    @RequestMapping(value = "/subtitle/test2")
    public String test2(){
        System.out.println("111111111111111111111111111111111111112222");
        return "abc";
    }        
    
    
}
