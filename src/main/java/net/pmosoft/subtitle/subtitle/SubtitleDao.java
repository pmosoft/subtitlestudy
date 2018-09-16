package net.pmosoft.subtitle.subtitle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubtitleDao {
    void insertUsrSttlMstr(UsrSttlVo inVo);
    void insertUsrSttlDtl(UsrSttlVo inVo);
    void insertUsrSttlDtlBulk(List<UsrSttlVo> inVo);
    void insertTestBulk(String qry);
    void insertTestBulk2(String qry);
    void insertSttlNum(UsrSttlVo inVo);
    List<UsrSttlVo> selectUsrSttl(UsrSttlVo inVo);
    List<UsrSttlVo> selectUsrRecentlySttl(UsrSttlVo inVo);
    List<UsrSttlVo> selectUsrSttlMstrList(UsrSttlVo inVo);
    List<UsrSttlVo> selectUsrSttlDtlList(UsrSttlVo inVo);
    void updateSttlNum(UsrSttlVo inVo);
    void deleteUsrSttlMstr(UsrSttlVo inVo);
    void deleteUsrSttlDtl(UsrSttlVo inVo);
    void deleteSttlNum(UsrSttlVo inVo);
} 

