package net.pmosoft.subtitle.subtitle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SubtitleCtrl {

    @Autowired
    private SubtitleSrv subtitleSrv;

    /**********************************************************************************
     *                                     저장
     **********************************************************************************/

    /**
     * 유저의 외국어 자막 및 모국어 자막을 합성하여 하나의 자막으로 합성 저장한다.
     * @param
    */
    @RequestMapping(value = "/subtitle/saveUsrSubtitles")
    public Map<String, Object> saveUsrSubtitles(
         @RequestPart("uploadFile") MultipartFile foreignSubtitleFile
        ,@RequestPart("uploadFile2") MultipartFile motherSubtitleFile
        ,@RequestParam String usrId){
        return subtitleSrv.saveUsrSubtitles(usrId,foreignSubtitleFile,motherSubtitleFile);
    }

    /**
     * 자막 책갈피 기능. 자막번호 저장
     */
    @RequestMapping(value = "/subtitle/saveSttlNum")
    public Map<String, Object> saveSttlNum(@RequestBody Subtitle inVo) {
        return subtitleSrv.saveSttlNum(inVo);
    }

    /**
     * 리뷰 저장
     */
    @RequestMapping(value = "/subtitle/saveReviewSttl")
    public Map<String, Object> saveReviewSttl(@RequestBody Subtitle inVo) {
        return subtitleSrv.saveReviewSttl(inVo);
    }

    /**********************************************************************************
     *                                     조회
     **********************************************************************************/

    /**
     * 자막목록 조회
     */
    @RequestMapping(value = "/subtitle/selectUsrSttlMstrList")
    public Map<String, Object> selectUsrSttlMstrList(@RequestBody Subtitle inVo) {
        return subtitleSrv.selectUsrSttlMstrList(inVo);
    }

    /**
     * 유저 최근 등록 자막 조회
     */
    @RequestMapping(value = "/subtitle/selectUsrRecentlySttl")
    public Map<String, Object> selectUsrRecentlySttl(@RequestBody Subtitle inVo) {
        return subtitleSrv.selectUsrRecentlySttl(inVo);
    }

    /**
     * 유저 목록에서 선택된 자막 조회
     */
    @RequestMapping(value = "/subtitle/selectUsrSttl")
    public Map<String, Object> selectUsrSttl(@RequestBody Subtitle inVo) {
        return subtitleSrv.selectUsrSttl(inVo);
     }

    /**
     * 자막상세 조회
     */
    @RequestMapping(value = "/subtitle/selectUsrSttlDtlList")
    public Map<String, Object> selectUsrSttlDtlList(@RequestParam Subtitle inVo) {
        return subtitleSrv.selectUsrSttlDtlList(inVo);
    }

    /**
     * 리뷰자막 조회
     */
    @RequestMapping(value = "/subtitle/selectReviewSttlList")
    public Map<String, Object> selectReviewSttlList(@RequestBody Subtitle inVo) {
    	System.out.println("selectReviewSttlList");
        return subtitleSrv.selectReviewSttlList(inVo);
    }

    /**********************************************************************************
     *                                     수정
     **********************************************************************************/

    /**
     * 리뷰수 갱신
     */
    @RequestMapping(value = "/subtitle/updateReviewCnt")
    public Map<String, Object> updateReviewCnt(@RequestBody Subtitle inVo) {
        return subtitleSrv.updateReviewCnt(inVo);
    }

    /**
     * 리뷰코드 갱신
     */
    @RequestMapping(value = "/subtitle/updateReviewCd")
    public Map<String, Object> updateReviewCd(@RequestBody Subtitle inVo) {
        return subtitleSrv.updateReviewCd(inVo);
    }

    /**********************************************************************************
     *                                     삭제
     **********************************************************************************/

    /**
     * 자막 삭제(Multi:json)
     */
    @RequestMapping(value = "/subtitle/deleteUsrSttl")
    public Map<String, Object> deleteUsrSttl(@RequestParam Subtitle inVo) {
        return subtitleSrv.deleteUsrSttl(inVo);
    }

    /**********************************************************************************
     *                                     ETC
     **********************************************************************************/

}
