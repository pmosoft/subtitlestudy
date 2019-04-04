package net.pmosoft.subtitle.subtitle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubtitleDao {
    void insertUsrSttlMstr(Subtitle inVo);
    void insertUsrSttlDtl(Subtitle inVo);
    void insertUsrSttlDtlBulk(List<Subtitle> inVo);
    void insertTestBulk(String qry);
    void insertTestBulk2(String qry);
    void insertSttlNum(Subtitle inVo);
    void insertReviewSttl(Subtitle inVo);
    List<Subtitle> selectUsrSttl(Subtitle inVo);
    List<Subtitle> selectUsrRecentlySttl(Subtitle inVo);
    List<Subtitle> selectUsrSttlMstrList(Subtitle inVo);
    List<Subtitle> selectUsrSttlDtlList(Subtitle inVo);
    List<Subtitle> selectReviewSttlList(Subtitle inVo);
    void updateSttlNum(Subtitle inVo);
    void updateUsrLang(Subtitle inVo);
    void updateReviewCnt(Subtitle inVo);
    void updateReviewCd(Subtitle inVo);
    void deleteUsrSttlMstr(Subtitle inVo);
    void deleteUsrSttlDtl(Subtitle inVo);
    void deleteSttlNum(Subtitle inVo);
}

