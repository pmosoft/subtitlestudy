package net.pmosoft.subtitlestudy.parse;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
    
/**
 *
 */
@RestController
public class ParseCtrl {

    @Autowired
    private ParseSrv parseSrv;

    /**
     * 코드목록 조회
     */
    @RequestMapping(value = "/dams/parse/selectParseList")
    public Map<String, Object> selectParseList(@RequestParam Map<String,String> params) {
        return parseSrv.selectParseList(params);
    }
   
    /**
     * 코드 저장(Multi:json)
     */
    @RequestMapping(value = "/dams/parse/saveParse")
    public Map<String, Object> saveParse(@RequestParam Map<String,String> params) {
        return parseSrv.saveParse(params);
    }

    /**
     * 코드 삭제(Multi:json)
     */
    @RequestMapping(value = "/dams/parse/deleteParse")
    public Map<String, Object> deleteParse(@RequestParam Map<String,String> params) {
        return parseSrv.deleteParse(params);
    }

}
