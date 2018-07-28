package net.pmosoft.subtitle.subtitle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public void saveUsrSubtitles(
             @RequestPart("uploadFile") MultipartFile foreignSubtitleFile
            ,@RequestPart("uploadFile2") MultipartFile motherSubtitleFile
            ,@RequestParam String usr
            ,HttpServletResponse response){
        try {
            
             System.out.println("asdf344443sdf==="+foreignSubtitleFile.getName());
             System.out.println("11222222222222222222221134123412341ddddsdf==="+motherSubtitleFile.getName());
             System.out.println("asdf33333sdf==="+usr);
             JSONObject jsonObj = subtitleSrv.saveUsrSubtitles(usr,foreignSubtitleFile,motherSubtitleFile);
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
    
    
    @RequestMapping(value = "/subtitle/test3")
    @CrossOrigin(origins="http://localhost:4200")
    public Map<String, Object> test3(){
        System.out.println("111111111444411111111111111112222");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", 10);        
        result.put("name", "abc");
        return result;
    }        

    @RequestMapping(value = "/subtitle/test4")
    @CrossOrigin(origins="http://localhost:4200")
    public Map<String, Object> test4(@RequestParam Map<String,String> mapParam) {
    	
    	System.out.println("mapParam=="+mapParam);
    	System.out.println("mapParam.name=="+mapParam.get("name"));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", 10);        
        result.put("name", "abc");
        return result;
    }
    
    
    @RequestMapping(value = "/subtitle/test5")
    @CrossOrigin(origins="http://localhost:4200")
    public Subtitle test5(Subtitle subtitle){
        System.out.println("test5 1111111111112222");
        System.out.println("subtitle="+subtitle);
        System.out.println("subtitle.name="+subtitle.name);
        System.out.println("subtitle.id="+subtitle.id);
        
        Subtitle st = new Subtitle();
        st.setId(10);
        st.setName("abc");
        return st;
    }        
    
    @RequestMapping(value = "/subtitle/test6")
    public Map<String, Object> test6(){
        System.out.println("111111111444411111111111111112222");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", "abc");
        result.put("age", 10);
        
        ArrayList<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
        list1.add(result);

        Map<String, Object> result1 = new HashMap<String, Object>();
        result1.put("name", "abc");
        result1.put("age", 10);

        result.put("result1", result1);
        return result;
    }    

    
    
    
    
    
    
    
}
