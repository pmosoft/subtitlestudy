package net.pmosoft.subtitle.subtitle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubtitleDao {
    void insertUsrSttlMstr(UsrSttlVo inVo);
    //void insertUsrSttlDtl(UsrSttlVo inVo);
    void insertUsrSttlDtl(List<UsrSttlVo> inVo);
    void insertTestBulk(String qry);
    void insertTestBulk2(String qry);
    List<UsrSttlVo> selectUsrRecentlySttl(String usrId);
    List<UsrSttlVo> selectUsrSttlMstrList(UsrSttlVo inVo);
    List<UsrSttlVo> selectUsrSttlDtlList(UsrSttlVo inVo);
    void deleteUsrSttlMstr(UsrSttlVo inVo);
    void deleteUsrSttlDtl(UsrSttlVo inVo);
}

