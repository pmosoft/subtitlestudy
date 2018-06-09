package net.pmosoft.subtitle.subtitle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubtitleDao {


    List<Map<String, Object>> selectSubtitleList(Map<String,String> params);
    void insertSubtitle(Map<String,String> params);
    void updateSubtitle(Map<String,String> params);
    void deleteSubtitle(Map<String,String> params);
}

